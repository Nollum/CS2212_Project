package cryptoTrader.viewer;

import java.util.ArrayList;
import javax.swing.JComponent;

import cryptoTrader.gui.MainUI;
import cryptoTrader.tradeResult.TradeResult;

/**
 * DataVisualizationCreator class composes the panel of the GUI consisting of trading data
 * 
 * @author Professor Kontogiannis
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class DataVisualizationCreator implements DataVisualizationCreatorInterface {
	
	/**
	 * createCharts method creates the table and histogram viewer on the front end and adds the resultsList
	 * @param ArrayList<TradeResult> resultsList
	 */
	public void createCharts(ArrayList<TradeResult> resultsList) {
		
		ViewerInterface tableViewer = new TableViewer();
		JComponent table = tableViewer.createOutput(resultsList);

		ViewerInterface hist = new HistogramViewer();
		JComponent chart = hist.createOutput(resultsList);
		
		JComponent[] components = {table, chart};

		MainUI.getInstance().updateStats(components);
	}

	


}
