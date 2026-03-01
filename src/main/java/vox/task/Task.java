package vox.task;

/**
 * Represents a generic task with a name and completion status.
 * Serves as the base class for Todo, Deadline, and Event.
 */
public class Task {
    private String taskName;
    private boolean isMarked;

    /**
     * Constructs a Task with the given name, initially unmarked.
     *
     * @param taskName the name/description of the task
     */
    public Task(String taskName) {
        setTaskName(taskName);
        isMarked = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the name of the task.
     *
     * @param taskName the new task name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks the task as done.
     */
    public void setMarked() {
        isMarked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUnmarked() {
        isMarked = false;
    }

    /**
     * Returns "X" if the task is done, or a blank space if not.
     *
     * @return the status icon string
     */
    public String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }

    /**
     * Returns a pipe-delimited string representation for file storage.
     *
     * @return the file format string
     */
    public String toFileString() {
        return " | " + (isMarked ? "1" : "0") + " | " + taskName;
    }

    /**
     * Returns a human-readable string representation of the task.
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[ ][" + getStatusIcon() + "] " + getTaskName();
    }
}
