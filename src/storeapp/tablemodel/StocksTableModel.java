package storeapp.tablemodel;

import storeapp.model.Stock;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StocksTableModel extends AbstractTableModel {
    private final List<Stock> stocks;
    private final String[] columnNames = {"Name", "Category", "Quantity", "Price", "Manufacturer"};

    public StocksTableModel(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public int getRowCount() {
        return stocks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stock stock = stocks.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stock.getName();
            case 1:
                return stock.getCategory();
            case 2:
                return stock.getQuantity();
            case 3:
                return stock.getPrice();
            case 4:
                return stock.getManufacturer();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Stock stock = stocks.get(rowIndex);

        switch (columnIndex) {
            case 0 ->
                stock.setName((String)aValue);
            case 1 ->
                stock.setCategory((String)aValue);
            case 2 ->
                stock.setQuantity((Integer)aValue);
            case 3 ->
                stock.setPrice(Double.parseDouble((String)aValue));
            case 4 ->
                stock.setManufacturer((String)aValue);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 2 -> Integer.class;
            default -> String.class;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public boolean addStock(Stock stock) {
        try {
            stock.setName(stock.getName());
            stock.setQuantity(stock.getQuantity());
            stock.setPrice(stock.getPrice());
            stock.setManufacturer(stock.getManufacturer());

            stocks.add(stock);
            fireTableRowsInserted(stocks.size() - 1, stocks.size() - 1);

            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }


    public void removeStockAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < stocks.size()) {
            stocks.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
