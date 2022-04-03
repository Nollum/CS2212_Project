package cryptoTrader.tradingBroker;
import java.util.ArrayList;

import cryptoTrader.strategy.StrategyInterface;

/*
 * TradingBroker class creates a Broker instance in accordance to the user inputs.
 * Imports StrategyInterface from the cryptoTrader.strategy package
 * 
 * @author Hanniya Zohdi, Rustam Mamedov, Shruthi Sundararaman
 */

public class TradingBroker{
	
	private String brokerName;
	private ArrayList<String> coinList;
	private StrategyInterface strategy;
	
	/*
	 * Constructor consists of the trading client name, their respected crypto coin list, and chosen strategy. 
	 * The constructor takes the user inputs from the MainUI JComponents
	 * @param String brokerName
	 * @param String[] coinList
	 * @param StrategyInterface strategy
	 */
	public TradingBroker(String brokerName, String[] coinList, StrategyInterface strategy) {
		
		this.brokerName = brokerName;
		this.coinList = new ArrayList<String>();
		this.strategy = strategy;
		
	}

	/*
	 * getter method for brokerName
	 * @return String brokerName: name of the trading client
	 */
	public String getBrokerName() {
		return brokerName;
	}

	/*
	 * setter method for brokerName
	 * @param String brokerName: name of the trading client
	 */
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	/*
	 * getter method for coinList
	 * @return String[] coinList: a list of the crypto coins the client is trading
	 */
	public ArrayList<String> getCoinList() {
		return coinList;
	}

	/*
	 * setter method for coinList
	 * @param String[] coinList: a list of the crypto coins the client is trading
	 */
	public void setCoinList(ArrayList<String> coinList) {
		this.coinList = coinList;
	}

	/*
	 * getter method for strategy
	 * @return StrategyInterface strategy: the Strategy object the client is basing their trade actions on
	 */
	public StrategyInterface getStrategy() {
		return strategy;
	}

	/*
	 * setter method for strategy
	 * @param StrategyInterface strategy: the Strategy object the client is basing their trade actions on
	 */
	public void setStrategy(StrategyInterface strategy) {
		this.strategy = strategy;
	}
	
	

}
