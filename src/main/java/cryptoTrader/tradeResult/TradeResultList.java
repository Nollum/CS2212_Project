package cryptoTrader.tradeResult;

import java.util.*;
import cryptoTrader.viewer.*; // change to interface

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
		DataVisualizationCreator creator = new DataVisualizationCreator();
		//creator.createCharts(resultsList);
		
	}
}
