package vox.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline date and time.
 * Accepts dates in {@code yyyy-MM-dd HHmm} format and displays them
 * as {@code MMM dd yyyy, h:mma} (e.g. Dec 02 2019, 6:00PM).
 * Falls back to storing the raw string if the date cannot be parsed.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private LocalDateTime by;
    private String byRaw;

    /**
     * Constructs a Deadline task with the given description and deadline string.
     * Attempts to parse the deadline as a {@code LocalDateTime}; stores it as
     * a raw string if parsing fails.
     *
     * @param description the task description
     * @param by          the deadline string in {@code yyyy-MM-dd HHmm} format
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
            this.byRaw = null;
        } catch (DateTimeParseException e) {
            this.by = null;
            this.byRaw = by;
        }
    }

    /**
     * Returns the pipe-delimited file format string for this deadline.
     * Saves the date in input format if parsed, otherwise saves the raw string.
     *
     * @return the file format string prefixed with "D"
     */
    @Override
    public String toFileString() {
        String byStr = (by != null) ? by.format(INPUT_FORMAT) : byRaw;
        return "D" + super.toFileString() + " | " + byStr;
    }

    /**
     * Returns a human-readable string representation of this deadline.
     * Displays the date in a formatted style if parsed, otherwise uses the raw string.
     *
     * @return the display string
     */
    @Override
    public String toString() {
        String byStr = (by != null) ? by.format(OUTPUT_FORMAT) : byRaw;
        return "[D][" + getStatusIcon() + "] " + getTaskName() + " (by: " + byStr + ")";
    }
}
