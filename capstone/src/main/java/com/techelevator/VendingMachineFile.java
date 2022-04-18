package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/* 1. Access and read File
   2. Delimit the line
   3. Setting name and price
   4. Fill the Map with (K,V) pairs (name, price)
   5. Loop through the Map to build Inventory List


*/
public class VendingMachineFile {
   Map<String, Double> inventoryDisplay = new HashMap<String, Double>();
    File vendingInventoryFile = new File("C:\\MeritCode\\New folder\\nicholas-craft-student-exercises\\Mod 1 Capstone\\module-1-capstone\\capstone\\vendingmachine.csv");
    Item item = new Item();

    public void getInventoryDisplay(){
     item.setInventoryCount(5);
    // Reading each line of the file
     try(Scanner input = new Scanner(vendingInventoryFile)) {
      while (input.hasNextLine()) {
       String lines = input.nextLine();
       String[] lineAfterSplit = lines.split("\\|");
       item.setName(lineAfterSplit[1]);
       item.setPrice(Double.parseDouble(lineAfterSplit[2]));

       inventoryDisplay.put(item.getName(), item.getPrice());
      }

     } catch (FileNotFoundException e){
      System.err.println("Error File not Found!");
     }
     for (Map.Entry<String, Double> inventory : inventoryDisplay.entrySet()) {

      System.out.println(inventory.getKey() + " " + "$" + inventory.getValue());
     }

    }


    //public void display() {

   // }
    //Chips chips = new Chips();
    //Gum gum = new Gum();
    //Candy candy = new Candy();
    //Drink drink = new Drink();








    }
