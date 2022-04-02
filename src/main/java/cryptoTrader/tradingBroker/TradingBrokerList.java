package cryptoTrader.tradingBroker;
import java.util.*;


public class TradingBrokerList implements TradingBrokerListInterface {
	
	private static TradingBrokerList instance = null;
	private static ArrayList<TradingBroker> brokerList;
	
	public static TradingBrokerList getInstance() {
		if (instance == null)
			instance = new TradingBrokerList();

		return instance;
	}
	
	private TradingBrokerList() {
		brokerList = new ArrayList<TradingBroker>();
	}
	
	@Override
	public ArrayList<TradingBroker> getBrokers() {
		return brokerList;
	}
	
	@Override
	public void addBroker(TradingBroker broker) {
		brokerList.add(broker);
	}
	
	@Override
	public void removeBroker(String broker) {
		brokerList.remove(broker);
	}

	
	public void clear() {
		brokerList.clear();
	}
	
	
}
