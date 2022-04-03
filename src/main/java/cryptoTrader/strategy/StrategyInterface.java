package cryptoTrader.strategy;
import java.util.ArrayList;
import java.util.HashMap;

import cryptoTrader.tradeResult.*;

/**
 * StrategyInterface is used by StrategyA, StrategyB, and StrategyC
 * The methods are overridden by each Strategy class
 * @authors Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov 
 */
public interface StrategyInterface {

	/*
	 * performTrade method is abstract so it can be overriden by the Strategy classes
	 * It returns the TradeResult of a trade
	 * @param String trader
	 * @param ArrayList<String> coinList
	 * @param HashMap<String, Double> coinPrices
	 * @return TradeResult
	 */
	abstract TradeResult performTrade(String trader, ArrayList<String> coinList, HashMap<String, Double> coinPrices);
	
	/*
	 * getStrategyName returns the name of the implemented strategy
	 * @return String (of the strategy name)
	 */
	abstract String getStrategyName();
	
}
