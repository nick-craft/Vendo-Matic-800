package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class ItemPurchase {
    /*
     Dispensing also returns a message:
          - All chip items print "Crunch Crunch, Yum!"
          - All candy items print "Munch Munch, Yum!"
          - All drink items print "Glug Glug, Yum!"
          - All gum items print "Chew Chew, Yum!"


          Gonna need to ask for Slot Location for purchase
          Need to print message based on type
          Needs to access current inventory
          Needs how much money is inserted
          Needs how much money is left over
          Needs change dispensed at end if any

          Display Full Item Availability with slot location to choose item
     */

    //Dispensing an item prints the item name, cost, and the money remaining.
    Item item = new Item();
    private String itemSlotLocation;
    private double dollarAmountInserted;
    private int currentInventory;
    private int initialInventory = 5;
    private int numberOfItemsPurchased = 1;
    private final File outputFile = new File("Log.txt");

    public int getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(int currentInventory) {
        this.currentInventory = currentInventory;
    }

    public double getDollarAmountInserted() {
        return dollarAmountInserted;
    }

    public void setDollarAmountInserted(double dollarAmountInserted) {
        this.dollarAmountInserted = dollarAmountInserted;
    }

    Scanner moneyInput = new Scanner(System.in);
    Scanner answerYOrN = new Scanner(System.in);
    Scanner itemSelected = new Scanner(System.in);

    File inventoryFile = new File("C:\\MeritCode\\New folder\\nicholas-craft-student-exercises\\Mod 1 Capstone\\module-1-capstone\\capstone\\vendingmachine.csv");
    // Need to allow customer to insert money
    // "Please insert whole dollar amount in $1, $5, or $10"
    //  Current Total: ...
    //  "Would you like to add more money? (Y/N)"

    public Double moneyInserted() {
        System.out.println("Please insert whole dollar amount in $1, $5, or $10");
        dollarAmountInserted = moneyInput.nextDouble();
        System.out.println("Current Money Provided: " + "$" + dollarAmountInserted);
        System.out.println("Would you like to add more money? (Y/N)");
        if (answerYOrN.nextLine().equalsIgnoreCase("y")) {
            do {
                System.out.println("Please insert whole dollar amount in $1, $5, or $10");
                dollarAmountInserted += moneyInput.nextDouble();
                System.out.println("Current Money Provided: " + "$" + dollarAmountInserted);
                System.out.println("Would you like to add more money? (Y/N)");
            } while (answerYOrN.nextLine().equalsIgnoreCase("y"));
        } else if (answerYOrN.nextLine().equalsIgnoreCase("n")) {
            return dollarAmountInserted;
        }
        return dollarAmountInserted;
    }


    public void customerItemSelectPrint() {
        try (Scanner inventoryReadOut = new Scanner(inventoryFile)) {
            while (inventoryReadOut.hasNextLine()) {
                System.out.println(inventoryReadOut.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("This File Does Not Exist.");
        }
    }

    public int inventoryCountCalculator() {
        currentInventory = initialInventory - numberOfItemsPurchased;
        return currentInventory;
    }


    public void printItemDispensed() {
        System.out.println("Please select the Slot Location of the Product you would like to buy?");
        itemSlotLocation = itemSelected.nextLine();
        item.setInventoryCount(initialInventory);
        try (Scanner inventoryDispenser = new Scanner(inventoryFile)) {

            while (inventoryDispenser.hasNextLine()) {
                String line = inventoryDispenser.nextLine();
                String[] lineDelimit = line.split("\\|");
                item.setName(lineDelimit[1]);
                item.setPrice(Double.parseDouble(lineDelimit[2]));
                item.setType(lineDelimit[lineDelimit.length - 1]);
                item.setSlotLocation(lineDelimit[0]);

                //if inventory count > 0 then the product can be bought
                //if inventory count == 0 then the product is SOLD OUT

                if (itemSlotLocation.equalsIgnoreCase(lineDelimit[0])) {
                    if (item.getInventoryCount() > 0 && dollarAmountInserted >= item.getPrice()) {
                        System.out.println(item.getName() + ": " + "$" + item.getPrice() + " " + "Change Remaining: " + "$" + (dollarAmountInserted - item.getPrice()));

                        item.setInventoryCount(inventoryCountCalculator());
                        System.out.println("Left in Stock: " + inventoryCountCalculator());
                        initialInventory--;

                        try (FileOutputStream file = new FileOutputStream(outputFile, true);
                             PrintWriter pw = new PrintWriter(file)) {
                            pw.println(dateTime() + " " + item.getName() + " " + item.getSlotLocation() + " $" + dollarAmountInserted + " $" +(dollarAmountInserted - item.getPrice()));

                            pw.flush();
                        } catch (IOException e) {
                            System.out.println(e.toString());
                            System.out.println("file not found");
                        }
                        setDollarAmountInserted(dollarAmountInserted - item.getPrice());
                        if (item.getType().equalsIgnoreCase("chip")) {
                            System.out.println("Crunch Crunch Yum!");
                        } else if (item.getType().equalsIgnoreCase("candy")) {
                            System.out.println("Munch Munch Yum!");
                        } else if (item.getType().equalsIgnoreCase("drink")) {
                            System.out.println("Glug Glug Yum!");
                        } else if (item.getType().equalsIgnoreCase("gum")) {
                            System.out.println("Chew Chew Yum!");
                            break;
                        } else if (dollarAmountInserted < item.getPrice()) {
                            System.out.println("Insufficient Funds");
                            break;
                        } else if (item.getInventoryCount() == 0) {
                            System.out.println("Sold Out");
                        }
                    }
                }
            }
            System.out.println("This product does not exist!");
        } catch (FileNotFoundException e) {
            System.err.println("This File Does Not Exist");
        }

    }

    public void changeInCoins() {
        Quarter quarter = new Quarter();
        Dime dime = new Dime();
        Nickel nickel = new Nickel();

        int change = (int) (dollarAmountInserted * 100);
        int quarterValue = change / 25;
        change = change - (quarterValue * 25);
        int dimeValue = change / 10;
        change = change - (dimeValue * 10);
        int nickelValue = change / 5;
        change = change - (nickelValue * 5);

        System.out.println("Quarter(s): " + quarterValue + " " + "Dime(s): " + dimeValue + " " + "Nickel(s): " + nickelValue);
        setDollarAmountInserted(0);

    }

    public void printLogFeedMoney() {
        try (FileOutputStream file = new FileOutputStream(outputFile, true);
             PrintWriter pw = new PrintWriter(file)) {
            pw.println(dateTime() + " FEED MONEY: " + "$" + dollarAmountInserted);
            pw.flush();
        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("file not found");
        }
    }
/*
 The audit entry must be in the format:
        >```
        > 01/01/2016 12:00:00 PM FEED MONEY: $5.00 $5.00
         >01/01/2016 12:00:15 PM FEED MONEY: $5.00 $10.00
         >01/01/2016 12:00:20 PM Crunchie B4 $10.00 $8.50
         >01/01/2016 12:01:25 PM Cowtales B2 $8.50 $7.50
         >01/01/2016 12:01:35 PM GIVE CHANGE: $7.50 $0.0
 */

    public String dateTime() {
        String date = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new Date()));

        return date;
    }

    /* public void ChangeInLog(Double beginningChange, Double endingBalance) {
        String eventTrigger = "GIVE CHANGE";
        String input = buildLogEntryString(eventTrigger, dollarAmountInserted, dollarAmountInserted);

        printLogFile(input);


    } */
}


