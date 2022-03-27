package cryptoTrader.tradingBroker;
import java.util.*;

public class TradingBrokerList{
	
	private static TradingBrokerList instance = null;
	List<TradingBroker> resultsList;
	
	public static TradingBrokerList getInstance() {
		if (instance == null)
			instance = new TradingBrokerList();

		return instance;
	}
	
	private TradingBrokerList() {
		resultsList = new LinkedList<TradingBroker>();
	}
	
	public void addResult(TradingBroker result) {
		resultsList.add(result);
		// notify viewer
	}
}