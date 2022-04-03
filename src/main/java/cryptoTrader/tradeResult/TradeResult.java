package cryptoTrader.tradeResult;
import java.util.*;

public class TradeResult {

	String trader; // update with Broker
	String strategy;
	String coinTraded;
	String action;
	int quantity;
	double price;
	Date date;
	
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
	
	public String getTraderName() {
		return trader;
	}
	
	public String getStrategyName() {
		return strategy;
	}
	
	public String getCoinTraded() {
		return coinTraded;
	}
	
	public String getAction() {
		return action;
	}
	
	public String getQuantity() {
		return Integer.toString(quantity);
	}

	public String getPrice() {
		return Double.toString(price);
	}
	
	public String getDate() {
		return date.toString();
	}
	
	
	// returns everything; for testing purposes
	public String getEverything() {
		return trader + " " + strategy + " " + coinTraded + " " + action + " " + getQuantity() + " " + getPrice() + " " + getDate();
	}

}
