package org.example.animalfarm;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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

    @FXML
    private ObservableList<Animal> animalList;

    public void setAnimalList(ObservableList<Animal> animalList) {
        this.animalList = animalList;
    }

    @FXML
    private void handleSaveAnimal(){
        String name = nameField.getText();
        String age = ageField.getText();
        String sex = sexField.getText();
        String type = typeField.getText();
        String breed = breedField.getText();
        String color = colorField.getText();
        String id = idField.getText();
        String birthday = birthdayField.getText();
        String lastchecked = lastweightField.getText();
        String location = locationField.getText();
        String tag = tagField.getText();
        String status = statusField.getText();
        Double lastweight = Double.parseDouble(lastweightField.getText());
        Double height = Double.parseDouble(heightField.getText());
        String maternity = maternityField.getText();
        String paternity = paternityField.getText();
        String offspring = offspringField.getText();
        String lastmeasured = "15-5-2025";
        //       animalList.add(new Animal("A01", "Bella", "Female", "2 years, 5 months", 430.0, "Healthy", "Cow", "COW123", "Barn A", "Brown", "1.3m", 1.3, "2022-01-15","12-13-2002", "Toro", "0","calf"));
        Animal newAnimal = new Animal(name,id,sex,age,lastweight,status,breed,type,tag,location,color,height,lastmeasured,birthday,paternity,maternity,offspring);
        animalList.add(newAnimal);
    }

}
