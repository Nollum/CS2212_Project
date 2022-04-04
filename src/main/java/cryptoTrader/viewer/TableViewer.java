package cryptoTrader.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.MainUI;
import cryptoTrader.tradeResult.TradeResult;

/**
 * TableViewer class composes the table panel of the application consisting of completed trade results
 * 
 * @author Professor Kontogiannis
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */
public class TableViewer implements ViewerInterface{

	
	/**
	 * createTableOutput method extracts the broker name, strategy, coin(s) traded, action, quantity, price, 
	 * and date of the trade from the TradeResult resultsList in order to display them on the "Trader Actions" log
	 * 
	 * @param ArrayList<TradeResult> resultsList
	 * @return JComponent
	 */
	@Override
	public JComponent createOutput(ArrayList<TradeResult> resultsList) {
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		int colSize = columnNames.length;
		int rowSize = resultsList.size();
		Object[][] myData = addResultsToTable(resultsList, rowSize, colSize);

		JTable table = new JTable(myData, columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		
		return scrollPane;
	}
	
	private Object[][] addResultsToTable(ArrayList<TradeResult> resultsList, int rowSize, int colSize){
		Object[][] myData = new Object[rowSize][colSize];

		int row = 0;
		for (TradeResult result : resultsList) {
			// fill in method for creating the data object
			myData[row][0] = result.getTraderName();
			myData[row][1] = result.getStrategyName();
			myData[row][2] = result.getCoinTraded();
			myData[row][3] = result.getAction();
			myData[row][4] = result.getQuantity();
			myData[row][5] = result.getPrice();
			myData[row][6] = result.getDate();
			row = row + 1;
		}
		
		return myData;
	}

	
}
