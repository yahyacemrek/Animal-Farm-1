package org.example.animalfarm;

import javafx.beans.property.*;

public class Task {
    private final StringProperty taskName;
    private final StringProperty dueDate;
    private final StringProperty priority;
    private final StringProperty status;
    private final StringProperty assignee;
    private final BooleanProperty done;

    public Task(String taskName, String dueDate, String priority, String status, String assignee) {
        this.taskName = new SimpleStringProperty(taskName);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.priority = new SimpleStringProperty(priority);
        this.status = new SimpleStringProperty(status);
        this.assignee = new SimpleStringProperty(assignee);
        this.done = new SimpleBooleanProperty(false);
    }

    public String getTaskName() {
        return taskName.get();
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getAssignee() {
        return assignee.get();
    }

    public void setAssignee(String assignee) {
        this.assignee.set(assignee);
    }

    public StringProperty assigneeProperty() {
        return assignee;
    }
    public BooleanProperty doneProperty() {
        return done;
    }
    public boolean isDone() {
        return done.get();
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }
}
