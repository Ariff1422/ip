package vox;

import java.util.ArrayList;
import vox.task.Deadline;
import vox.task.Event;
import vox.task.Task;
import vox.task.Todo;

/**
 * Manages the list of tasks and provides operations to add, delete,
 * mark, unmark, and search tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList pre-populated with the given tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Creates a new Todo task and adds it to the list.
     *
     * @param description the task description
     * @return the newly created Todo task
     * @throws VoxException if the description is empty
     */
    public Task addTodo(String description) throws VoxException {
        if (description.isEmpty()) {
            throw new VoxException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Creates a new Deadline task and adds it to the list.
     * Expected format: {@code description /by date}.
     *
     * @param args the full argument string containing description and /by date
     * @return the newly created Deadline task
     * @throws VoxException if the description or date is missing
     */
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

    /**
     * Creates a new Event task and adds it to the list.
     * Expected format: {@code description /from start /to end}.
     *
     * @param args the full argument string containing description, /from and /to times
     * @return the newly created Event task
     * @throws VoxException if the description or either time field is missing
     */
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

    /**
     * Deletes the task at the given 1-based index.
     *
     * @param arguments the task number as a string
     * @return the removed task
     * @throws VoxException if the number is invalid or out of range
     */
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

    /**
     * Marks the task at the given 1-based index as done.
     *
     * @param arguments the task number as a string
     * @return the marked task
     * @throws VoxException if the number is invalid or out of range
     */
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

    /**
     * Unmarks the task at the given 1-based index as not done.
     *
     * @param arguments the task number as a string
     * @return the unmarked task
     * @throws VoxException if the number is invalid or out of range
     */
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

    /**
     * Returns all tasks whose name contains the given keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return list of matching tasks
     * @throws VoxException if the keyword is empty
     */
    public ArrayList<Task> findTasks(String keyword) throws VoxException {
        if (keyword.isEmpty()) {
            throw new VoxException("Please specify a keyword to search for.");
        }
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }
}
