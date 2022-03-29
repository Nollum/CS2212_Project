package cryptoTrader.tradingBroker;
import java.util.*;


public class TradingBrokerList implements TradingBrokerInterface {
	
	private static TradingBrokerList instance = null;
	List<TradingBroker> brokerList;
	
	public static TradingBrokerList getInstance() {
		if (instance == null)
			instance = new TradingBrokerList();

		return instance;
	}
	
	private TradingBrokerList() {
		brokerList = new LinkedList<TradingBroker>();
	}
	
	@Override
	public void addBroker(TradingBroker broker) {
		brokerList.add(broker);
	}
}
