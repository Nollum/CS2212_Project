package cryptoTrader.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import cryptoTrader.tradeResult.TradeResult;

/**
 * HistogramViewer class composes the graph panel of the application consisting of completed trade results
 * 
 * @author Professor Kontogiannis
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class HistogramViewer implements ViewerInterface{
	
	
	
	/**
	 * createBar method takes the parsed results of the resultsList and creates the chart panel on the front-end
	 * @param ArrayList<TradeResult> resultsList
	 * @return JComponent
	 */
	@Override
	public JComponent createOutput(ArrayList<TradeResult> resultsList) {
		
		// parse resultsList
		HashMap<ArrayList<String>, Integer> parsedData = parseResults(resultsList);
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (ArrayList<String> key : parsedData.keySet()) {
			dataset.setValue(parsedData.get(key), key.get(0), key.get(1));
		}

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		NumberAxis rangeAxis = new NumberAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
//		MainUI.getInstance().updateStats(chartPanel);
		return chartPanel;
	}
	
	/**
	 * parseResults method extracts the broker name and their associated strategy from the resultsList,
	 * parsing them into a HashMap for the histogram
	 * @param resultsList
	 * @return HashMap<ArrayList<String>, Integer>
	 */
	private static HashMap<ArrayList<String>, Integer> parseResults(ArrayList<TradeResult> resultsList) {
		HashMap<ArrayList<String>, Integer> data = new HashMap<ArrayList<String>, Integer>();
		
		for (TradeResult result : resultsList) {
			String broker = result.getTraderName();
			String strategy = result.getStrategyName();
			
			ArrayList<String> key = new ArrayList<String>();
			key.add(broker);
			key.add(strategy);
			
			if (data.containsKey(key)) {
				if (!result.getAction().equals("Fail")) {
					data.put(key, data.get(key) + 1);
				}
			} else {
				if (!result.getAction().equals("Fail")) {
					data.put(key, 1);
				} else {
					data.put(key, 0);
				}
			}
		}		
		return data;
	}
}
