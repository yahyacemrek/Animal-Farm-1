package org.example.animalfarm;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LivestockController {

    @FXML
    public TableView<Animal> AnimalTableView;
    @FXML
    public TableColumn<Animal,String> AnimalColumn;
    @FXML
    public TableColumn<Animal,String> GenderColumn;
    @FXML
    public TableColumn<Animal,String> AgeColumn;
    @FXML
    public TableColumn<Animal,String> LastWeightColumn;
    @FXML
    public TableColumn<Animal,String> TypeColumn;
    @FXML  private Label nameLabel;

    private ObservableList<Animal> animalList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        //let's test try the List


        // Observable list of animals

        System.out.println("Initializing with " + animalList.size() + " animals");
        // Hash map to store the data ;
        HashMap<String, Animal> animalMap = new HashMap<>();

        //animalMap.put("B01", new Animal("B01", "Bull 01", "Male", "4 years, 7 months", 1458.0, "Active", "Cattle"));
        //animalMap.put("B02", new Animal("B02", "Bull 02", "Male", "3 years, 10 months", 1482.0, "Active", "Cattle"));
        loadAnimalData();
        AnimalColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        LastWeightColumn.setCellValueFactory(new PropertyValueFactory<>("lastWeight"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
     //   animalList = FXCollections.observableArrayList(animalMap.values());
        AnimalTableView.setItems(animalList);

        // on click it should open the animal detail page
        AnimalTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                Animal selectedAnimal = AnimalTableView.getSelectionModel().getSelectedItem();
                if (selectedAnimal != null) {
                    openAnimalDetails(selectedAnimal);
                }
            }
        });


    }
    private void openAnimalDetails(Animal animal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalDetails.fxml"));
            Parent root = loader.load();

            AnimalDetailsController controller = loader.getController();
            controller.setAnimal(animal);  // Pass the selected animal

            Stage stage = new Stage();
            stage.setTitle("Animal Details");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadAnimalData() {
       animalList.add(new Animal("A01", "Bella", "Female", "2 years, 5 months", 430.0, "Healthy", "Cow", "COW123", "Barn A", "Brown", "1.3m", 1.3, "2022-01-15","12-13-2002", "Toro", "0","calf"));
       animalList.add(new Animal("A02", "Max", "Male", "3 years, 1 month", 520.5, "Healthy", "Bull", "BULL456", "Barn B", "Black", "1.5m", 1.5, "2025-01-15", "12-12-2001","Zeus", "0","calf2"));
       animalList.add(new Animal("A03", "Daisy", "Female", "1 year, 9 months", 390.2, "Sick", "Cow", "COW789", "Barn C", "White", "1.2m", 1.8, "2023-01-15", "12-12-2002", "Bubu","0","calf3"));

    }
    @FXML
    private void openAddAnimalPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAnimal.fxml"));
            Parent root = loader.load();

            AddAnimalController controller = loader.getController();
            controller.setAnimalList(animalList); // Inject the shared list

            Stage stage = new Stage();
            stage.setTitle("Add New Animal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Taskpage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) AnimalTableView.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Livestock");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
