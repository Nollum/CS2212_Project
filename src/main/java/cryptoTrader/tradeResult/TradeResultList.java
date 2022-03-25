package cryptoTrader.tradeResult;

import java.util.*;

public class TradeResultList {
	
	private static TradeResultList instance = null;
	List<TradeResult> resultsList;
	
	public static TradeResultList getInstance() {
		if (instance == null)
			instance = new TradeResultList();

		return instance;
	}
	
	private TradeResultList() {
		resultsList = new LinkedList<TradeResult>();
	}
	
	public void addResult(TradeResult result) {
		resultsList.add(result);
		// notify viewer
	}
}
