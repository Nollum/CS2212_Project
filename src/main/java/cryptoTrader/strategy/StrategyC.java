package cryptoTrader.strategy;

import cryptoTrader.tradeResult.TradeResult;

public class StrategyC implements StrategyInterface {

	private static StrategyC instance = null;

	String trader;
	String strategy;
	String coinTraded;
	String action = null;
	int quantity;
	double price;
	
	public static StrategyC getInstance() {
		if (instance == null)
			instance = new StrategyC();
		
		return instance;
	}

	private StrategyC() {
		strategy = getStrategyName();
	}
	
	@Override
	public TradeResult performTrade(String trader, String[] coinList, double[] coinPrices) {

		// if price of ada is greater than 3
		// and price of btc is less than 50
		// sell 50 btc
		
		this.trader = trader;
		
		double btcPrice = 4.00; // replace with value from coinPrices
		double adaPrice = 30.34; // replace with value from coinPrices
		
		if ((adaPrice > 3) && (btcPrice < 50)) {
			coinTraded = "BTC";
			action = "Sell";
			quantity = 50;
			price = quantity * btcPrice;
		}
		else {
			coinTraded = "None";
			action = "Fail";
			quantity = 0;
			price = 0;
		}
		TradeResult result = new TradeResult(trader, strategy, coinTraded, action, quantity, price);
		return result;
	}

	@Override
	public String getStrategyName() {
		return "Strategy-C";
	}

}
