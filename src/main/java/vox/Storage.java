package vox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import vox.task.Deadline;
import vox.task.Event;
import vox.task.Task;
import vox.task.Todo;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws VoxException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(description, parts[3]);
                }

                if (task != null) {
                    if (isDone) {
                        task.setMarked();
                    }
                    loadedTasks.add(task);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            throw new VoxException("Error loading tasks: " + e.getMessage());
        }

        return loadedTasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            File directory = new File(filePath).getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }
}
