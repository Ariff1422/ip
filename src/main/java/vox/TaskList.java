package vox;

import java.util.ArrayList;
import vox.task.Deadline;
import vox.task.Event;
import vox.task.Task;
import vox.task.Todo;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task addTodo(String description) throws VoxException {
        if (description.isEmpty()) {
            throw new VoxException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    public Task addDeadline(String args) throws VoxException {
        if (args.isEmpty()) {
            throw new VoxException("The description of a deadline cannot be empty.");
        }

        String[] parts = args.split("/by", 2);
        if (parts.length < 2) {
            throw new VoxException("A deadline must have a /by date. (Format: description /by time)");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new VoxException("The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new VoxException("The date of a deadline cannot be empty.");
        }

        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    public Task addEvent(String args) throws VoxException {
        if (args.isEmpty()) {
            throw new VoxException("The description of an event cannot be empty.");
        }

        String[] parts = args.split("/from", 2);
        if (parts.length < 2) {
            throw new VoxException("An event must have a /from date.");
        }

        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new VoxException("An event must have a /to date.");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty()) {
            throw new VoxException("The description of an event cannot be empty.");
        }

        Task task = new Event(description, from + " to: " + to);
        tasks.add(task);
        return task;
    }

    public Task deleteTask(String arguments) throws VoxException {
        try {
            if (arguments.isEmpty()) {
                throw new VoxException("Please specify which task number to delete.");
            }
            int index = Integer.parseInt(arguments) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new VoxException("Task number " + arguments + " does not exist.");
            }
            return tasks.remove(index);
        } catch (NumberFormatException e) {
            throw new VoxException("That is not a valid number.");
        }
    }

    public Task markTask(String arguments) throws VoxException {
        try {
            if (arguments.isEmpty()) {
                throw new VoxException("Please specify which task number to mark.");
            }
            int index = Integer.parseInt(arguments) - 1;
            Task task = tasks.get(index);
            task.setMarked();
            return task;
        } catch (NumberFormatException e) {
            throw new VoxException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new VoxException("Task number " + arguments + " does not exist.");
        }
    }

    public Task unmarkTask(String arguments) throws VoxException {
        try {
            if (arguments.isEmpty()) {
                throw new VoxException("Please specify which task number to unmark.");
            }
            int index = Integer.parseInt(arguments) - 1;
            Task task = tasks.get(index);
            task.setUnmarked();
            return task;
        } catch (NumberFormatException e) {
            throw new VoxException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new VoxException("Task number " + arguments + " does not exist.");
        }
    }
}
