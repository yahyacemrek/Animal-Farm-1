package org.example.animalfarm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

public class Task {
    private final StringProperty taskName;
    private final StringProperty dueDate;
    private final StringProperty priority;
    private final StringProperty status;
    private final StringProperty assignee;
    private final BooleanProperty done;

    // No-args constructor for Jackson
    public Task() {
        this.taskName = new SimpleStringProperty("");
        this.dueDate = new SimpleStringProperty("");
        this.priority = new SimpleStringProperty("");
        this.status = new SimpleStringProperty("");
        this.assignee = new SimpleStringProperty("");
        this.done = new SimpleBooleanProperty(false);
    }

    @JsonCreator
    public Task(
        @JsonProperty("taskName") String taskName,
        @JsonProperty("dueDate") String dueDate,
        @JsonProperty("priority") String priority,
        @JsonProperty("status") String status,
        @JsonProperty("assignee") String assignee) {
        this.taskName = new SimpleStringProperty(taskName);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.priority = new SimpleStringProperty(priority);
        this.status = new SimpleStringProperty(status);
        this.assignee = new SimpleStringProperty(assignee);
        this.done = new SimpleBooleanProperty(false);
    }

    @JsonProperty("taskName")
    public String getTaskName() {
        return taskName.get();
    }

    @JsonProperty("taskName")
    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    @JsonIgnore
    public StringProperty taskNameProperty() {
        return taskName;
    }

    @JsonProperty("dueDate")
    public String getDueDate() {
        return dueDate.get();
    }

    @JsonProperty("dueDate")
    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    @JsonIgnore
    public StringProperty dueDateProperty() {
        return dueDate;
    }

    @JsonProperty("priority")
    public String getPriority() {
        return priority.get();
    }

    @JsonProperty("priority")
    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    @JsonIgnore
    public StringProperty priorityProperty() {
        return priority;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status.get();
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status.set(status);
    }

    @JsonIgnore
    public StringProperty statusProperty() {
        return status;
    }

    @JsonProperty("assignee")
    public String getAssignee() {
        return assignee.get();
    }

    @JsonProperty("assignee")
    public void setAssignee(String assignee) {
        this.assignee.set(assignee);
    }

    @JsonIgnore
    public StringProperty assigneeProperty() {
        return assignee;
    }

    @JsonProperty("done")
    public boolean isDone() {
        return done.get();
    }

    @JsonProperty("done")
    public void setDone(boolean done) {
        this.done.set(done);
    }

    @JsonIgnore
    public BooleanProperty doneProperty() {
        return done;
    }

    // Method to check if task is due today
    @JsonIgnore
    public boolean isDueToday() {
        String today = java.time.LocalDate.now().toString();
        return getDueDate().equals(today);
    }

    // Method to check if task is urgent (high priority and due within next 3 days)
    @JsonIgnore
    public boolean isUrgent() {
        if (getPriority().equalsIgnoreCase("High")) {
            java.time.LocalDate dueDate = java.time.LocalDate.parse(getDueDate());
            java.time.LocalDate today = java.time.LocalDate.now();
            long daysUntilDue = java.time.temporal.ChronoUnit.DAYS.between(today, dueDate);
            return daysUntilDue >= 0 && daysUntilDue <= 3;
        }
        return false;
    }
}
