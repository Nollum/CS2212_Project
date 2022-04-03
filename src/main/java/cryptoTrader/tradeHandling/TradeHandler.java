package cryptoTrader.tradeHandling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import cryptoTrader.gui.MainUI;
import cryptoTrader.strategy.StrategyFactory;
import cryptoTrader.tradeResult.TradeResult;
import cryptoTrader.tradeResult.TradeResultList;
import cryptoTrader.tradingBroker.TradingBroker;
import cryptoTrader.tradingBroker.TradingBrokerList;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;

/*
 * TradeHandler class handles the user's actions on the GUI
 * This includes initiating a trade, adding brokers, removing brokers, changing strategies for brokers,
 * adding/removing crypto tickers, and more.
 * 
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class TradeHandler {

	private static TradeHandler instance;
	private DataFetcher dataFetcher;
	private TradingBrokerList tradingBrokerList;
	private TradeResultList tradeResultList;
	private AvailableCryptoList availableCryptos;

	/*
	 * Method creates an instance of TradeHandler if it is not active yet
	 */
	public static TradeHandler getInstance() {
		if (instance == null)
			instance = new TradeHandler();

		return instance;
	}
	
	/*
	 * TradeHandler constructor creates an instance of the DataFetcher API, TradingBrokerList, TradeResultList, and AvailableCryptoList
	 * A success string is printed if the TradeHandler is successfully setup
	 */
	private TradeHandler() {
		dataFetcher = DataFetcher.getInstance();
		tradingBrokerList = TradingBrokerList.getInstance();
		tradeResultList = TradeResultList.getInstance();
		availableCryptos = AvailableCryptoList.getInstance();
		// Debug message
		System.out.println("Trade Handler is setup and active");

	}
	
	
	/*
	 * @param ArrayList<String> brokers
	 * @return HashSet<String>
	 */
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
	
	
	/*
	 * @param ArrayList<String[]> coinMatrix
	 * @return HashSet<String>
	 */
	private static HashSet<String> consolidateCoins(ArrayList<String[]> coinMatrix) {
		HashSet<String> result = new HashSet<String>();
		for (String[] coinList : coinMatrix) {
			for (String coin : coinList) {
				result.add(coin);
			}
		}
		return result;
	}
	
//	private static fetchPrices()
	
	
	/*
	 * initiateTrade() method executes trades depending on broker, strategy, crypto coins, and strategy
	 * The method grabs the list of brokers and their associated crypto coins, prices, current date, and strategy to perform the trades.
	 * @param ArrayList<String> brokers
	 * @param ArrayList<String[]> coinMatrix
	 * @param ArrayList<String> strategies
	 */
	public void initiateTrade(ArrayList<String> brokers, ArrayList<String[]> coinMatrix, ArrayList<String> strategies) {
//		System.out.println(brokers.get(0) + " " + coins.get(0)[0] + " " + strategies.get(0));
		
		HashSet<String> duplicateBrokers = getDuplicates(brokers);
		
		if (duplicateBrokers.size() != 0) {
			brokers.removeAll(duplicateBrokers);
			MainUI.getInstance().duplicateError(duplicateBrokers);
		}
		
		StrategyFactory stratFact = new StrategyFactory();
		
		for (int index = 0; index < brokers.size(); index++) {
			tradingBrokerList.addBroker(new TradingBroker(brokers.get(index), 
										coinMatrix.get(index), 
										stratFact.createStrategy(strategies.get(index))));
		}
		
		HashSet<String> consolidatedCoinList = consolidateCoins(coinMatrix);
		
		HashMap<String, Double> coinPrices = new HashMap<String, Double>();
		
		//https://www.tutorialkart.com/java/how-to-get-current-date-in-mm-dd-yyyy-format-in-java/
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String currentDate = dateObj.format(formatter);
        
		for (String coin : consolidatedCoinList) {
			double price = dataFetcher.getPriceForCoin(availableCryptos.getCryptoID(coin), currentDate);
			
			//DEBUGGING STATEMENT
			System.out.println(coin + ": " + price);
			
			coinPrices.put(coin, price);
		}
		
		for (TradingBroker broker : tradingBrokerList.getBrokers()) {
			
			// to only pass the appropriate coin prices to the broker's strategy
			HashMap<String, Double> appropriateCoins = new HashMap<String, Double>();	
			for (String coin : broker.getCoinList()) {
				appropriateCoins.put(coin, coinPrices.get(coin));
			}
			
			TradeResult result = broker.getStrategy().performTrade(currentDate, broker.getCoinList(), appropriateCoins);
			System.out.println(result.getCoinTraded() + " " + result.getAction() + " " + result.getQuantity());
			tradeResultList.addResult(result);
		}

	}
	
	// Debugging purposes only
	public static void main(String[] args) {
		TradeHandler instance = TradeHandler.getInstance();
		System.out.println(instance.availableCryptos.getCryptoID("BTC"));
//		System.out.println("Price: " + instance.fetchCoinData(null));
	}
	
	
	/*
	 * Method is called every time the list of brokers is updated, whether a broker is added, deleted, or edited.
	 * The original tradingBrokerList is cleared and a new brokerList is created with the updated client info
	 * @param ArrayList<String> brokers
	 * @param TradingBrokerList tradingBrokerList
	 * @param ArrayList<String[]> coinMatrix
	 * @param ArrayList<String> strategies
	 */
	public void updateBrokers(ArrayList<String> brokers, TradingBrokerList tradingBrokerList, ArrayList<String[]> coinMatrix, 
			ArrayList<String> strategies) {
		
		// clearing the brokerlist to create the new updated list
		tradingBrokerList.clear();
		
		StrategyFactory stratFact = new StrategyFactory();
		
		for (int i = 0; i < brokers.size(); i++) {
			tradingBrokerList.addBroker(new TradingBroker(brokers.get(i), 
										coinMatrix.get(i), 
										stratFact.createStrategy(strategies.get(i))));
		}
		
		
		
	}
}
