package cryptoTrader.tradingBroker;

import java.util.ArrayList;

/**
 * TradingBrokerListInterface is used by TradingBrokerList
 * The methods addBroker(TradingBroker broker), getBrokers(), removeBroker(String brokerName) and are overridden by TradingBrokerList
 * @author Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov 
 */

public interface TradingBrokerListInterface {

	/**
	 * addBroker method adds a new TradingBroker to the broker list
	 * @param TradingBroker broker
	 */
	public void addBroker(TradingBroker broker);
	
	/**
	 * getBrokers method returns the ArrayList of TradingBroker objects
	 * @return ArrayList<TradingBroker>
	 */
	public ArrayList<TradingBroker> getBrokers();
	
	/**
	 * removeBroker method removes a broker from the list using their client name
	 * @param String brokerName
	 */
	public void removeBroker(String brokerName);
	
}