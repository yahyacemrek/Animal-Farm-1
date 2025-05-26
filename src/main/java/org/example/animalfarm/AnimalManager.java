package org.example.animalfarm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class AnimalManager {
    private static AnimalManager instance;
    private final Map<String, Animal> animalMap;
    private final ObservableList<Animal> animalList;
    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "animals.json";

    private AnimalManager() {
        animalMap = new HashMap<>();
        animalList = FXCollections.observableArrayList();
        objectMapper = new ObjectMapper();
        loadAnimalsFromJson();
    }

    public static AnimalManager getInstance() {
        if (instance == null) {
            instance = new AnimalManager();
        }
        return instance;
    }

    public void addAnimal(Animal animal) {
        // Add to both map and list
        animalMap.put(animal.getId(), animal);
        animalList.add(animal);
        // Save to JSON after adding
        saveAnimalsToJson();
    }

    public void removeAnimal(String id) {
        Animal animal = animalMap.remove(id);
        if (animal != null) {
            animalList.remove(animal);
            // Save to JSON after removing
            saveAnimalsToJson();
        }
    }

    public Animal getAnimal(String id) {
        return animalMap.get(id);
    }

    public ObservableList<Animal> getAnimalList() {
        return animalList;
    }

    public Map<String, Animal> getAnimalMap() {
        return animalMap;
    }

    // Save animals to JSON file
    private void saveAnimalsToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), animalList);
        } catch (IOException e) {
            System.err.println("Error saving animals to JSON: " + e.getMessage());
        }
    }

    // Load animals from JSON file
    private void loadAnimalsFromJson() {
        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            try {
                List<Animal> loadedAnimals = objectMapper.readValue(jsonFile, 
                    new TypeReference<List<Animal>>() {});
                
                // Clear existing data
                animalMap.clear();
                animalList.clear();
                
                // Add loaded animals to both map and list
                for (Animal animal : loadedAnimals) {
                    animalMap.put(animal.getId(), animal);
                    animalList.add(animal);
                }
            } catch (IOException e) {
                System.err.println("Error loading animals from JSON: " + e.getMessage());
                // Load some default animals if JSON loading fails
                loadDefaultAnimals();
            }
        } else {
            // Load default animals if JSON file doesn't exist
            loadDefaultAnimals();
        }
    }

    // Load some default animals for first-time use
    private void loadDefaultAnimals() {
        addAnimal(new Animal("A01", "Bella", "Female", "2 years, 5 months", 430.0, "Healthy", 
            "Cow", "COW123", "Barn A", "Brown", "1.3m", 1.3, "2022-01-15", 
            "12-13-2002", "Toro", "0", "calf"));
        addAnimal(new Animal("A02", "Max", "Male", "3 years, 1 month", 520.5, "Healthy", 
            "Bull", "BULL456", "Barn B", "Black", "1.5m", 1.5, "2025-01-15", 
            "12-12-2001", "Zeus", "0", "calf2"));
        addAnimal(new Animal("A03", "Daisy", "Female", "1 year, 9 months", 390.2, "Sick", 
            "Cow", "COW789", "Barn C", "White", "1.2m", 1.8, "2023-01-15", 
            "12-12-2002", "Bubu", "0", "calf3"));
    }

    public void updateAnimal(Animal animal) {
        if (animalMap.containsKey(animal.getId())) {
            animalMap.put(animal.getId(), animal);
            // Update in the observable list
            int index = -1;
            for (int i = 0; i < animalList.size(); i++) {
                if (animalList.get(i).getId().equals(animal.getId())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                animalList.set(index, animal);
                // Save to JSON after updating
                saveAnimalsToJson();
            }
        }
    }

    public Animal searchById(String id) {
        return animalMap.get(id);
    }

    public ObservableList<Animal> searchByType(String type) {
        ObservableList<Animal> result = FXCollections.observableArrayList();
        for (Animal animal : animalMap.values()) {
            if (animal.getType().equalsIgnoreCase(type)) {
                result.add(animal);
            }
        }
        return result;
    }

    public boolean containsAnimal(String id) {
        return animalMap.containsKey(id);
    }

    public int getTotalAnimals() {
        return animalMap.size();
    }

    public void clearAll() {
        animalMap.clear();
        animalList.clear();
        saveAnimalsToJson();
    }
} 