package org.example.animalfarm;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTaskController {

    @FXML
    private TextField taskField;
    @FXML
    private DatePicker dueField;

    @FXML
    private ComboBox<String> priorityComboBox;
    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private ComboBox<String> AssigneeComboBox;

    @FXML
    private ObservableList<Task> taskList;
    @FXML
    private Button createButton;

    private TaskController taskController; // Reference to main controller

    public void setTaskController(TaskController controller) {
        this.taskController = controller;
    }
    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }
    public void initialize() {
        // Set priority options in ComboBox
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        priorityComboBox.setValue("Medium");

        statusComboBox.getItems().addAll("Not Started","In Progress","Finished");
        statusComboBox.setValue("Not Started");

        AssigneeComboBox.getItems().addAll("Gina","Eyup","Bereket");
        AssigneeComboBox.setValue("Bereket");

    }

    @FXML
    private void handleCreateTask() {
        String taskName = taskField.getText();
        String dueDate = dueField.getValue().toString();
        String priority = priorityComboBox.getValue();
        String status = statusComboBox.getValue();
        String assignee = AssigneeComboBox.getValue();

        // new task object
        Task newTask = new Task(taskName, dueDate, priority, status, assignee);

        taskList.add(newTask);
    }
    @FXML
    private void handleClose() {
        Stage s = (Stage) taskField.getScene().getWindow();
        s.close();
    }

}
