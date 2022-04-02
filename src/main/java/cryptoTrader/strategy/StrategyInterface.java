package cryptoTrader.strategy;
import java.util.ArrayList;
import java.util.HashMap;

import cryptoTrader.tradeResult.*;

public interface StrategyInterface {

	abstract TradeResult performTrade(String trader, ArrayList<String> coinList, HashMap<String, Double> coinPrices);
	
	abstract String getStrategyName();
	
}
