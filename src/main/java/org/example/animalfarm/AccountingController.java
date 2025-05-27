package org.example.animalfarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AccountingController implements Initializable {
    @FXML
    private TableView<Account> TableId;
    
    @FXML
    private LineChart<String, Number> ChartId;

    @FXML
    private Button TransactionButton;

    @FXML
    private TableColumn<Account, String> dateColumn;

    @FXML
    private TableColumn<Account, String> descriptionColumn;

    @FXML
    private TableColumn<Account, Double> amountColumn;

    @FXML
    private TableColumn<Account, String> typeColumn;

    private AccountManager accountManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountManager = AccountManager.getInstance();
        
        // Initialize columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Set table items
        TableId.setItems(accountManager.getAccountList());

        // Add transaction button handler
        TransactionButton.setOnAction(event -> showAddTransactionDialog());

        // Initialize chart
        ChartId.setTitle("Income and Expenses Over Time");
        updateChart();

        // Add listener to account list to update chart when data changes
        accountManager.getAccountList().addListener((javafx.collections.ListChangeListener.Change<? extends Account> c) -> {
            updateChart();
        });
    }

    private void updateChart() {
        ChartId.getData().clear();

        // Create series for income and expenses
        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");
        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        expenseSeries.setName("Expenses");
        XYChart.Series<String, Number> balanceSeries = new XYChart.Series<>();
        balanceSeries.setName("Balance");

        // Get all transactions sorted by date
        List<Account> sortedTransactions = accountManager.getAccountList().stream()
                .sorted(Comparator.comparing(Account::getDate))
                .collect(Collectors.toList());

        double runningBalance = 0.0;
        Map<String, Double> incomeByDate = new HashMap<>();
        Map<String, Double> expensesByDate = new HashMap<>();
        Map<String, Double> balanceByDate = new HashMap<>();

        // Aggregate transactions by date
        for (Account transaction : sortedTransactions) {
            String date = transaction.getDate();
            double amount = transaction.getAmount();

            if ("Income".equals(transaction.getType())) {
                incomeByDate.merge(date, amount, Double::sum);
                runningBalance += amount;
            } else {
                expensesByDate.merge(date, amount, Double::sum);
                runningBalance -= amount;
            }
            balanceByDate.put(date, runningBalance);
        }

        // Add data points to series
        for (String date : balanceByDate.keySet()) {
            if (incomeByDate.containsKey(date)) {
                incomeSeries.getData().add(new XYChart.Data<>(date, incomeByDate.get(date)));
            }
            if (expensesByDate.containsKey(date)) {
                expenseSeries.getData().add(new XYChart.Data<>(date, expensesByDate.get(date)));
            }
            balanceSeries.getData().add(new XYChart.Data<>(date, balanceByDate.get(date)));
        }

        // Add series to chart
        ChartId.getData().addAll(incomeSeries, expenseSeries, balanceSeries);
    }

    private void showAddTransactionDialog() {
        // Create the custom dialog
        Dialog<Account> dialog = new Dialog<>();
        dialog.setTitle("Add Transaction");
        dialog.setHeaderText("Enter transaction details");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the form fields
        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField descriptionField = new TextField();
        TextField amountField = new TextField();
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Income", "Expense");
        typeComboBox.setValue("Income");

        // Create and populate the grid
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        grid.add(new Label("Date:"), 0, 0);
        grid.add(datePicker, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Amount:"), 0, 2);
        grid.add(amountField, 1, 2);
        grid.add(new Label("Type:"), 0, 3);
        grid.add(typeComboBox, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to Account object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    String date = datePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
                    String description = descriptionField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    String type = typeComboBox.getValue();

                    Account account = new Account(date, description, amount, type);
                    accountManager.addAccount(account);
                    return account;
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please enter a valid number for amount.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    @FXML
    private void openSchedule() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Schedule.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) TableId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Schedule");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Taskpage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) TableId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tasks");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openLivestockPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimalPage.fxml"));
            Parent livestockRoot = loader.load();

            // Get current stage
            Stage stage = (Stage) TableId.getScene().getWindow();
            stage.setScene(new Scene(livestockRoot));
            stage.setTitle("Livestock");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Livestock.fxml: " + e.getMessage());
        }
    }

} 