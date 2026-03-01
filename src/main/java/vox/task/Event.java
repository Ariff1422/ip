package vox.task;

/**
 * Represents a task that spans a time range with a start and end time.
 */
public class Event extends Task {
    protected String by;

    /**
     * Constructs an Event task with the given description and time range.
     * Any slash-prefixed tokens (e.g. /to) are converted to "to:" format.
     *
     * @param description the task description
     * @param by          the time range string in the format "start to: end"
     */
    public Event(String description, String by) {
        super(description);
        this.by = by.replaceAll("/(\\w+)", "$1:");
    }

    /**
     * Returns the pipe-delimited file format string for this event.
     *
     * @return the file format string prefixed with "E"
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + by;
    }

    /**
     * Returns a human-readable string representation of this event.
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getTaskName() + " (from: " + by + ")";
    }
}
