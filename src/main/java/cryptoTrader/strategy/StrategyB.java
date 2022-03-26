package cryptoTrader.strategy;

import cryptoTrader.tradeResult.TradeResult;

public class StrategyB implements StrategyInterface {

	@Override
	public TradeResult performTrade(String[] coinList, float[] coinPrices) {
		// TODO Auto-generated method stub
		// if the price of ADA is less than or equal to $2
		// and the price of ETH is less than $3500
		// then buy ADA coins worth of $1000
		return null;
	}

	@Override
	public String getStrategyName() {
		return "Strategy-B";
	}

}
