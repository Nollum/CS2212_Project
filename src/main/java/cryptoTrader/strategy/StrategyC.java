package cryptoTrader.strategy;

import java.util.ArrayList;
import java.util.HashMap;

import cryptoTrader.tradeResult.TradeResult;

/**
 * Creates a StrategyC object in a singleton design pattern
 * Performs trade using the appropriate rules
 * Rule: "If the price of ADA is more than $3, and the price of BTC is less than $50, then buy 50 BTC"
 * @author Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov
 */
public class StrategyC implements StrategyInterface {

	private static StrategyC instance = null;

	String strategy;
	String coinTraded;
	String action = null;
	int quantity;
	double price;
	
	/**
	 * This method is used to get the single instance of StrategyC
	 * If an instance doesn't already exists, it creates a new instance of StrategyC
	 * @return StrategyC the instance of StrategyC
	 */
	public static StrategyC getInstance() {
		if (instance == null)
			instance = new StrategyC();
		
		return instance;
	}

	/**
	 * The constructor of the class
	 * initializes strategyName to the name of the current strategy
	 */
	private StrategyC() {
		strategy = getStrategyName();
		initializeProperties();
	}
	
	/**
	 * This method executes a trade according to a predefined set of rules. 
	 * This performs a trade as follows: If the price of ADA > $3, and the price of BTC < $50, then buy 50 BTC
	 * @param trader This is the name of the trading broker that initiated this trade
	 * @param coinList This is the list of coins that this strategy is interested in knowing the prices for performing a trade
	 * @param coinPrices This is the list of prices corresponding to each coin in the list of coins in coinList
	 * @return TradeResult This method returns a TradeResult object storing the details of the trade performed
	 */
	public TradeResult performTrade(String trader, ArrayList<String> coinList, HashMap<String, Double> coinPrices) {
		
		initializeProperties();
		
		if (coinList.contains("BTC") && coinList.contains("ADA")) {
			double btcPrice = coinPrices.get("BTC");
			double adaPrice = coinPrices.get("ADA");		
			if ((adaPrice > 3) && (btcPrice < 50)) {
				coinTraded = "BTC";
				action = "Sell";
				quantity = 50;
				price = quantity * btcPrice;
			}
		}
		
		TradeResult result = new TradeResult(trader, strategy, coinTraded, action, quantity, price);
		return result;
	}
	
	/**
	 * This method returns the name of the strategy
	 * @return String This returns the name of the current strategy - "Strategy-C"
	 */
	@Override
	public String getStrategyName() {
		return "Strategy-C";
	}
	
	/**
	 * This method sets all the properties for a trade result to represent a fail state
	 */
	@Override
	public void initializeProperties() {
		coinTraded = "None";
		action = "Fail";
		quantity = 0;
		price = 0;
	}
	
	// for testing purposes
	public static void main(String[] args) {
		StrategyC myStrategy = StrategyC.getInstance();
		ArrayList<String> coinList1 = new ArrayList<String>();
		coinList1.add("BTC");
		coinList1.add("ADA");
		
		HashMap<String, Double> coinPrices1 = new HashMap<String, Double>();
		coinPrices1.put("BTC", 10.3);
		coinPrices1.put("ADA", 4.0);
		
		TradeResult testResult = myStrategy.performTrade("TestBroker", coinList1, coinPrices1);
		System.out.println(testResult.getEverything());
				
	}

}
