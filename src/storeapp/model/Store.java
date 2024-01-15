package storeapp.model;

import java.util.List;

public class Store {
    private String name;
    private String address;
    private List<Stock> stockList;

    public Store(String name, String address, List<Stock> stockList) {
        setName(name);
        setAddress(address);
        this.stockList = stockList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() >= 3 && name.length() <= 50) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Store name should be between 3 and 30 characters long");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.length() >= 3 && address.length() <= 100) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Store address should be between 3 and 100 characters long");
        }
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
