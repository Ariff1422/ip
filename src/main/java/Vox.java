import java.util.Scanner;
import java.util.ArrayList;

public class Vox {
    // Define Color Constants (ANSI Escape Codes)
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    public static void printLine() {
        // For ease of calling the function and utilising the lines as section breaks
        System.out.println("________________________________________________\n");
    }

    public static void printBreaks() {
        // For the separations between the echoing
        // RESET to prevent any unforeseen break in green colour
        System.out.println(RESET + "    ____________________________________________\n");
    }

    private static void printWelcomeMessage() {
        // Prints the initial Startup Messages
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
        // Prints the ending message when bye is seen
        printLine();
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        printBreaks();
        for (int i = 0; i < tasks.size(); i++) {
            // Retrieve task for printing
            Task t = tasks.get(i);
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        printBreaks();
    }

    private static void markTask(String arguments, ArrayList<Task> tasks) {
        // Try and Catch blocks in case the number that the user gives is out of bounds
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task t = tasks.get(index);
            t.setMarked();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      [" + t.getStatusIcon() + "] " + t.getTaskName());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("    Error: Please provide a valid task number.");
        }
    }

    private static void unmarkTask(String arguments, ArrayList<Task> tasks) {
        // Similar to markTask another try and exception block to prevent crashing
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task t = tasks.get(index);
            t.setUnmarked();
            System.out.println("    I've unmarked this task as done:");
            System.out.println("      [" + t.getStatusIcon() + "] " + t.getTaskName());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("    Error: Please provide a valid task number.");
        }
    }

    private static void addTodo(String description, ArrayList<Task> tasks) {
        // Adds todo
        if (description.isEmpty()) {
            System.out.println("    Error: The description of a todo cannot be empty.");
            return;
        }
        Task newTodo = new Todo(description);
        addTaskToStorage(newTodo, tasks);
    }

    private static void addDeadline(String args, ArrayList<Task> tasks) {
        // Expected format: description /by time
        String[] parts = args.split("/", 2);
        if (parts.length < 2) {
            System.out.println("    Error: Please use /by to specify the deadline time.");
            return;
        }
        String description = parts[0].trim();
        String by = parts[1].replaceFirst(" ", ": ");

        Task newDeadline = new Deadline(description, by);
        addTaskToStorage(newDeadline, tasks);
    }

    private static void addEvent(String args, ArrayList<Task> tasks) {
        // Expected format: description /from time /to time
        String[] parts = args.split("/", 2);
        // Exception programming for now, to be made more robust later
        if (parts.length < 2) {
            System.out.println("    Error: Please use /from and /to for events.");
            return;
        }
        String description = parts[0].trim();
        String limit = parts[1].replaceFirst(" ", ": ");

        Task newEvent = new Event(description, limit);
        addTaskToStorage(newEvent, tasks);
    }

    private static void addGenericTask(String line, ArrayList<Task> tasks) {
        // echoes the task typed in the terminal
        tasks.add(new Task(line));
        printBreaks();
        System.out.println("    added: " + line);
        printBreaks();
    }

    private static void addTaskToStorage(Task task, ArrayList<Task> tasks) {
        // adds task to the ArrayList and prints the confirmation for todo, deadline and event
        tasks.add(task);
        printBreaks();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        printBreaks();
    }

    private static void handleCommand(String command, String arguments, ArrayList<Task> tasks, String fullLine) {
        // based on the commands whether its deadline, event, etc.
        switch (command) {
        case "list":
            listTasks(tasks);
            break;
        case "mark":
            markTask(arguments, tasks);
            break;
        case "unmark":
            unmarkTask(arguments, tasks);
            break;
        case "todo":
            addTodo(arguments, tasks);
            break;
        case "deadline":
            addDeadline(arguments, tasks);
            break;
        case "event":
            addEvent(arguments, tasks);
            break;
        default:
            // Default behavior: add as a generic task by echoing
            addGenericTask(fullLine, tasks);
            break;
        }
    }

    public static void main(String[] args) {
        String name = "Vox";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> userInputs = new ArrayList<Task>();

        printWelcomeMessage();

        while (true) {
            System.out.print(GREEN);
            String line = in.nextLine().trim();
            System.out.print(RESET);

            if (line.isEmpty()) {
                continue;
            }

            // Split command from arguments (e.g., "todo read" -> ["todo", "read"])
            String[] parts = line.split(" ", 2);
            String command = parts[0].toLowerCase();
            String arguments = parts.length > 1 ? parts[1] : "";

            // bye is handled separately to break out of the loop when command is sensed
            if (command.equals("bye")) {
                printExitMessage();
                break;
            }

            // Command Dispatcher: Decides which method to call
            handleCommand(command, arguments, userInputs, line);
        }
    }
}
