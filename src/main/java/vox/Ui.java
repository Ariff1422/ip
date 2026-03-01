package vox;

import java.util.Scanner;
import vox.task.Task;
import java.util.ArrayList;

public class Ui {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("________________________________________________\n");
    }

    public void showBreaks() {
        System.out.println(RESET + "    ____________________________________________\n");
    }

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

    public void showExit() {
        showLine();
        System.out.println("Bye. Hope to see you again!");
        showLine();
    }

    public void showError(String message) {
        showBreaks();
        System.out.println(RED + "    OOPS!!! " + message + RESET);
        showBreaks();
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks file. Starting with empty task list.");
    }

    public String readCommand() {
        System.out.print(GREEN);
        String line = scanner.nextLine().trim();
        System.out.print(RESET);
        return line;
    }

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

    public void showTaskAdded(Task task, int totalTasks) {
        showBreaks();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        showBreaks();
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        showBreaks();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        showBreaks();
    }

    public void showTaskMarked(Task task) {
        showBreaks();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [" + task.getStatusIcon() + "] " + task.getTaskName());
        showBreaks();
    }

    public void showTaskUnmarked(Task task) {
        showBreaks();
        System.out.println("    I've unmarked this task as done:");
        System.out.println("      [" + task.getStatusIcon() + "] " + task.getTaskName());
        showBreaks();
    }
}
