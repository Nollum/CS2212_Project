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
import cryptoTrader.tradeResult.TradeResultListInterface;
import cryptoTrader.tradingBroker.TradingBroker;
import cryptoTrader.tradingBroker.TradingBrokerList;
import cryptoTrader.tradingBroker.TradingBrokerListInterface;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.AvailableCryptoListInterface;
import cryptoTrader.utils.DataFetcher;
import cryptoTrader.utils.DataFetcherInterface;


/**
 * TradeHandler class handles the user's actions on the GUI
 * This includes initiating a trade, adding brokers, removing brokers, changing strategies for brokers,
 * adding/removing crypto tickers, and more.
 * 
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class TradeHandler implements TradeHandlerInterface {

	private static TradeHandler instance;
	private DataFetcherInterface dataFetcher;
	private TradingBrokerListInterface tradingBrokerList;
	private TradeResultListInterface tradeResultList;
	private AvailableCryptoListInterface availableCryptos;

	/**
	 * Method creates an instance of TradeHandler if it is not active yet
	 */
	public static TradeHandler getInstance() {
		if (instance == null)
			instance = new TradeHandler();

		return instance;
	}
	
	/**
	 * TradeHandler constructor creates an instance of the DataFetcher API, TradingBrokerList, TradeResultList, and AvailableCryptoList
	 * A success string is printed if the TradeHandler is successfully setup
	 */
	private TradeHandler() {
		dataFetcher = DataFetcher.getInstance();
		tradingBrokerList = TradingBrokerList.getInstance();
		tradeResultList = TradeResultList.getInstance();
		availableCryptos = AvailableCryptoList.getInstance();
	}
		
	/**
	 * initiateTrade() method executes trades depending on broker, strategy, crypto coins, and strategy
	 * The method grabs the list of brokers and their associated crypto coins, prices, current date, and strategy to perform the trades.
	 * @param ArrayList<String> brokers
	 * @param ArrayList<String[]> coinMatrix
	 * @param ArrayList<String> strategies
	 */
	public void initiateTrade(ArrayList<String> brokers, ArrayList<String[]> coinMatrix, ArrayList<String> strategies) {
		
		updateBrokersList(brokers, tradingBrokerList, coinMatrix, strategies);		
		HashSet<String> consolidatedCoinList = consolidateCoins(coinMatrix);		
		HashMap<String, Double> coinPrices = getCoinPrices(consolidatedCoinList);
		
		for (TradingBroker broker : tradingBrokerList.getBrokers()) {
			
			// to only pass the appropriate coin prices to the broker's strategy
			HashMap<String, Double> appropriateCoinPrices = new HashMap<String, Double>();	
			for (String coin : broker.getCoinList()) {
				appropriateCoinPrices.put(coin, coinPrices.get(coin));
			}
			
			TradeResult result = broker.getStrategy().performTrade(broker.getBrokerName(), broker.getCoinList(), appropriateCoinPrices);
			if (result.getAction().equals("Fail")) {
				MainUI.getInstance().displayError("Strategy for trader " + result.getTraderName() + 
						" was not executed due to information missing and/or strategy rules not being met.");
			}
			tradeResultList.addResult(result);
		}
	}
	
	/**
	 * creates a hashset of unique coins from all the coins lists passed as a matrix
	 * @param ArrayList<String[]> coinMatrix the matrix containing lists of coins
	 * @return HashSet<String> the unique set of coins from the matrix
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
	
	/**
	 * Method is called every time the list of brokers is updated, whether a broker is added, deleted, or edited.
	 * The original tradingBrokerList is cleared and a new brokerList is created with the updated client info
	 * @param ArrayList<String> brokers
	 * @param TradingBrokerList tradingBrokerList
	 * @param ArrayList<String[]> coinMatrix
	 * @param ArrayList<String> strategies
	 */
	private void updateBrokersList(ArrayList<String> brokers, TradingBrokerListInterface tradingBrokerList, 
			ArrayList<String[]> coinMatrix, ArrayList<String> strategies) {
		
		// clearing the brokerlist to create the new updated list
		tradingBrokerList.clear();
		
		StrategyFactory stratFact = new StrategyFactory();
		
		for (int i = 0; i < brokers.size(); i++) {
			tradingBrokerList.addBroker(new TradingBroker(brokers.get(i), 
										coinMatrix.get(i), 
										stratFact.createStrategy(strategies.get(i))));
		}
				
	}
	
	/**
	 * This method fetches the prices for every coin in the coins list passed
	 * @param consolidatedCoinList the list of coins for which the prices need to be fetched
	 * @return a hashmap of each coin and its current price
	 */
	private HashMap<String, Double> getCoinPrices(HashSet<String> consolidatedCoinList){
		
		HashMap<String, Double> coinPrices = new HashMap<String, Double>();
		
		//https://www.tutorialkart.com/java/how-to-get-current-date-in-mm-dd-yyyy-format-in-java/
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String currentDate = dateObj.format(formatter);

		for (String coin : consolidatedCoinList) {
			double price = dataFetcher.getPriceForCoin(availableCryptos.getCryptoID(coin), currentDate);
			coinPrices.put(coin, price);
		}
		
		return coinPrices;
	}
	
	// Debugging purposes only
	public static void main(String[] args) {
		TradeHandler instance = TradeHandler.getInstance();
		System.out.println(instance.availableCryptos.getCryptoID("BTC"));
	}
}
