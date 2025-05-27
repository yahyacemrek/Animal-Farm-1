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
    private Button createButton;

    private final TaskManager taskManager = TaskManager.getInstance();

    @FXML
    public void initialize() {
        // Set priority options in ComboBox
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        priorityComboBox.setValue("Medium");

        statusComboBox.getItems().addAll("Not Started", "In Progress", "Finished");
        statusComboBox.setValue("Not Started");

        AssigneeComboBox.getItems().addAll("Gina", "Eyup", "Bereket");
        AssigneeComboBox.setValue("Bereket");
    }

    @FXML
    private void handleCreateTask() {
        String taskName = taskField.getText();
        String dueDate = dueField.getValue().toString();
        String priority = priorityComboBox.getValue();
        String status = statusComboBox.getValue();
        String assignee = AssigneeComboBox.getValue();

        // Create new task and add it to TaskManager
        Task newTask = new Task(taskName, dueDate, priority, status, assignee);
        taskManager.addTask(newTask);

        // Close the window
        handleClose();
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) taskField.getScene().getWindow();
        stage.close();
    }
}
