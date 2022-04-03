package cryptoTrader.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// import org.apache.commons.lang3.ArrayUtils; --> figure out how to do this to find array element index

import cryptoTrader.tradeResult.TradeResult;
import cryptoTrader.tradeResult.TradeResultList;

/**
 * Creates a StrategyA object in a singleton design pattern
 * Performs trade using the appropriate rules
 * Rule: "If the price of BTC < 50,000 and the price of ADA > 2, then buy 10 ADA"
 * @authors Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov 
 */
public class StrategyA implements StrategyInterface {
	
	private static StrategyA instance = null;

	String strategyName;
	String coinTraded;
	String action = null;
	int quantity;
	double price;
	
	/**
	 * This method is used to get the single instance of StrategyA
	 * If an instance doesn't already exists, it creates a new instance of StrategyA
	 * @return StrategyA the instance of StrategyA
	 */
	public static StrategyA getInstance() {
		
		if (instance == null)
			instance = new StrategyA();

		return instance;
	}
	
	/**
	 * The constructor of the class
	 * initializes strategyName to the name of the current strategy
	 */
	private StrategyA () {
		
		strategyName = getStrategyName();
		coinTraded = "None";
		action = "Fail";
		quantity = 0;
		price = 0;
	}

	
	/**
	 * This method executes a trade according to a predefined set of rules. 
	 * This performs a trade as follows: If the price of BTC < 50,000 and the price of ADA > 2, then buy 10 ADA
	 * @param trader This is the name of the trading broker that initiated this trade
	 * @param coinList This is the list of coins that this strategy is interested in knowing the prices for performing a trade
	 * @param coinPrices This is the list of prices corresponding to each coin in the list of coins in coinList
	 * @return TradeResult This method returns a TradeResult object storing the details of the trade performed
	 */
	@Override
	public TradeResult performTrade(String trader, ArrayList<String> coinList, HashMap<String, Double> coinPrices) {		
		if (coinList.contains("BTC") && coinList.contains("ADA")) {
			double btcPrice = coinPrices.get("BTC"); 
			double adaPrice = coinPrices.get("ADA");	
			if ((btcPrice < 50000) && (adaPrice > 2) && (coinList.contains("ADA"))) {
				coinTraded = "ADA";
				action = "Buy";
				quantity = 10;
				price = quantity * adaPrice;
			}
		}

		TradeResult result = new TradeResult(trader, strategyName, coinTraded, action, quantity, price);
		return result;
	}

	/**
	 * This method returns the name of the strategy
	 * @return String This returns the name of the current strategy - "Strategy-A"
	 */
	@Override
	public String getStrategyName() {
		return "Strategy-A";
	}
	
	// for testing purposes
	public static void main(String[] args) {
		StrategyA myStrategy = StrategyA.getInstance();
		ArrayList<String> coinList1 = new ArrayList<String>();
		coinList1.add("BTC");
		coinList1.add("ADA");
		
		HashMap<String, Double> coinPrices1 = new HashMap<String, Double>();
		coinPrices1.put("BTC", 500.3);
		coinPrices1.put("ADA", 4.0);
		
		TradeResult testResult = myStrategy.performTrade("TestBroker", coinList1, coinPrices1);
		System.out.println(testResult.getEverything());
				
	}

}


