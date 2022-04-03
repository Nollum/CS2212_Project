package cryptoTrader.tradeResult;

import java.util.*;

/*
 * TradeResultList class implements methods from TradeResultListInterface
 * It creates an instance of a the TradeResultList that are an output of the trading actions,
 * and is used to display the data charts
 * 
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class TradeResultList implements TradeResultListInterface {
	
	private static TradeResultList instance = null;
	private ArrayList<TradeResult> resultsList;
	
	/*
	 * method getInstance() returns an instance of a TradeResultList object
	 * @return instance
	 */
	public static TradeResultList getInstance() {
		if (instance == null)
			instance = new TradeResultList();

		return instance;
	}
	
	/*
	 * TradeResultList constructor initiates the ArrayList of TradeResult objects
	 */
	private TradeResultList() {
		resultsList = new ArrayList<TradeResult>();
	}
	
	/*
	 * method is implemented from TradeResultListInterface, adding a TradeResult to ArrayList<TradeResult> resultsList
	 * @param TradeResult result
	 */
	@Override
	public void addResult(TradeResult result) {
		resultsList.add(result);
		// notify viewer
	}
}
