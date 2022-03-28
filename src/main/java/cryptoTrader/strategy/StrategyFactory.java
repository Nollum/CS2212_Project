package cryptoTrader.strategy;

public class StrategyFactory {

	public StrategyInterface createStrategy(String strategyName) {
		
		if (strategyName == "Strategy-A") {
			return StrategyA.getInstance();
		}
		else if (strategyName == "Strategy-B"){
			return StrategyB.getInstance();
		}
		else if (strategyName == "Strategy-A") {
			return StrategyC.getInstance();
		}
		else {
			return null;
		}
		
	}
	
}
