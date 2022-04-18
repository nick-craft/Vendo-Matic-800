package com.techelevator;

public class Item implements VendingItem{
    private String name;
    private String type;
    private Double price;
    private String slotLocation;
    private int inventoryCount;
    private int numberPurchased;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public String getSlotLocation() {
        return slotLocation;
    }

    @Override
    public int getInventoryCount() {
        return inventoryCount;
    }

    @Override
    public double getTransactionTotal() {
        return price * numberPurchased;
    }

    @Override
    public int numberPurchased() {
        return numberPurchased;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public void setNumberPurchased(int numberPurchased) {
        this.numberPurchased = numberPurchased;
    }
}
