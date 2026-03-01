package vox;

public class Parser {
    public static String[] parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        return new String[]{command, arguments};
    }
}
