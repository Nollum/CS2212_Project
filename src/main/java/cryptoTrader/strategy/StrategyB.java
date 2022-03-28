package cryptoTrader.strategy;
import cryptoTrader.tradeResult.TradeResult;

/**
 * Creates a StrategyB object in a singleton design pattern
 * Performs trade using the appropriate rules
 * Rule: "If the price of ADA < $2, and the price of ETH < $3500, then buy ADA of worth $1000"
 * @authors Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov
 */
public class StrategyB implements StrategyInterface {
	
	private static StrategyB instance = null;

	String trader;
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
	}
	
	/**
	 * This method executes a trade according to a predefined set of rules. 
	 * This performs a trade as follows: If the price of ADA < $2, and the price of ETH < $3500, then buy ADA of worth $1000
	 * @param trader This is the name of the trading broker that initiated this trade
	 * @param coinList This is the list of coins that this strategy is interested in knowing the prices for performing a trade
	 * @param coinPrices This is the list of prices corresponding to each coin in the list of coins in coinList
	 * @return TradeResult This method returns a TradeResult object storing the details of the trade performed
	 */
	@Override
	public TradeResult performTrade(String trader, String[] coinList, double[] coinPrices) {
		this.trader = trader;
		
		double ethPrice = 4.00; // replace with value from coinPrices
		double adaPrice = 30.34; // replace with value from coinPrices
		
		if ((adaPrice < 2) && (ethPrice < 3500)) {
			coinTraded = "ADA";
			action = "Buy";
			quantity = (int) (1000 / adaPrice);
			price = quantity * adaPrice;
		}
		else {
			coinTraded = "None";
			action = "Fail";
			quantity = 0;
			price = 0;
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

}
