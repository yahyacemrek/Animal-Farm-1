package org.example.animalfarm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private final StringProperty date;
    private final StringProperty description;
    private final DoubleProperty amount;
    private final StringProperty type; // "Income" or "Expense"

    // No-args constructor for Jackson
    public Account() {
        this.date = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.amount = new SimpleDoubleProperty(0.0);
        this.type = new SimpleStringProperty("");
    }

    @JsonCreator
    public Account(
            @JsonProperty("date") String date,
            @JsonProperty("description") String description,
            @JsonProperty("amount") double amount,
            @JsonProperty("type") String type) {
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
        this.amount = new SimpleDoubleProperty(amount);
        this.type = new SimpleStringProperty(type);
    }

    @JsonProperty("date")
    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    @JsonProperty("type")
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }
} 