package org.example.animalfarm;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

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
    public TableColumn<Animal,String> IdColumn;
    @FXML
    public TableColumn<Animal,String> BreedColumn;
    @FXML
    public TableColumn<Animal,String> StatusColumn;
    @FXML  private Label nameLabel;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchCriteria;

    private ObservableList<Animal> animalList = FXCollections.observableArrayList();
    private final AnimalManager animalManager = AnimalManager.getInstance();
    // Additional HashMaps for efficient searching
    private final Map<String, Set<Animal>> typeIndex = new HashMap<>();
    private final Map<String, Set<Animal>> breedIndex = new HashMap<>();
    private final Map<String, Set<Animal>> statusIndex = new HashMap<>();

    @FXML
    public void initialize() {
        // Initialize table columns
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AnimalColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        LastWeightColumn.setCellValueFactory(new PropertyValueFactory<>("lastWeight"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        BreedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set up search criteria combo box
        searchCriteria.setItems(FXCollections.observableArrayList(
            "ID", "Name", "Type", "Breed", "Status", "All"
        ));
        searchCriteria.setValue("All");

        // Bind table to animal list
        AnimalTableView.setItems(animalManager.getAnimalList());

        // Add search field listener
        searchField.setOnKeyReleased(this::handleSearch);

        // Initialize indexes
        updateSearchIndexes();

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

    private void updateSearchIndexes() {
        // Clear existing indexes
        typeIndex.clear();
        breedIndex.clear();
        statusIndex.clear();

        // Rebuild indexes
        for (Animal animal : animalManager.getAnimalList()) {
            // Index by type
            typeIndex.computeIfAbsent(animal.getType().toLowerCase(), k -> new HashSet<>()).add(animal);
            
            // Index by breed
            breedIndex.computeIfAbsent(animal.getBreed().toLowerCase(), k -> new HashSet<>()).add(animal);
            
            // Index by status
            statusIndex.computeIfAbsent(animal.getStatus().toLowerCase(), k -> new HashSet<>()).add(animal);
        }
    }

    @FXML
    private void handleSearch(KeyEvent event) {
        String searchText = searchField.getText().toLowerCase().trim();
        String criteria = searchCriteria.getValue();
        
        if (searchText.isEmpty()) {
            AnimalTableView.setItems(animalManager.getAnimalList());
            return;
        }

        Set<Animal> results = new HashSet<>();
        
        switch (criteria) {
            case "ID":
                // Changed to partial match for ID
                animalManager.getAnimalList().forEach(a -> {
                    if (a.getId().toLowerCase().contains(searchText)) {
                        results.add(a);
                    }
                });
                break;
                
            case "Name":
                animalManager.getAnimalList().forEach(a -> {
                    if (a.getName().toLowerCase().contains(searchText)) {
                        results.add(a);
                    }
                });
                break;
                
            case "Type":
                // Changed to partial match for Type
                animalManager.getAnimalList().forEach(a -> {
                    if (a.getType().toLowerCase().contains(searchText)) {
                        results.add(a);
                    }
                });
                break;
                
            case "Breed":
                // Changed to partial match for Breed
                animalManager.getAnimalList().forEach(a -> {
                    if (a.getBreed().toLowerCase().contains(searchText)) {
                        results.add(a);
                    }
                });
                break;
                
            case "Status":
                // Changed to partial match for Status
                animalManager.getAnimalList().forEach(a -> {
                    if (a.getStatus().toLowerCase().contains(searchText)) {
                        results.add(a);
                    }
                });
                break;
                
            case "All":
                // Search all fields
                animalManager.getAnimalList().forEach(a -> {
                    if (a.getId().toLowerCase().contains(searchText) ||
                        a.getName().toLowerCase().contains(searchText) ||
                        a.getType().toLowerCase().contains(searchText) ||
                        a.getBreed().toLowerCase().contains(searchText) ||
                        a.getStatus().toLowerCase().contains(searchText)) {
                        results.add(a);
                    }
                });
                break;
        }
        
        // If no results found, show empty list instead of clearing the table
        ObservableList<Animal> observableResults = FXCollections.observableArrayList(results);
        AnimalTableView.setItems(observableResults);
        
        // Optional: Add this to show total results found
        System.out.println("Search criteria: " + criteria + ", Query: " + searchText + ", Results found: " + results.size());
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

    @FXML
    private void openAddAnimalPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAnimal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Animal");
            stage.setScene(new Scene(root));
            
            // Show the dialog and wait for it to close
            stage.showAndWait();
            
            // After dialog closes, update the search indexes
            updateSearchIndexes();
            
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Could not open Add Animal window: " + e.getMessage());
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
    @FXML
    private void openSchedule() {
        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Schedule.fxml"));
            Parent livestockRoot = loader1.load();

            // Get current stage
            Stage stage = (Stage) AnimalTableView.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Schedule");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
