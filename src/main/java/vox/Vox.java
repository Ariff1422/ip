package vox;

import vox.task.Task;

public class Vox {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Vox(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (VoxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String fullCommand = ui.readCommand();

            if (fullCommand.isEmpty()) {
                continue;
            }

            String[] parsed = Parser.parse(fullCommand);
            String command = parsed[0];
            String arguments = parsed[1];

            if (command.equals("bye")) {
                ui.showExit();
                break;
            }

            try {
                handleCommand(command, arguments);
            } catch (VoxException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void handleCommand(String command, String arguments) throws VoxException {
        switch (command) {
        case "list":
            ui.showTaskList(tasks.getTasks());
            break;
        case "delete": {
            Task removed = tasks.deleteTask(arguments);
            storage.save(tasks.getTasks());
            ui.showTaskDeleted(removed, tasks.size());
            break;
        }
        case "mark": {
            Task marked = tasks.markTask(arguments);
            storage.save(tasks.getTasks());
            ui.showTaskMarked(marked);
            break;
        }
        case "unmark": {
            Task unmarked = tasks.unmarkTask(arguments);
            storage.save(tasks.getTasks());
            ui.showTaskUnmarked(unmarked);
            break;
        }
        case "todo": {
            Task added = tasks.addTodo(arguments);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(added, tasks.size());
            break;
        }
        case "deadline": {
            Task added = tasks.addDeadline(arguments);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(added, tasks.size());
            break;
        }
        case "event": {
            Task added = tasks.addEvent(arguments);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(added, tasks.size());
            break;
        }
        default:
            throw new VoxException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        new Vox("./data/vox.txt").run();
    }
}
