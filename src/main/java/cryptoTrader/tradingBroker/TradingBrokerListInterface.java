package cryptoTrader.tradingBroker;

import java.util.ArrayList;

public interface TradingBrokerListInterface {

	public void addBroker(TradingBroker broker);
	
	public ArrayList<TradingBroker> getBrokers();
	
	public void removeBroker(String brokerName);
	
}