package cryptoTrader.strategy;

public class StrategyFactory {

	public StrategyInterface createStrategy(String strategyName) {
		
		if (strategyName == "Strategy-A") {
			return new StrategyA();
		}
		else if (strategyName == "Strategy-B"){
			return new StrategyB();
		}
		else if (strategyName == "Strategy-A") {
			return new StrategyC();
		}
		else {
			return null;
		}
		
	}
	
}
