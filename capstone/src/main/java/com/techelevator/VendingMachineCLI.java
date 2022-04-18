package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class VendingMachineCLI {


	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION,};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	VendingMachineFile vendingMachineFile = new VendingMachineFile();
	ItemPurchase itemPurchase = new ItemPurchase();
	public void run() {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingMachineFile.getInventoryDisplay();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while (true) {
					String purchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchase.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						itemPurchase.moneyInserted();
						itemPurchase.printLogFeedMoney();
					} else if (purchase.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						itemPurchase.customerItemSelectPrint();
						itemPurchase.printItemDispensed();
					} else if (purchase.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						itemPurchase.changeInCoins();
						String mainMenu = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

						if(mainMenu.equals(MAIN_MENU_OPTION_EXIT)){
							System.exit(0);
						}

					}

				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
