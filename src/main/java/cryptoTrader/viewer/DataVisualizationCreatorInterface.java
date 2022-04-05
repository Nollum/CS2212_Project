import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComponent;

import cryptoTrader.tradeResult.TradeResult;

/**
 * DataVisualizationCreatorInterface is used by DataVisualizationCreator
 * @author Shruthi Sundararaman, Hanniya Zohdi, Rustam Mamedov 
 */

public interface DataVisualizationCreatorInterface {

	/**
	 * createCharts method is overridden by DataVisualizationCreator
	 * @param ArrayList<TradeResult> resultsList
	 */
	public void createCharts(ArrayList<TradeResult> resultsList);
	
}
