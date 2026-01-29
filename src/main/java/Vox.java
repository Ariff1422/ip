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
        ArrayList<String> userInputs = new ArrayList<String>();
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
            if (line.trim().equalsIgnoreCase("list")) {
                printBreaks();
                for (int i = 0; i < userInputs.size(); i++) {
                    System.out.println(("    " + (i + 1) + ". " + userInputs.get(i)));
                }
                printBreaks();
            } else {
                userInputs.add(line);
                printBreaks();
                System.out.println("    added: " + line);
                printBreaks();
            }
            if (line.trim().equalsIgnoreCase("bye")) {
                printLine();
                break;
            }
        }
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }
}
