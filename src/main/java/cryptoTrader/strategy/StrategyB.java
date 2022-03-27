package cryptoTrader.strategy;

import cryptoTrader.tradeResult.TradeResult;

public class StrategyB implements StrategyInterface {

	String trader;
	String strategy;
	String coinTraded;
	String action = null;
	int quantity;
	double price;
	
	@Override
	public TradeResult performTrade(String trader, String[] coinList, double[] coinPrices) {
		// TODO Auto-generated method stub
		// if the price of ADA is less than or equal to $2
		// and the price of ETH is less than $3500
		// then buy ADA coins worth of $1000
		
		this.trader = trader;
		
		double ethPrice = 4.00; // replace with value from coinPrices
		double adaPrice = 30.34; // replace with value from coinPrices
		
		if ((adaPrice < 2) && (ethPrice < 3500)) {
			coinTraded = "ADA";
			action = "Buy";
			quantity = (int) (1000 / adaPrice);
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
		return "Strategy-B";
	}

}
