package com.techelevator;

public interface VendingItem {

    String getName();
    String getType();
    Double getPrice();
    String getSlotLocation();
    int getInventoryCount();
    double getTransactionTotal();
    int numberPurchased();


}
