package vox;

import java.util.Scanner;
import java.util.ArrayList;

import vox.task.Task;
import vox.task.Deadline;
import vox.task.Todo;
import vox.task.Event;

// Reading Files
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Vox {
    // Define Color Constants (ANSI Escape Codes)
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static void printLine() {
        System.out.println("________________________________________________\n");
    }

    public static void printBreaks() {
        System.out.println(RESET + "    ____________________________________________\n");
    }

    private static void printWelcomeMessage() {
        String logo = "__      __          \n"
                + "\\ \\    / /_  __  __ \n"
                + " \\ \\  / / _ \\ \\/ /\n"
                + "  \\ \\/ / (_) | >  < \n"
                + "   \\__/ \\___/ /_/\\_\\\n";
        printLine();
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File("./data/vox.txt");

        if (!file.exists()) {
            return loadedTasks; // Return empty list if file doesn't exist
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| "); // Split by " | "

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
                    if (isDone) task.setMarked();
                    loadedTasks.add(task);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return loadedTasks;
    }

    private static void listTasks(ArrayList<Task> tasks) {
        printBreaks();
        if (tasks.isEmpty()) {
            System.out.println("    Your list is currently empty.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        printBreaks();
    }

    private static void deleteTask(String arguments, ArrayList<Task> tasks) throws VoxException {
        try {
            if (arguments.isEmpty()) {
                throw new VoxException("Please specify which task number to delete.");
            }
            int index = Integer.parseInt(arguments) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new VoxException("Task number " + arguments + " does not exist.");
            }

            Task removedTask = tasks.remove(index);

            printBreaks();
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + removedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            printBreaks();

        } catch (NumberFormatException e) {
            throw new VoxException("That is not a valid number.");
        }
    }

    private static void markTask(String arguments, ArrayList<Task> tasks) throws VoxException {
        try {
            if (arguments.isEmpty()) {
                throw new VoxException("Please specify which task number to mark.");
            }
            int index = Integer.parseInt(arguments) - 1;
            Task t = tasks.get(index);
            t.setMarked();
            printBreaks();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      [" + t.getStatusIcon() + "] " + t.getTaskName());
            printBreaks();
        } catch (NumberFormatException e) {
            throw new VoxException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new VoxException("Vox.task.Task number " + arguments + " does not exist.");
        }
    }

    private static void unmarkTask(String arguments, ArrayList<Task> tasks) throws VoxException {
        try {
            if (arguments.isEmpty()) {
                throw new VoxException("Please specify which task number to unmark.");
            }
            int index = Integer.parseInt(arguments) - 1;
            Task t = tasks.get(index);
            t.setUnmarked();
            printBreaks();
            System.out.println("    I've unmarked this task as done:");
            System.out.println("      [" + t.getStatusIcon() + "] " + t.getTaskName());
            printBreaks();
        } catch (NumberFormatException e) {
            throw new VoxException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new VoxException("Vox.task.Task number " + arguments + " does not exist.");
        }
    }

    private static void addTodo(String description, ArrayList<Task> tasks) throws VoxException {
        if (description.isEmpty()) {
            throw new VoxException("The description of a todo cannot be empty.");
        }
        Task newTodo = new Todo(description);
        addTaskToStorage(newTodo, tasks);
    }

    // Now throws Vox.VoxException for missing /by or empty description
    private static void addDeadline(String args, ArrayList<Task> tasks) throws VoxException {
        if (args.isEmpty()) {
            throw new VoxException("The description of a deadline cannot be empty.");
        }

        // Expected format: description /by time
        String[] parts = args.split("/by", 2);

        if (parts.length < 2) {
            throw new VoxException("A deadline must have a /by date. (Format: description /by time)");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new VoxException("The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new VoxException("The date of a deadline cannot be empty.");
        }

        Task newDeadline = new Deadline(description, by);
        addTaskToStorage(newDeadline, tasks);
    }

    // Throws Vox.VoxException for missing /from or /to
    private static void addEvent(String args, ArrayList<Task> tasks) throws VoxException {
        if (args.isEmpty()) {
            throw new VoxException("The description of an event cannot be empty.");
        }

        // Expected format: description /from time /to time
        // Split by /from first
        String[] parts = args.split("/from", 2);

        if (parts.length < 2) {
            throw new VoxException("An event must have a /from date.");
        }

        String description = parts[0].trim();
        String rest = parts[1]; // this contains "time /to time"

        String[] timeParts = rest.split("/to", 2);
        if (timeParts.length < 2) {
            throw new VoxException("An event must have a /to date.");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty()) {
            throw new VoxException("The description of an event cannot be empty.");
        }

        Task newEvent = new Event(description, from + " to: " + to);
        addTaskToStorage(newEvent, tasks);
    }

    private static void addTaskToStorage(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        printBreaks();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        printBreaks();
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // Creates the directory if it's missing
            }

            FileWriter writer = new FileWriter("./data/vox.txt");

            for (Task task : tasks) {
                // Write the task + a new line
                writer.write(task.toFileString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            // Captures IO errors (like permission issues) and prints a safe message
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // Method signature now includes 'throws Vox.VoxException'
    private static void handleCommand(String command, String arguments, ArrayList<Task> tasks) throws VoxException {
        switch (command) {
        case "list":
            listTasks(tasks);
            break;
        case "delete":
            deleteTask(arguments, tasks);
            saveTasks(tasks);
            break;
        case "mark":
            markTask(arguments, tasks);
            saveTasks(tasks);
            break;
        case "unmark":
            unmarkTask(arguments, tasks);
            saveTasks(tasks);
            break;
        case "todo":
            addTodo(arguments, tasks);
            saveTasks(tasks);
            break;
        case "deadline":
            addDeadline(arguments, tasks);
            saveTasks(tasks);
            break;
        case "event":
            addEvent(arguments, tasks);
            saveTasks(tasks);
            break;
        default:
            // Satisfies the requirement to handle unknown commands
            throw new VoxException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> userInputs = loadTasks();

        printWelcomeMessage();

        while (true) {
            System.out.print(GREEN);
            String line = in.nextLine().trim();
            System.out.print(RESET);

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ", 2);
            String command = parts[0].toLowerCase();
            String arguments = parts.length > 1 ? parts[1] : "";

            if (command.equals("bye")) {
                printExitMessage();
                break;
            }

            // MAIN ERROR HANDLING BLOCK
            try {
                handleCommand(command, arguments, userInputs);
            } catch (VoxException e) {
                printBreaks();
                // Use RED color for the error message if you want
                System.out.println(RED + "    OOPS!!! " + e.getMessage() + RESET);
                printBreaks();
            }
        }
    }
}