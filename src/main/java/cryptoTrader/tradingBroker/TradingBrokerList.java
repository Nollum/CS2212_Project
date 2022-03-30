package cryptoTrader.tradingBroker;
import java.util.*;


public class TradingBrokerList implements TradingBrokerListInterface {
	
	private static TradingBrokerList instance = null;
	private static ArrayList brokerList;
	
	public static TradingBrokerList getInstance() {
		if (instance == null)
			instance = new TradingBrokerList();

		return instance;
	}
	
	private TradingBrokerList() {
		brokerList = new ArrayList<TradingBroker>();
	}
	
	public ArrayList<TradingBroker> getBrokers() {
		return brokerList;
	}
	
	@Override
	public void addBroker(TradingBroker broker) {
		brokerList.add(broker);
	}
}
