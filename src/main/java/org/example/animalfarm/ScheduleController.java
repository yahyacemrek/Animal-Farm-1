package org.example.animalfarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleController {
    @FXML
    private Button TaskButton;


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

}
