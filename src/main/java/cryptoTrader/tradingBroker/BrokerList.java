package cryptoTrader.tradingBroker;
import cryptoTrader.strategy.StrategyInterface;


public interface BrokerList {
	
	void createBrokers(String brokerName, String[] coinList, StrategyInterface strategy);
	
	TradingBroker[] getBrokers();

}

