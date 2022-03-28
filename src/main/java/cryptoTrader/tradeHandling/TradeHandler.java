package cryptoTrader.tradeHandling;

import java.util.ArrayList;

import cryptoTrader.tradeResult.TradeResultList;
import cryptoTrader.tradingBroker.TradingBrokerList;
import cryptoTrader.utils.DataFetcher;

public class TradeHandler {

	private static TradeHandler instance;
	private DataFetcher dataFetcher;
	private TradingBrokerList tradingBrokerList;
	private TradeResultList tradeResultList;

	public static TradeHandler getInstance() {
		if (instance == null)
			instance = new TradeHandler();

		return instance;
	}
	
	private TradeHandler() {
		// Instantiate a DataFetcher 
		// CHANGE TO SINGLETON?
		this.dataFetcher = new DataFetcher();
		this.tradingBrokerList = TradingBrokerList.getInstance();
		this.tradeResultList = TradeResultList.getInstance();
		// Debug message
		System.out.println("Trade Handler is setup and active");

	}
	
	
	public void initiateTrade(ArrayList<String> brokers, ArrayList<String[]> coins, ArrayList<String> strategies) {
//		System.out.println(brokers.get(0) + " " + coins.get(0)[0] + " " + strategies.get(0));
		
	}
	
	private double fetchCoinData(String list) {
		double price = this.dataFetcher.getPriceForCoin("bitcoin", "08-09-2021");
		return price;
	}
	
	// Debugging purposes only
	public static void main(String[] args) {
		TradeHandler instance = TradeHandler.getInstance();
		System.out.println("Price: " + instance.fetchCoinData(null));
	}
}
