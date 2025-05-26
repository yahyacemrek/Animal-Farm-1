package org.example.animalfarm;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.CheckBox;
import javafx.animation.PauseTransition;
import javafx.stage.Modality;
import javafx.util.Duration;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Parent;

public class TaskController {

    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task, Boolean> checkboxColumn;

    @FXML
    private TableColumn<Task, String> taskColumn;
    @FXML
    private TableColumn<Task, String> dueColumn;
    @FXML
    private TableColumn<Task, String> priorityColumn;
    @FXML
    private TableColumn<Task, String> statusColumn;
    @FXML
    private TableColumn<Task, String> assigneeColumn;

    // ObservableList to hold tasks
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    // Initialize the TableView and data
    @FXML
    public void initialize() {
        taskTableView.setEditable(true);
        checkboxColumn.setEditable(true);

        // Set up the columns with the data from Task class
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        assigneeColumn.setCellValueFactory(new PropertyValueFactory<>("assignee"));

        checkboxColumn.setCellValueFactory(cellData -> cellData.getValue().doneProperty());

        checkboxColumn.setCellFactory(column -> {
            return new CheckBoxTableCell<>() {
                {
                    setSelectedStateCallback(index -> {
                        if (index >= 0 && index < taskTableView.getItems().size()) {
                            Task task = taskTableView.getItems().get(index);
                            // Add listener to delete the task when checkbox is checked
                            task.doneProperty().addListener((obs, wasDone, isDone) -> {
                                if (isDone) {
                                    // Remove the task from the list
                                    // Delay before removing
                                    PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                                    delay.setOnFinished(e -> taskList.remove(task));
                                    delay.play();
                                }
                            });
                            return task.doneProperty();
                        } else {
                            return null;
                        }
                    });
                }
            };
        });


        // Populate the TableView with sample data
        loadData();
        taskTableView.setItems(taskList);
    }

    // Method to load sample task data
    private void loadData() {
        taskList.add(new Task("Water the plants", "2025-05-20", "High", "Pending", "John"));
        taskList.add(new Task("Harvest crops", "2025-06-01", "Medium", "In Progress", "Anna"));
        taskList.add(new Task("Fix irrigation", "2025-05-15", "Low", "Completed", "Mike"));
    }

    // Method to add a new task (this could be connected to an "Add Task" button)
    public void addTask(String taskName, String dueDate, String priority, String status, String assignee) {
        taskList.add(new Task(taskName, dueDate, priority, status, assignee));
    }

    //  "Delete Task" not used yet
    public void deleteTask(Task task) {
        taskList.remove(task);
    }
   // For Add Task
    public void openAddTaskPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTask.fxml"));
            Parent root = loader.load();

            AddTaskController popup = loader.getController();


            popup.setTaskList(this.taskList);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Add New Task");
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Open the Live Stock Page;
    @FXML
    private void openLivestockPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalPage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) taskTableView.getScene().getWindow();
           // FadeTransition fadeIn = new FadeTransition(Duration.millis(500), livestockRoot);
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Livestock");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Livestock.fxml: " + e.getMessage());
        }



    }
    @FXML
    private void openSchedule() {
        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Schedule.fxml"));
            Parent livestockRoot = loader1.load();

            // Get current stage
            Stage stage = (Stage) taskTableView.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Schedule");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
