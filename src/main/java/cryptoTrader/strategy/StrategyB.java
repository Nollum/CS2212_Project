package cryptoTrader.strategy;
import java.util.ArrayList;
import java.util.HashMap;

import cryptoTrader.tradeResult.TradeResult;

/**
 * Creates a StrategyB object in a singleton design pattern
 * Performs trade using the appropriate rules
 * Rule: "If the price of ADA is less than $2, and the price of ETH is less than $3500, then buy ADA of worth $1000"
 * @author Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov
 */
public class StrategyB implements StrategyInterface {
	
	private static StrategyB instance = null;

	String strategy;
	String coinTraded;
	String action = null;
	int quantity;
	double price;
	
	/**
	 * This method is used to get the single instance of StrategyB
	 * If an instance doesn't already exists, it creates a new instance of StrategyB
	 * @return StrategyB the instance of StrategyB
	 */
	public static StrategyB getInstance() {
		if (instance == null)
			instance = new StrategyB();
		
		return instance;
	}
	
	/**
	 * The constructor of the class
	 * initializes strategyName to the name of the current strategy
	 */
	private StrategyB() {
		strategy = getStrategyName();
		initializeProperties();
	}
	
	/**
	 * This method executes a trade according to a predefined set of rules. 
	 * This performs a trade as follows: If the price of ADA < $2, and the price of ETH < $4500, then buy ADA of worth $1000
	 * @param trader This is the name of the trading broker that initiated this trade
	 * @param coinList This is the list of coins that this strategy is interested in knowing the prices for performing a trade
	 * @param coinPrices This is the list of prices corresponding to each coin in the list of coins in coinList
	 * @return TradeResult This method returns a TradeResult object storing the details of the trade performed
	 */
	public TradeResult performTrade(String trader, ArrayList<String> coinList, HashMap<String, Double> coinPrices) {
		
		initializeProperties();
		
		if (coinList.contains("ADA") && coinList.contains("ETH")) {
			
			double ethPrice = coinPrices.get("ETH");
			double adaPrice = coinPrices.get("ADA");
			//System.out.println(ethPrice);
			//System.out.println(adaPrice);
			if ((adaPrice < 2) && (ethPrice < 4500)) { 
				//System.out.println("HERE");
				coinTraded = "ADA";
				action = "Buy";
				quantity = (int) (1000 / adaPrice);
				price = quantity * adaPrice;
			}
		}
		
		TradeResult result = new TradeResult(trader, strategy, coinTraded, action, quantity, price);
		return result;
	}

	/**
	 * This method returns the name of the strategy
	 * @return String This returns the name of the current strategy - "Strategy-B"
	 */
	@Override
	public String getStrategyName() {
		return "Strategy-B";
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
		StrategyB myStrategy = StrategyB.getInstance();
		ArrayList<String> coinList1 = new ArrayList<String>();
		coinList1.add("ETH");
		coinList1.add("ADA");
		
		HashMap<String, Double> coinPrices1 = new HashMap<String, Double>();
		coinPrices1.put("ETH", 500.3);
		coinPrices1.put("ADA", 1.4);
		
		TradeResult testResult = myStrategy.performTrade("TestBroker", coinList1, coinPrices1);
		System.out.println(testResult.getEverything());
				
	}

}
