package storeapp.model;

import javax.swing.*;

public class Stock {
    private String name;
    private String category;
    private int quantity;
    private double price;
    private String manufacturer;

    public Stock(String name, String category, int quantity, double price, String manufacturer) {
        try {
            setName(name);
            setQuantity(quantity);
            setPrice(price);
        } catch (IllegalArgumentException e) {
            // Display error message in a pop-up window
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.category = category;
        setManufacturer(manufacturer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() >= 3 && name.length() <= 50) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name should be between 3 and 50 characters long");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 1 && quantity <= 1000) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity should be between 1 and 1000");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0 && price <= 1000) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price should be between 0 and 1,000");
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
