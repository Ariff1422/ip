package vox.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        // Use regex to find all the remaining / and replace it with a word: format
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getTaskName() + " (by: " + by + ")";
    }
}
