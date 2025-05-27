package org.example.animalfarm;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddAnimalController {
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField sexField;
    @FXML private TextField typeField;
    @FXML private TextField breedField;
    @FXML private TextField colorField;
    @FXML private TextField idField;
    @FXML private TextField birthdayField;
    @FXML private TextField lastcheckedField;
    @FXML private TextField lastweightField;
    @FXML private TextField locationField;
    @FXML private TextField tagField;
    @FXML private TextField statusField;
    @FXML private TextField heightField;
    @FXML private TextField paternityField;
    @FXML private TextField maternityField;
    @FXML private TextField offspringField;
    @FXML private Button save;
    @FXML private Button close;

    private final AnimalManager animalManager = AnimalManager.getInstance();

    @FXML
    private void initialize() {
        // Add close button handler
        close.setOnAction(event -> {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    private void handleSaveAnimal() {
        try {
            // Validate required fields
            if (nameField.getText().isEmpty() || idField.getText().isEmpty()) {
                showError("Required Fields", "Name and ID are required fields.");
                return;
            }

            String id = idField.getText();
            
            // Check if animal with this ID already exists
            if (animalManager.containsAnimal(id)) {
                showError("Duplicate ID", "An animal with this ID already exists.");
                return;
            }

            // Get all the text values
        String name = nameField.getText();
        String age = ageField.getText();
        String sex = sexField.getText();
        String type = typeField.getText();
        String breed = breedField.getText();
        String color = colorField.getText();
        String birthday = birthdayField.getText();
        String lastchecked = lastweightField.getText();
        String location = locationField.getText();
        String tag = tagField.getText();
        String status = statusField.getText();
            
            // Parse numeric values with error handling
            Double lastweight = 0.0;
            Double height = 0.0;
            try {
                lastweight = Double.parseDouble(lastweightField.getText());
                height = Double.parseDouble(heightField.getText());
            } catch (NumberFormatException e) {
                showError("Invalid Input", "Last weight and height must be valid numbers.");
                return;
            }

        String maternity = maternityField.getText();
        String paternity = paternityField.getText();
        String offspring = offspringField.getText();
        String lastmeasured = "15-5-2025";

            // Create and add the new animal using HashMap
            Animal newAnimal = new Animal(name, id, sex, age, lastweight, status, breed, type, tag, location, color, height, lastmeasured, birthday, paternity, maternity, offspring);
            animalManager.addAnimal(newAnimal);

            // Close the window after successful save
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            showError("Error", "An error occurred while saving: " + e.getMessage());
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
