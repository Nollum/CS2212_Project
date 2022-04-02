package cryptoTrader.tradeResult;

import java.util.*;

public class TradeResultList implements TradeResultListInterface {
	
	private static TradeResultList instance = null;
	private ArrayList<TradeResult> resultsList;
	
	public static TradeResultList getInstance() {
		if (instance == null)
			instance = new TradeResultList();

		return instance;
	}
	
	private TradeResultList() {
		resultsList = new ArrayList<TradeResult>();
	}
	
	public void addResult(TradeResult result) {
		resultsList.add(result);
		// notify viewer
	}
}
