package org.example.animalfarm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class TaskManager {
    private static TaskManager instance;
    private final ObservableList<Task> taskList;
    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "tasks.json";
    private final File jsonFile;

    private TaskManager() {
        taskList = FXCollections.observableArrayList();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        String currentDir = System.getProperty("user.dir");
        jsonFile = Paths.get(currentDir, JSON_FILE_PATH).toFile();
        System.out.println("JSON file path: " + jsonFile.getAbsolutePath());
        
        loadTasksFromJson();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Adding task: " + task.getTaskName());
        System.out.println("Current task list size: " + taskList.size());
        saveTasksToJson();
    }

    public void removeTask(Task task) {
        taskList.remove(task);
        System.out.println("Removing task: " + task.getTaskName());
        System.out.println("Current task list size: " + taskList.size());
        saveTasksToJson();
    }

    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    // Save tasks to JSON file with better error handling
    private void saveTasksToJson() {
        System.out.println("\n=== Saving Tasks to JSON ===");
        try {
            // Configure ObjectMapper for better output
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            
            System.out.println("Preparing to save " + taskList.size() + " tasks");
            
            // First convert to string to verify JSON format
            String jsonContent = objectMapper.writeValueAsString(taskList);
            System.out.println("Generated JSON content:");
            System.out.println(jsonContent);
            
            // Then write to file
            objectMapper.writeValue(jsonFile, taskList);
            
            // Verify the file was written
            String savedContent = new String(java.nio.file.Files.readAllBytes(jsonFile.toPath()));
            System.out.println("File saved successfully. Content:");
            System.out.println(savedContent);
            System.out.println("File size: " + jsonFile.length() + " bytes");
        } catch (IOException e) {
            System.err.println("Error saving tasks to JSON: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=== Finished Saving Tasks ===\n");
    }

    // Load tasks from JSON file
    private void loadTasksFromJson() {
        System.out.println("\n=== Loading Tasks from JSON ===");
        System.out.println("JSON file path: " + jsonFile.getAbsolutePath());
        
        if (!jsonFile.exists()) {
            System.out.println("JSON file does not exist yet - starting with empty task list");
            return;
        }

        System.out.println("JSON file exists, size: " + jsonFile.length() + " bytes");
        
        try {
            String jsonContent = new String(java.nio.file.Files.readAllBytes(jsonFile.toPath()));
            System.out.println("JSON Content:");
            System.out.println(jsonContent);
            
            if (jsonContent.trim().isEmpty()) {
                System.out.println("JSON file is empty - starting with empty task list");
                return;
            }

            try {
                // Configure ObjectMapper for detailed error messages
                objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                
                // Try to parse as a single task first
                try {
                    Task singleTask = objectMapper.readValue(jsonFile, Task.class);
                    System.out.println("Successfully parsed single task");
                    taskList.clear();
                    taskList.add(singleTask);
                    return;
                } catch (Exception e) {
                    System.out.println("Not a single task, trying as list...");
                }

                // Try to parse as a list of tasks
                List<Task> loadedTasks = objectMapper.readValue(jsonFile, 
                    new TypeReference<List<Task>>() {});
                
                System.out.println("Successfully parsed JSON file as list");
                System.out.println("Number of tasks loaded: " + (loadedTasks != null ? loadedTasks.size() : 0));
                
                if (loadedTasks != null && !loadedTasks.isEmpty()) {
                    taskList.clear();
                    taskList.addAll(loadedTasks);
                    System.out.println("Successfully loaded tasks into taskList");
                    System.out.println("Current taskList size: " + taskList.size());
                    
                    System.out.println("\nLoaded Tasks:");
                    for (Task task : taskList) {
                        System.out.println("- " + task.getTaskName() + " (Due: " + task.getDueDate() + ")");
                    }
                } else {
                    System.out.println("No tasks found in JSON file");
                }
            } catch (Exception e) {
                System.err.println("Error parsing JSON content: " + e.getMessage());
                System.err.println("JSON content that failed to parse:");
                System.err.println(jsonContent);
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=== Finished Loading Tasks ===\n");
    }
} 