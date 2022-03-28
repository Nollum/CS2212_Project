package cryptoTrader.strategy;

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
	String trader;
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
	public TradeResult performTrade(String trader, String[] coinList, double[] coinPrices) {
		this.trader = trader;
		
		double btcPrice = 4.00; // replace with value from coinPrices
		double adaPrice = 30.34; // replace with value from coinPrices
		
		if ((btcPrice < 50000) && (adaPrice > 2)) {
			coinTraded = "ADA";
			action = "Buy";
			quantity = 10;
			price = quantity * adaPrice;
		}
		else {
			coinTraded = "None";
			action = "Fail";
			quantity = 0;
			price = 0;
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

}
