package storeapp;

import storeapp.gui.MainFrame;
import storeapp.model.Store;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static List<Store> stores;
    public static List<String> categories;

    public static void main(String[] args) {
        stores =  new ArrayList<>();
        categories = new ArrayList<>();

        categories.add("Food Items");
        categories.add("Electronics");
        categories.add("Clothing");
        categories.add("Home and Garden");
        categories.add("Books and Literature");
        categories.add("Health and Beauty");
        categories.add("Toys and Games");

        SwingUtilities.invokeLater(() -> new MainFrame(stores));
    }
}