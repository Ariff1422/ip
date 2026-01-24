public class Vox {
    public static void printLine() {
        // For ease of calling the function and utilising the lines as section breaks
        System.out.println("____________________________________________________________\n");
    }
    public static void main(String[] args) {
        String name = "Vox";
        String logo = "__      __          \n"
                + "\\ \\    / /_  __  __ \n"
                + " \\ \\  / / _ \\ \\ \\/ /\n"
                + "  \\ \\/ / (_) | >  < \n"
                + "   \\__/ \\___/ /_/\\_\\\n";
        printLine();
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        printLine();
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }
}
