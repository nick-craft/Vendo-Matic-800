package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ItemPurchaseTest {
    ItemPurchase itemPurchase = new ItemPurchase();
    @Test
    public void test_for_moneyInserted() {
        itemPurchase.setDollarAmountInserted(10);

        Assert.assertEquals("The dollar amount is invalid",10.00, itemPurchase.getDollarAmountInserted(), 0);
    }

    @Test
    public void test_for_inventoryCountCalculator() {
        Assert.assertEquals(4, itemPurchase.inventoryCountCalculator());
    }

    @Test
    public void test_for_printItemDispensed(){
        itemPurchase.item.setName("Potato Crisps");
        itemPurchase.item.setPrice(3.05);
        itemPurchase.item.setType("Chip");
        itemPurchase.item.setSlotLocation("A1");

        Assert.assertEquals("The item name does not exist","Potato Crisps",itemPurchase.item.getName());
        Assert.assertEquals("The price could not be found",3.05, itemPurchase.item.getPrice(), 0.0);
        Assert.assertEquals("That Type does not exist","Chip", itemPurchase.item.getType());
        Assert.assertEquals("That Slot Location does not exist","A1", itemPurchase.item.getSlotLocation());
    }
}