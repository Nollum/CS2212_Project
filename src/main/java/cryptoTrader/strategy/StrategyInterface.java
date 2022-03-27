package cryptoTrader.strategy;
import cryptoTrader.tradeResult.*;

public interface StrategyInterface {

	// update to return TradeResult object
	TradeResult performTrade(String trader, String[] coinList, double[] coinPrices);
	
	String getStrategyName();
	
}
