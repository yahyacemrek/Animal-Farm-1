package org.example.animalfarm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private static AccountManager instance;
    private final ObservableList<Account> accountList;
    private final String JSON_FILE_PATH = "accounts.json";
    private final ObjectMapper objectMapper;

    private AccountManager() {
        accountList = FXCollections.observableArrayList();
        objectMapper = new ObjectMapper();
        loadAccountsFromJson();
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public ObservableList<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        accountList.add(account);
        saveAccountsToJson();
    }

    public void removeAccount(Account account) {
        accountList.remove(account);
        saveAccountsToJson();
    }

    private void loadAccountsFromJson() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                List<Account> accounts = objectMapper.readValue(file, new TypeReference<List<Account>>() {});
                accountList.setAll(accounts);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading accounts from JSON: " + e.getMessage());
        }
    }

    private void saveAccountsToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), new ArrayList<>(accountList));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving accounts to JSON: " + e.getMessage());
        }
    }

    public double getTotalIncome() {
        return accountList.stream()
                .filter(account -> "Income".equals(account.getType()))
                .mapToDouble(Account::getAmount)
                .sum();
    }

    public double getTotalExpenses() {
        return accountList.stream()
                .filter(account -> "Expense".equals(account.getType()))
                .mapToDouble(Account::getAmount)
                .sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpenses();
    }
} 