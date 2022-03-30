package cryptoTrader.tradeHandling;

import java.util.ArrayList;

import cryptoTrader.strategy.StrategyFactory;
import cryptoTrader.tradeResult.TradeResultList;
import cryptoTrader.tradingBroker.TradingBroker;
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
		dataFetcher = DataFetcher.getInstance();
		tradingBrokerList = TradingBrokerList.getInstance();
		tradeResultList = TradeResultList.getInstance();
		// Debug message
		System.out.println("Trade Handler is setup and active");

	}
	
	
	public void initiateTrade(ArrayList<String> brokers, ArrayList<String[]> coins, ArrayList<String> strategies) {
//		System.out.println(brokers.get(0) + " " + coins.get(0)[0] + " " + strategies.get(0));
		
		StrategyFactory stratFact = new StrategyFactory();
		
		for (int index = 0; index < brokers.size(); index++) {
			tradingBrokerList.addBroker(new TradingBroker(brokers.get(index), 
										coins.get(index), 
										stratFact.createStrategy(strategies.get(index))));
		}
		
		// testing
//		ArrayList<TradingBroker> brokerList = tradingBrokerList.getBrokers();
//		
//		for (TradingBroker broker : brokerList) {
//			System.out.println(broker.getBrokerName());
//		}
		
		
		
		// VALIDATE CRYPTO LIST (AvailableCryptoList)
		// CREATE A MAP FOR COIN PRICES (CoinName -> Price)
		// ITERATE OVER THE UPDATED BROKER LIST
			// tradeResult = BROKER.performTrade()
			// tradeResultList.add(tradeResult)
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
