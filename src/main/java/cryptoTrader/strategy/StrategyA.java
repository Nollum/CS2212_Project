package cryptoTrader.strategy;

import java.util.List;

// import org.apache.commons.lang3.ArrayUtils; --> figure out how to do this to find array element index

import cryptoTrader.tradeResult.TradeResult;
import cryptoTrader.tradeResult.TradeResultList;

public class StrategyA implements StrategyInterface {
	
	private static StrategyA instance = null;

	String strategy;
	String trader;
	String coinTraded;
	String action = null;
	int quantity;
	double price;
	
	public static StrategyA getInstance() {
		if (instance == null)
			instance = new StrategyA();

		return instance;
	}
	
	private StrategyA () {
		strategy = getStrategyName();
	}

	@Override
	public TradeResult performTrade(String trader, String[] coinList, double[] coinPrices) {
		// if the price of BTC is less than or equal to $50,000
		// and the price of ADA is more than $2
		// then buy 10 ADA coins
		
		this.trader = trader;
		
		double btcPrice = 4.00; // replace with value from coinPrices
		double adaPrice = 30.34; // replace with value from coinPrices
		
		if ((btcPrice < 50000) && (adaPrice > 2)) {
			coinTraded = "ADA";
			action = "Buy";
			quantity = 10;
			price = quantity * adaPrice;
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
		return "Strategy-A";
	}

}
