package cryptoTrader.tradeResult;
import java.util.*;

/**
 * TradeResult class handles the output of client trading actions.
 * It returns the broker name, strategy, crypto traded, price, quantity, and action (buy/sell)
 * 
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class TradeResult {

	String trader; // update with Broker
	String strategy;
	String coinTraded;
	String action;
	int quantity;
	double price;
	Date date;
	
	/**
	 * TradeResult constructor
	 * @param String trader
	 * @param String strategy
	 * @param String coinTraded
	 * @param String action 
	 * @param int quantity
	 * @paramdouble price
	 */
	public TradeResult(String trader, String strategy, String coinTraded, 
			String action, int quantity, double price) 
	{
		this.trader = trader;
		this.strategy = strategy;
		this.coinTraded = coinTraded;
		this.action = action;
		this.quantity = quantity;
		this.price = price;
		date = new Date();
	}
	
	/**
	 * getter method that returns the trader name in a TradeResult
	 * @return String trader
	 */
	public String getTraderName() {
		return trader;
	}
	
	/**
	 * getter method that returns the strategy used in a TradeResult
	 * @return String strategy
	 */
	public String getStrategyName() {
		return strategy;
	}
	
	/**
	 * getter method that returns the crypto ticker in a TradeResult
	 * @return String coinTraded
	 */
	public String getCoinTraded() {
		return coinTraded;
	}
	
	/**
	 * getter method that returns the action of a TradeResult
	 * @return String action - either "sell" or "buy" depending on the trade action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * getter method that returns the quantity of coins traded in a TradeResult
	 * @return String quantity
	 */
	public String getQuantity() {
		return Integer.toString(quantity);
	}

	/**
	 * getter method that returns the price of crypto traded in a TradeResult
	 * @return String price
	 */
	public String getPrice() {
		return Double.toString(price);
	}
	
	/**
	 * getter method that returns the date of a specific trade in a TradeResult
	 * @return String date
	 */
	public String getDate() {
		return date.toString();
	}
	
	
	// returns everything; for testing purposes
	public String getEverything() {
		
		// "Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3","13-January-2022"
		return trader + " " + strategy + " " + coinTraded + " " + action + " " + getQuantity() + " " + getPrice() + " " + getDate();
	}

}
