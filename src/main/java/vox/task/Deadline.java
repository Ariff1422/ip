package vox.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private LocalDateTime by;
    private String byRaw; // fallback if date cannot be parsed

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

    @Override
    public String toFileString() {
        String byStr = (by != null) ? by.format(INPUT_FORMAT) : byRaw;
        return "D" + super.toFileString() + " | " + byStr;
    }

    @Override
    public String toString() {
        String byStr = (by != null) ? by.format(OUTPUT_FORMAT) : byRaw;
        return "[D][" + getStatusIcon() + "] " + getTaskName() + " (by: " + byStr + ")";
    }
}
