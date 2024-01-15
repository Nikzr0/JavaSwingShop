package storeapp.tablemodel;

import storeapp.model.Stock;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StocksTableModel extends AbstractTableModel {
    private final List<Stock> stocks;
    private final String[] columnNames = {"Name", "Category", "Price", "Quantity"};

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
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public void addStock(Stock stock) {
        try {
            stock.setName(stock.getName());
            stock.setQuantity(stock.getQuantity());
            stock.setPrice(stock.getPrice());
            stocks.add(stock);
            fireTableRowsInserted(stocks.size() - 1, stocks.size() - 1);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
        }
    }

    public void removeStock(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < stocks.size()) {
            stocks.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }
}