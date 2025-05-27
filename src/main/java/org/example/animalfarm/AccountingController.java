package org.example.animalfarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountingController {
    @FXML
    private TableView TableId;
    
    @FXML
    private LineChart ChartId;

    @FXML
    private void openSchedule() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Schedule.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) TableId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Schedule");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void openTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Taskpage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) TableId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tasks");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openLivestockPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalPage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) TableId.getScene().getWindow();
            // FadeTransition fadeIn = new FadeTransition(Duration.millis(500), livestockRoot);
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Livestock");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Livestock.fxml: " + e.getMessage());
        }
    }
} 