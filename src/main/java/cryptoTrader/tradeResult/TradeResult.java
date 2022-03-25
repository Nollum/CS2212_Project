package cryptoTrader.tradeResult;

public class TradeResult {

	String trader; // update with Broker
	String strategy;
	String coinTraded;
	String action;
	int quantity;
	float price;
	java.util.Date date;
	
	public TradeResult(String trader, String strategy, String coinTraded, 
			String action, int quantity, float price) 
	{
		this.trader = trader;
		this.strategy = strategy;
		this.coinTraded = coinTraded;
		this.action = action;
		this.quantity = quantity;
		this.price = price;
	}
	
}
