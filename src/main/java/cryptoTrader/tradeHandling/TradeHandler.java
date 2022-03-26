package cryptoTrader.tradeHandling;

import cryptoTrader.utils.DataFetcher;

public class TradeHandler {

	private static TradeHandler instance;
	
	private DataFetcher dataFetcher;

	public static TradeHandler getInstance() {
		if (instance == null)
			instance = new TradeHandler();

		return instance;
	}

	private TradeHandler() {
		// Instantiate a DataFetcher
		this.dataFetcher = new DataFetcher();
		
		// Debug message
		System.out.println("Trade Handler is setup and active");

	}
	
	public double fetchCoinData(String list) {
		double price = this.dataFetcher.getPriceForCoin("bitcoin", "08-09-2021");
		return price;
	}
	
	// Debugging purposes only
	public static void main(String[] args) {
		TradeHandler instance = TradeHandler.getInstance();
		System.out.println("Price: " + instance.fetchCoinData(null));
	}
}
