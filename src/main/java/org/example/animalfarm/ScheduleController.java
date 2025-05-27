package org.example.animalfarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;

import java.io.IOException;

public class ScheduleController {
    @FXML
    private Button TaskButton;
    
    @FXML
    private VBox urgentTasksBox;
    
    @FXML
    private ListView<String> urgentTasksList;

    private TaskController taskController;

    @FXML
    public void initialize() {
        // Create a new TaskController instance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Taskpage.fxml"));
        try {
            loader.load();
            taskController = loader.getController();
            
            // Initialize the urgent tasks list
            updateUrgentTasksList();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateUrgentTasksList() {
        if (taskController != null) {
            ObservableList<Task> urgentTasks = taskController.getUrgentTasks();
            urgentTasksList.getItems().clear();
            
            for (Task task : urgentTasks) {
                String taskDisplay = String.format("%s - Due: %s (%s)", 
                    task.getTaskName(), 
                    task.getDueDate(),
                    task.getPriority());
                urgentTasksList.getItems().add(taskDisplay);
            }
        }
    }

    @FXML
    private void openLivestockPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalPage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) TaskButton.getScene().getWindow();
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
    private void openTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Taskpage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) TaskButton.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Livestock");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openAccounting() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accounting.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) TaskButton.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Accounting");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
