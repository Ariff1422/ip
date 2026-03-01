# Vox User Guide

**Vox** is a desktop chatbot for managing your tasks via a command-line interface.
It supports todos, deadlines, and events — and saves your tasks automatically between sessions.

---

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
  - [List all tasks — `list`](#list-all-tasks--list)
  - [Add a todo — `todo`](#add-a-todo--todo)
  - [Add a deadline — `deadline`](#add-a-deadline--deadline)
  - [Add an event — `event`](#add-an-event--event)
  - [Mark a task as done — `mark`](#mark-a-task-as-done--mark)
  - [Unmark a task — `unmark`](#unmark-a-task--unmark)
  - [Delete a task — `delete`](#delete-a-task--delete)
  - [Find tasks by keyword — `find`](#find-tasks-by-keyword--find)
  - [Show all commands — `help`](#show-all-commands--help)
  - [Exit the app — `bye`](#exit-the-app--bye)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have **Java 11** or above installed.
2. Download the latest `vox.jar` from the releases page.
3. Open a terminal in the folder containing the jar and run:
   ```
   java -jar vox.jar
   ```
4. Type a command and press **Enter**. Try `help` to see all available commands.

---

## Features

### List all tasks — `list`

Shows all tasks currently in your list.

**Format:** `list`

**Example:**
```
list
```
**Expected output:**
```
    ____________________________________________

    Here are the tasks in your list:
    1.[T][ ] read textbook
    2.[D][ ] return book (by: Sunday)
    3.[E][ ] project meeting (from: Monday 2pm to: 4pm)
    ____________________________________________
```

---

### Add a todo — `todo`

Adds a simple task with no date or time.

**Format:** `todo <description>`

**Example:**
```
todo read textbook
```
**Expected output:**
```
    ____________________________________________

    Got it. I've added this task:
      [T][ ] read textbook
    Now you have 1 tasks in the list.
    ____________________________________________
```

---

### Add a deadline — `deadline`

Adds a task with a due date and time.
Dates in `yyyy-MM-dd HHmm` format are parsed and displayed in a readable format.
Other date strings are accepted as-is.

**Format:** `deadline <description> /by <date>`

**Examples:**
```
deadline return book /by 2019-12-02 1800
deadline submit report /by Sunday
```
**Expected output (parsed date):**
```
    ____________________________________________

    Got it. I've added this task:
      [D][ ] return book (by: Dec 02 2019, 6:00PM)
    Now you have 2 tasks in the list.
    ____________________________________________
```

---

### Add an event — `event`

Adds a task with a start and end time.

**Format:** `event <description> /from <start> /to <end>`

**Example:**
```
event project meeting /from Monday 2pm /to 4pm
```
**Expected output:**
```
    ____________________________________________

    Got it. I've added this task:
      [E][ ] project meeting (from: Monday 2pm to: 4pm)
    Now you have 3 tasks in the list.
    ____________________________________________
```

---

### Mark a task as done — `mark`

Marks the task at the given number as completed.

**Format:** `mark <task number>`

**Example:**
```
mark 1
```
**Expected output:**
```
    ____________________________________________

    Nice! I've marked this task as done:
      [X] read textbook
    ____________________________________________
```

---

### Unmark a task — `unmark`

Marks a previously completed task as not done.

**Format:** `unmark <task number>`

**Example:**
```
unmark 1
```
**Expected output:**
```
    ____________________________________________

    I've unmarked this task as done:
      [ ] read textbook
    ____________________________________________
```

---

### Delete a task — `delete`

Removes the task at the given number from the list.

**Format:** `delete <task number>`

**Example:**
```
delete 2
```
**Expected output:**
```
    ____________________________________________

    Noted. I've removed this task:
      [D][ ] return book (by: Sunday)
    Now you have 2 tasks in the list.
    ____________________________________________
```

---

### Find tasks by keyword — `find`

Searches for tasks whose description contains the given keyword (case-insensitive).

**Format:** `find <keyword>`

**Example:**
```
find book
```
**Expected output:**
```
    ____________________________________________

    Here are the matching tasks in your list:
    1.[T][ ] read textbook
    2.[D][ ] return book (by: Sunday)
    ____________________________________________
```

---

### Show all commands — `help`

Displays all available commands and their formats.

**Format:** `help`

**Expected output:**
```
    ____________________________________________

    Here are the available commands:
      list                              - Lists all tasks
      todo <desc>                       - Adds a todo task
      deadline <desc> /by yyyy-MM-dd HHmm  - Adds a deadline task
      event <desc> /from <start> /to <end> - Adds an event task
      mark <number>                     - Marks a task as done
      unmark <number>                   - Unmarks a task
      delete <number>                   - Deletes a task
      find <keyword>                    - Finds tasks by keyword
      bye                               - Exits the application
    ____________________________________________
```

---

### Exit the app — `bye`

Exits Vox. Your tasks are automatically saved and will be loaded next time.

**Format:** `bye`

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List | `list` | `list` |
| Todo | `todo <desc>` | `todo read textbook` |
| Deadline | `deadline <desc> /by <date>` | `deadline return book /by 2019-12-02 1800` |
| Event | `event <desc> /from <start> /to <end>` | `event meeting /from Mon 2pm /to 4pm` |
| Mark | `mark <number>` | `mark 1` |
| Unmark | `unmark <number>` | `unmark 1` |
| Delete | `delete <number>` | `delete 2` |
| Find | `find <keyword>` | `find book` |
| Help | `help` | `help` |
| Exit | `bye` | `bye` |
