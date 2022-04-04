package cryptoTrader.viewer;

import java.util.ArrayList;

import javax.swing.JComponent;

import cryptoTrader.tradeResult.TradeResult;

public interface ViewerInterface {
	
	abstract JComponent createOutput(ArrayList<TradeResult> resultsList);

}
