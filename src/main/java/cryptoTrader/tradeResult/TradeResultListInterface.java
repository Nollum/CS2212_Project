package cryptoTrader.tradeResult;

/**
 * TradeResultListInterface is used by TradeResultList
 * The addResult(TradeResult result) method is overridden by TradeResultList
 * @author Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov 
 */

public interface TradeResultListInterface {

	/**
	 * addResult method adds a new TradeResult to the TradeResultList
	 * @param TradeResult result
	 */
	public void addResult(TradeResult result);
}
