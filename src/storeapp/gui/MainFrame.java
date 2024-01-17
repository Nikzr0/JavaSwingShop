package storeapp.gui;

import storeapp.App;
import storeapp.model.Stock;
import storeapp.model.Store;
import storeapp.tablemodel.StocksTableModel;
import storeapp.tablemodel.StoresTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    private JTextField storeNameField;
    private JButton addStoreButton;
    private JTable storesTable;
    private JTextField storeFieldName;
    private JButton removeStoreButton;
    private JComboBox<String> stockCategoryComboBox;
    private JSpinner stockQuantitySpinner;
    private JTextField stockPriceField;
    private JTextField stockNameField;
    private JTextField stockManufacturerField;
    private JButton addStockButton;
    private JButton removeStockButton;
    private JTable stocksTable;

    private StocksTableModel stocksTableModel;

    public MainFrame(List<Store> stores) {
        var storesTableModel = new StoresTableModel(stores);
        storesTable.setModel(storesTableModel);

        seedCategoriesComboBox();

        addStoreButton.addActionListener(e -> {
            String storeName = storeNameField.getText();
            String storeAddress = storeFieldName.getText();

            Store newStore = new Store(storeName, storeAddress, new ArrayList<>());

            storesTableModel.addStore(newStore);
        });

        removeStoreButton.addActionListener(e -> {
            var row = storesTable.getSelectedRow();

            var result = JOptionPane.showConfirmDialog(null, "This store will be deleted!", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
                storesTableModel.removeStoreAt(row);
        });

        storesTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedStoreIndex = storesTable.getSelectedRow();
            if (selectedStoreIndex >= 0) {
                Store selectedStore = storesTableModel.getStoreAt(selectedStoreIndex);

                List<Stock> stocks = selectedStore.getStockList();

                stocksTableModel = new StocksTableModel(stocks);

                stocksTable.setModel(stocksTableModel);
            }
        });

        addStockButton.addActionListener(e -> {
            int selectedStoreIndex = storesTable.getSelectedRow();

            if (selectedStoreIndex >= 0) {
                String stockName = stockNameField.getText();
                String stockCategory = (String) stockCategoryComboBox.getSelectedItem();
                int stockQuantity = (int) stockQuantitySpinner.getValue();
                double stockPrice = Double.parseDouble(stockPriceField.getText());
                String stockManufacturer = stockManufacturerField.getText();

                Stock newStock = new Stock(stockName, stockCategory, stockQuantity, stockPrice, stockManufacturer);

                stocksTableModel.addStock(newStock);

            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Please select a store first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeStockButton.addActionListener(e -> {
            var row = stocksTable.getSelectedRow();

            var result = JOptionPane.showConfirmDialog(null, "This stock will be deleted!", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
                stocksTableModel.removeStockAt(row);
        });


        setContentPane(contentPane);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void seedCategoriesComboBox() {
        for (var category : App.categories) {
            stockCategoryComboBox.addItem(category);
        }

        stockCategoryComboBox.setSelectedItem(null);
    }
}