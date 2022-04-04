package cryptoTrader.tradeHandling;

import java.util.ArrayList;

public interface TradeHandlerInterface {
	public void initiateTrade(ArrayList<String> brokers, ArrayList<String[]> coinMatrix, ArrayList<String> strategies);
}
