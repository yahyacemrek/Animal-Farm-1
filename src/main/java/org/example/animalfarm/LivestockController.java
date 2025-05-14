package org.example.animalfarm;

import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

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



    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        // Hash map to store the data ;
        HashMap<String, Animal> animalMap = new HashMap<>();

        animalMap.put("B01", new Animal("B01", "Bull 01", "Male", "4 years, 7 months", 1458.0, "Active", "Cattle"));
        animalMap.put("B02", new Animal("B02", "Bull 02", "Male", "3 years, 10 months", 1482.0, "Active", "Cattle"));

        AnimalColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        LastWeightColumn.setCellValueFactory(new PropertyValueFactory<>("lastWeight"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        ObservableList<Animal> animalList = FXCollections.observableArrayList(animalMap.values());
        AnimalTableView.setItems(animalList);
    }
}
