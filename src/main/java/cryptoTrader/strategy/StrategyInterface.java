package cryptoTrader.strategy;
import cryptoTrader.tradeResult.*;

public interface StrategyInterface {

	TradeResult performTrade(String trader, String[] coinList, double[] coinPrices);
	
	String getStrategyName();
	
}
