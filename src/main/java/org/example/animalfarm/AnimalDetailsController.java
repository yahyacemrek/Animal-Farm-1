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
        nameLabel.setText(animal.getName());
        genderLabel.setText( animal.getGender());
        ageLabel.setText(animal.getAge());
        weightLabel.setText(animal.getLastWeight() + " kg");
        statusLabel.setText( animal.getStatus());
        breedLabel.setText(animal.getBreed());
        TypeLabel.setText(animal.getBreed());
        TagLabel.setText(animal.getTag());
        LocationLabel.setText(animal.getLocation());
        colorLabel.setText( animal.getColor());
        HeightLabel.setText("" + animal.getHeight());
        BirthdayLabel.setText(animal.getBirthdate());
        MaternityLabel.setText( animal.getMaternity());
        PaternityLabel.setText( animal.getPaternity());
        OffspringLabel.setText(animal.getOffspring());

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
