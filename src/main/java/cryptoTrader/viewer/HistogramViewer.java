package cryptoTrader.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

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
import org.jfree.chart.axis.NumberAxis;
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

public class HistogramViewer {
	
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
	
	public JComponent createBar(ArrayList<TradeResult> resultsList) {
		
		// parse resultsList
		HashMap<ArrayList<String>, Integer> parsedData = parseResults(resultsList);
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		Those are hard-coded values!!!! 
//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		
		for (ArrayList<String> key : parsedData.keySet()) {
			dataset.setValue(parsedData.get(key), key.get(0), key.get(1));
		}
//		dataset.setValue(6, "Trader-1", "Strategy-A");
//		dataset.setValue(5, "Trader-2", "Strategy-B");
//		dataset.setValue(0, "Trader-3", "Strategy-E");
//		dataset.setValue(1, "Trader-4", "Strategy-C");
//		dataset.setValue(10, "Trader-5", "Strategy-D");

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
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
//		MainUI.getInstance().updateStats(chartPanel);
		return chartPanel;
	}

}
