package cryptoTrader.tradeHandling;

import java.util.ArrayList;
import java.util.HashSet;

import cryptoTrader.gui.MainUI;
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
	
	
	private static HashSet<String> getDuplicates(ArrayList<String> brokers) {
		HashSet<String> duplicates = new HashSet<String>();
		for(int i = 0; i < brokers.size(); i++) {
			for(int j = i + 1; j < brokers.size(); j++) {
		  		if(j != i && brokers.get(j).equals(brokers.get(i)) ) {
		  			duplicates.add(brokers.get(i));
				}
			}
		}
		return duplicates;
	}
	
	
	private static HashSet<String> consolidateCoins(ArrayList<String[]> coinMatrix) {
		HashSet<String> result = new HashSet<String>();
		for (String[] coinList : coinMatrix) {
			for (String coin : coinList) {
				result.add(coin);
			}
		}
		return result;
	}
	
	
	public void initiateTrade(ArrayList<String> brokers, ArrayList<String[]> coinMatrix, ArrayList<String> strategies) {
//		System.out.println(brokers.get(0) + " " + coins.get(0)[0] + " " + strategies.get(0));
		
		HashSet<String> duplicateBrokers = getDuplicates(brokers);
		
		if (duplicateBrokers.size() != 0) {
			brokers.removeAll(duplicateBrokers);
			MainUI.getInstance().duplicateError(duplicateBrokers);
		}
		
		
		for (int i = 0; i < brokers.size(); i++) {
			System.out.println(i + ": " + brokers.get(i));
		}
		
		StrategyFactory stratFact = new StrategyFactory();
		
		for (int index = 0; index < brokers.size(); index++) {
			tradingBrokerList.addBroker(new TradingBroker(brokers.get(index), 
										coinMatrix.get(index), 
										stratFact.createStrategy(strategies.get(index))));
		}
		
		HashSet<String> consolidatedCoinList = consolidateCoins(coinMatrix);
		
//		System.out.println(consolidatedCoinList);
		
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
	
	
	
	public void updateBrokers(ArrayList<String> brokers, TradingBrokerList tradingBrokerList, ArrayList<String[]> coinMatrix, ArrayList<String> strategies) {
		
		// clearing the brokerlist to create the new updated list
		tradingBrokerList.clear();
		
		StrategyFactory stratFact = new StrategyFactory();
		
		for (int i = 0; i < brokers.size(); i++) {
			tradingBrokerList.addBroker(new TradingBroker(brokers.get(i), 
										coinMatrix.get(i), 
										stratFact.createStrategy(strategies.get(i))));
		}
		
	}
	
	
	public void addAndEditBrokers(TradingBrokerList tradingBrokerList) {
		
	}
	
	
	public void removeBrokers(TradingBrokerList tradingBrokerList) {
		
		// delete the brokerlist to create the new updated list
		
		
	}
	
	
	
	
}
