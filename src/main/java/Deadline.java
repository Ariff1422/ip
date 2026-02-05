public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        // Use regex to find all the remaining / and replace it with a word: format
        this.by = by.replaceAll("/(\\w+)", "$1:");
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] (" + by + ")";
    }
}
