package org.example.animalfarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AnimalDetailsController {

    @FXML private Label nameLabel;
    @FXML private Label genderLabel;
    @FXML private Label ageLabel;
    @FXML private Label weightLabel;
    @FXML private Label statusLabel;
    @FXML private Label breedLabel;
    @FXML private Label  TypeLabel;
    @FXML private Label TagLabel;
    @FXML private Label LocationLabel;
    @FXML private Label colorLabel;
    @FXML private Label HeightLabel;
    @FXML private Label BirthdayLabel;
    @FXML private Label MaternityLabel;
    @FXML private Label PaternityLabel;
    @FXML private Label OffspringLabel;




    public void setAnimal(Animal animal) {
        nameLabel.setText("Name: " + animal.getName());
        genderLabel.setText("Gender: " + animal.getGender());
        ageLabel.setText("Age: " + animal.getAge());
        weightLabel.setText("Weight: " + animal.getLastWeight() + " kg");
        statusLabel.setText("Status: " + animal.getStatus());
        breedLabel.setText("Breed: " + animal.getBreed());
        TypeLabel.setText("Type:" + animal.getBreed());
        TagLabel.setText("Tag:" + animal.getTag());
        LocationLabel.setText("Location" + animal.getLocation());
        colorLabel.setText("Color" + animal.getColor());
        HeightLabel.setText("Height" + animal.getHeight());
        BirthdayLabel.setText("Birthday" + animal.getBirthdate());
        MaternityLabel.setText("Maternity" + animal.getMaternity());
        PaternityLabel.setText("Paternity" + animal.getPaternity());
        OffspringLabel.setText("Offspring" + animal.getOffspring());

    }
    @FXML
    private void openLivestockPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalPage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Livestock");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
