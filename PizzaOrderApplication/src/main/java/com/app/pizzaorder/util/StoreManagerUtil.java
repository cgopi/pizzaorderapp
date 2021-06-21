package com.app.pizzaorder.util;

public class StoreManagerUtil {

	/* Get the deal based on pizza type */
	public static String getAvailableDeal(String variety) {
		String deal;
		switch(variety) {
			case PizzaApplicationConstants.CHEESE_PIZZA : 
				deal = PizzaApplicationConstants.ONE_PERCENT_ON_CHEESE_PIZZA;
				break;
			case PizzaApplicationConstants.CHICKEN_PIZZA : 
				deal = PizzaApplicationConstants.THREE_PERCENT_ON_CHICKEN_PIZZA;
				break;
			case PizzaApplicationConstants.BEEF_PIZZA : 
				deal = PizzaApplicationConstants.TWO_PERCENT_ON_BEEF_PIZZA;
				break;
			default :
				deal = PizzaApplicationConstants.NO_DEAL;
		}

		return deal;
	}

	/* Get the inventory based on pizza type */
	public static int getInventory(String variety) {
		int inventoryCount = 0;
		switch(variety) {
			case PizzaApplicationConstants.CHEESE_PIZZA : 
				inventoryCount = PizzaApplicationConstants.CHEESE_PIZZA_INVENTORY;
				break;
			case PizzaApplicationConstants.CHICKEN_PIZZA : 
				inventoryCount = PizzaApplicationConstants.CHICKEN_PIZZA_INVENTORY;
				break;
			case PizzaApplicationConstants.BEEF_PIZZA : 
				inventoryCount = PizzaApplicationConstants.BEEF_PIZZA_INVENTORY;
				break;
			default :
		}

		return inventoryCount;
	}
	
	public static double getDealPrice(double price, final String deal) {
		price = price - (price * (Integer.parseInt(deal))/100);
		return price;
	}

}
