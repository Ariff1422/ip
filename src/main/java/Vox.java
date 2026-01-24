public class Vox {
    public static void main(String[] args) {
        String name = "Vox";
        // For separation between sections, use horizontalLine
        String horizontalLine = "─────────────────────────────────────────────────────────────";
        String logo = "__      __          \n"
                + "\\ \\    / /_  __  __ \n"
                + " \\ \\  / / _ \\ \\ \\/ /\n"
                + "  \\ \\/ / (_) | >  < \n"
                + "   \\__/ \\___/ /_/\\_\\\n";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(horizontalLine);
    }
}
