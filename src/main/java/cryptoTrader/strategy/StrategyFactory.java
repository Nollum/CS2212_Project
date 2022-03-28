package cryptoTrader.strategy;


/**
 * This strategy factory returns the appropriate strategy type depending on the parameter provided by the client
 * @author Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov 
 *
 */
public class StrategyFactory {

	/**
	 * Returns the instance of the appropriate strategy according to the user selection
	 * @param strategyName This is the name of the Strategy selected by the user 
	 * @return StrategyInterface This returns the appropriate strategy object for the strategy name provided
	 */
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
