public class Task {
    private String taskName;
    private boolean isMarked;

    public Task(String taskName) {
        setTaskName(taskName);
        isMarked = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setMarked() {
        isMarked = true;
    }
    public void setUnmarked() {
        isMarked = false;
    }
    public String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }

    public String toString() {
        return "";
    }
}

