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

    // Use TaskManager instead of local ObservableList
    private final TaskManager taskManager = TaskManager.getInstance();

    // Method to get urgent tasks
    public ObservableList<Task> getUrgentTasks() {
        ObservableList<Task> urgentTasks = FXCollections.observableArrayList();
        for (Task task : taskManager.getTaskList()) {
            if (!task.isDone() && (task.isUrgent() || task.isDueToday())) {
                urgentTasks.add(task);
            }
        }
        return urgentTasks;
    }

    // Method to get tasks due today
    public ObservableList<Task> getTodaysTasks() {
        ObservableList<Task> todaysTasks = FXCollections.observableArrayList();
        for (Task task : taskManager.getTaskList()) {
            if (!task.isDone() && task.isDueToday()) {
                todaysTasks.add(task);
            }
        }
        return todaysTasks;
    }

    // Initialize the TableView and data
    @FXML
    public void initialize() {
        System.out.println("Initializing TaskController...");
        
        taskTableView.setEditable(true);
        checkboxColumn.setEditable(true);

        // Set up the columns with the data from Task class
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        assigneeColumn.setCellValueFactory(new PropertyValueFactory<>("assignee"));
        checkboxColumn.setCellValueFactory(cellData -> cellData.getValue().doneProperty());

        // Set up checkbox column
        checkboxColumn.setCellFactory(column -> new CheckBoxTableCell<>() {
            {
                setSelectedStateCallback(index -> {
                    if (index >= 0 && index < taskTableView.getItems().size()) {
                        Task task = taskTableView.getItems().get(index);
                        task.doneProperty().addListener((obs, wasDone, isDone) -> {
                            if (isDone) {
                                PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                                delay.setOnFinished(e -> taskManager.removeTask(task));
                                delay.play();
                            }
                        });
                        return task.doneProperty();
                    }
                    return null;
                });
            }
        });

        // Bind the TableView directly to TaskManager's observable list
        taskTableView.setItems(taskManager.getTaskList());
        
        // Print current tasks for debugging
        System.out.println("Current tasks in TableView: " + taskTableView.getItems().size());
        for (Task task : taskTableView.getItems()) {
            System.out.println("- " + task.getTaskName() + " (Due: " + task.getDueDate() + ")");
        }
    }

    // Method to add a new task
    public void addTask(String taskName, String dueDate, String priority, String status, String assignee) {
        Task newTask = new Task(taskName, dueDate, priority, status, assignee);
        taskManager.addTask(newTask);
        
        // Print tasks after adding for debugging
        System.out.println("Tasks after adding new task:");
        for (Task task : taskTableView.getItems()) {
            System.out.println("- " + task.getTaskName() + " (Due: " + task.getDueDate() + ")");
        }
    }

    // Delete task method
    public void deleteTask(Task task) {
        taskManager.removeTask(task);
    }

    // For Add Task
    public void openAddTaskPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTask.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Add New Task");
            popupStage.showAndWait();
            
            // Print tasks after popup closes for debugging
            System.out.println("Tasks after popup closes:");
            for (Task task : taskTableView.getItems()) {
                System.out.println("- " + task.getTaskName() + " (Due: " + task.getDueDate() + ")");
            }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Schedule.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) taskTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Schedule");
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
            Stage stage = (Stage) taskTableView.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Accounting");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
