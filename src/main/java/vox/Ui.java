package vox;

import java.util.Scanner;
import vox.task.Task;
import java.util.ArrayList;

/**
 * Handles all user interaction for the Vox application.
 * Responsible for reading input and displaying output to the user.
 */
public class Ui {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    private final Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a plain divider line.
     */
    public void showLine() {
        System.out.println("________________________________________________\n");
    }

    /**
     * Prints an indented divider line used around most responses.
     */
    public void showBreaks() {
        System.out.println(RESET + "    ____________________________________________\n");
    }

    /**
     * Displays the welcome message and Vox logo on startup.
     */
    public void showWelcome() {
        String logo = "__      __          \n"
                + "\\ \\    / /_  __  __ \n"
                + " \\ \\  / / _ \\ \\/ /\n"
                + "  \\ \\/ / (_) | >  < \n"
                + "   \\__/ \\___/ /_/\\_\\\n";
        showLine();
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message when the user exits.
     */
    public void showExit() {
        showLine();
        System.out.println("Bye. Hope to see you again!");
        showLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        showBreaks();
        System.out.println(RED + "    OOPS!!! " + message + RESET);
        showBreaks();
    }

    /**
     * Displays a warning that the task file could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks file. Starting with empty task list.");
    }

    /**
     * Reads a command from the user via standard input.
     *
     * @return the trimmed input string entered by the user
     */
    public String readCommand() {
        System.out.print(GREEN);
        String line = scanner.nextLine().trim();
        System.out.print(RESET);
        return line;
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks the list of tasks to display
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showBreaks();
        if (tasks.isEmpty()) {
            System.out.println("    Your list is currently empty.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        showBreaks();
    }

    /**
     * Displays a confirmation message after a task is added.
     *
     * @param task       the task that was added
     * @param totalTasks the total number of tasks after adding
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showBreaks();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        showBreaks();
    }

    /**
     * Displays a confirmation message after a task is deleted.
     *
     * @param task       the task that was removed
     * @param totalTasks the total number of tasks after deletion
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        showBreaks();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        showBreaks();
    }

    /**
     * Displays a confirmation message after a task is marked as done.
     *
     * @param task the task that was marked
     */
    public void showTaskMarked(Task task) {
        showBreaks();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [" + task.getStatusIcon() + "] " + task.getTaskName());
        showBreaks();
    }

    /**
     * Displays a confirmation message after a task is unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        showBreaks();
        System.out.println("    I've unmarked this task as done:");
        System.out.println("      [" + task.getStatusIcon() + "] " + task.getTaskName());
        showBreaks();
    }

    /**
     * Displays all available commands and their formats.
     */
    public void showHelp() {
        showBreaks();
        System.out.println("    Here are the available commands:");
        System.out.println("      list                              - Lists all tasks");
        System.out.println("      todo <desc>                       - Adds a todo task");
        System.out.println("      deadline <desc> /by yyyy-MM-dd HHmm  - Adds a deadline task");
        System.out.println("      event <desc> /from <start> /to <end> - Adds an event task");
        System.out.println("      mark <number>                     - Marks a task as done");
        System.out.println("      unmark <number>                   - Unmarks a task");
        System.out.println("      delete <number>                   - Deletes a task");
        System.out.println("      find <keyword>                    - Finds tasks by keyword");
        System.out.println("      bye                               - Exits the application");
        showBreaks();
    }

    /**
     * Displays tasks that match a search keyword.
     *
     * @param matches the list of matching tasks
     */
    public void showMatchingTasks(ArrayList<Task> matches) {
        showBreaks();
        if (matches.isEmpty()) {
            System.out.println("    No matching tasks found.");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println("    " + (i + 1) + "." + matches.get(i));
            }
        }
        showBreaks();
    }
}
