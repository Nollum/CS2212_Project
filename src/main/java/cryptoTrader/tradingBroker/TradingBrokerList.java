package cryptoTrader.tradingBroker;
import java.util.*;

/*
 * TradingBrokerList implements methods from TradingBrokerListInterface
 * It creates an instance of a trading broker client list, with methods to
 * add a broker, remove a broker, get the brokers, and clear the entire list when an edit is made
 * 
 * @author Hanniya Zohdi, Rustam Mamedov, Shruthi Sundararaman
 */

public class TradingBrokerList implements TradingBrokerListInterface {
	
	private static TradingBrokerList instance = null;
	private static ArrayList<TradingBroker> brokerList;
	
	/*
	 * method getInstance() returns an instance of a TradingBrokerList object
	 * @return instance
	 */
	public static TradingBrokerList getInstance() {
		if (instance == null)
			instance = new TradingBrokerList();

		return instance;
	}
	
	/*
	 * TradingBrokerList constructor initiates the ArrayList of TradingBroker objects
	 */
	private TradingBrokerList() {
		brokerList = new ArrayList<TradingBroker>();
	}
	
	/*
	 * method is implemented from the TradingBrokerListInterface
	 * method returns the list of brokers
	 * @return ArrayList<TradingBroker> brokerList
	 */
	@Override
	public ArrayList<TradingBroker> getBrokers() {
		return brokerList;
	}
	
	/*
	 * method is implemented from the TradingBrokerListInterface
	 * method adds a new broker to the brokerList 
	 * @param TradingBroker broker
	 */
	@Override
	public void addBroker(TradingBroker broker) {
		brokerList.add(broker);
	}
	
	/*
	 * method is implemented from the TradingBrokerListInterface
	 * method removes a specific broker from the brokerList
	 * @param String broker 
	 */
	@Override
	public void removeBroker(String broker) {
		brokerList.remove(broker);
	}

	/*
	 * clear() method is used by the TradeHandler to clear out the brokerList whenever a user edit is made
	 */
	public void clear() {
		brokerList.clear();
	}
	
	
}
