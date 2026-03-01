package vox.task;

/**
 * Represents a simple todo task with no associated date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the pipe-delimited file format string for this todo.
     *
     * @return the file format string prefixed with "T"
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * Returns a human-readable string representation of this todo.
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getTaskName();
    }
}
