package cryptoTrader.strategy;
import cryptoTrader.tradeResult.*;

public interface StrategyInterface {

	// update to return TradeResult object
	TradeResult performTrade(String[] coinList, float[] coinPrices);
	
	String getStrategyName();
	
}
