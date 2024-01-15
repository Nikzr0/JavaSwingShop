package storeapp.gui;

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
    private JComboBox stockCategoryComboBox;
    private JSpinner stockQuantitySpinner;
    private JTextField stockPriceField;
    private JTextField stockNameField;
    private JTextField stockManufacturerField;
    private JButton addStockButton;
    private JButton removeStockButton;
    private JTable stocksTable;

    public MainFrame(List<Store> stores) {
        var storesTableModel = new StoresTableModel(stores);

        storesTable.setModel(storesTableModel);

        addStoreButton.addActionListener(e -> {
            String storeName = storeNameField.getText();
            String storeAddress = storeFieldName.getText();

            Store newStore = new Store(storeName, storeAddress, new ArrayList<>());

            storesTableModel.addStore(newStore);
        });

        stocksTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedStoreIndex = storesTable.getSelectedRow();
            if (selectedStoreIndex >= 0) {
                Store selectedStore = storesTableModel.getStoreAt(selectedStoreIndex);

                List<Stock> stocks = selectedStore.getStockList();

                var stocksTableModel = new StocksTableModel(stocks);

                stocksTable.setModel(stocksTableModel);
            }
        });

        removeStoreButton.addActionListener(e -> {
            var row = storesTable.getSelectedRow();
            storesTableModel.removeStoreAt(row);
        });



        addStockButton.addActionListener(e -> {
            int selectedStoreIndex = storesTable.getSelectedRow();

            if (selectedStoreIndex >= 0) {
                Store selectedStore = storesTableModel.getStoreAt(selectedStoreIndex);

                String stockName = stockNameField.getText();
                String stockCategory = (String) stockCategoryComboBox.getSelectedItem();
                int stockQuantity = (int) stockQuantitySpinner.getValue();
                double stockPrice = Double.parseDouble(stockPriceField.getText());
                String stockManufacturer = stockManufacturerField.getText();

                Stock newStock = new Stock(stockName, stockCategory, stockQuantity, stockPrice, stockManufacturer);

                StocksTableModel stocksTableModel = (StocksTableModel) stocksTable.getModel();
                stocksTableModel.addStock(newStock);

            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Please select a store first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}