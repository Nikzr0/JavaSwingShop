package storeapp.tablemodel;

import storeapp.model.Stock;
import storeapp.model.Store;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StoresTableModel extends AbstractTableModel {
    private final List<Store> stores;

    private final String[] columnNames = {
            "Name",
            "Address"
    };
    public StoresTableModel(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public int getRowCount() {
        return stores.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addStore(Store store) {
        try {
            store.setName(store.getName());
            store.setAddress(store.getAddress());
            stores.add(store);
            fireTableRowsInserted(stores.size() - 1, stores.size() - 1);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
        }
    }

    public void removeStoreAt(int rowIndex) {
        if (rowIndex >= 0) {
            stores.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    public Store getStoreAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < stores.size()) {
            return stores.get(rowIndex);
        } else {
            throw new IndexOutOfBoundsException("Invalid store index: " + rowIndex);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Store store = stores.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return store.getName();
            case 1:
                return store.getAddress();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Store store = stores.get(rowIndex);

        switch (columnIndex) {
            case 0 ->
                    store.setName((String)aValue);
            case 1 ->
                    store.setAddress((String)aValue);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
