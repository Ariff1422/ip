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

    public static void main(String[] args) {
        String name = "Vox";
        // Initialise Scanner
        Scanner in = new Scanner(System.in);
        // Initialise a String array to maintain user list
        ArrayList<Task> userInputs = new ArrayList<Task>();
        String logo = "__      __          \n"
                + "\\ \\    / /_  __  __ \n"
                + " \\ \\  / / _ \\ \\ \\/ /\n"
                + "  \\ \\/ / (_) | >  < \n"
                + "   \\__/ \\___/ /_/\\_\\\n";
        printLine();
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        printLine();
        while (true) {
            String line;
            System.out.print(GREEN);
            line = in.nextLine();
            System.out.print(RESET);

            // Terminating after bye
            if (line.trim().equalsIgnoreCase("bye")) {
                printLine();
                break;
            }
            // Printing the items as a list
            else if (line.trim().equalsIgnoreCase("list")) {
                printBreaks();
                for (int i = 0; i < userInputs.size(); i++) {
                    // Retrieve task for printing
                    Task t = userInputs.get(i);
                    System.out.println(("    " + (i + 1) + ".[" + t.getStatusIcon() + "] " + t.getTaskName()));
                }
                printBreaks();
            }
            // Marking the items
            else if (line.trim().startsWith("mark")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                Task t = userInputs.get(index);
                t.setMarked(); // Make the task marked
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      [" + t.getStatusIcon() + "] " + t.getTaskName());
            }
            // Unmarking the items
            else if (line.trim().startsWith("unmark")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                Task t = userInputs.get(index);
                t.setUnmarked(); // Make the task unmarked
                System.out.println("    I've unmarked this task as done:");
                System.out.println("      [" + t.getStatusIcon() + "] " + t.getTaskName());
            } else {
                userInputs.add(new Task(line));
                printBreaks();
                System.out.println("    added: " + line);
                printBreaks();
            }
        }
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }
}
