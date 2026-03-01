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

/**
 * Handles loading and saving of tasks to a persistent file.
 * Tasks are stored in a pipe-delimited format.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the given file path.
     *
     * @param filePath path to the file where tasks are saved and loaded from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * Returns an empty list if the file does not exist.
     *
     * @return list of tasks loaded from the file
     * @throws VoxException if the file exists but cannot be parsed
     */
    public ArrayList<Task> load() throws VoxException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {
                    continue; // skip malformed lines
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D") && parts.length >= 4) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E") && parts.length >= 4) {
                    task = new Event(description, parts[3]);
                }

                if (task != null) {
                    if (isDone) {
                        task.setMarked();
                    }
                    loadedTasks.add(task);
                }
            }
        } catch (Exception e) {
            throw new VoxException("Error loading tasks: " + e.getMessage());
        }

        return loadedTasks;
    }

    /**
     * Saves all tasks to the storage file, creating the directory if needed.
     *
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File directory = new File(filePath).getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(task.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }
}
