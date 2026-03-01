package vox;

/**
 * Parses raw user input into a command keyword and its arguments.
 */
public class Parser {

    /**
     * Splits a full command string into a command keyword and its arguments.
     *
     * @param fullCommand the raw input string from the user
     * @return a two-element array where index 0 is the lowercase command
     *         and index 1 is the remaining arguments (empty string if none)
     */
    public static String[] parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        return new String[]{command, arguments};
    }
}
