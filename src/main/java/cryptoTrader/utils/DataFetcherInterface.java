package cryptoTrader.utils;

public interface DataFetcherInterface {
	
	public double getPriceForCoin(String id, String date);
	
	public double getMarketCapForCoin(String id, String date);
	
	public double getVolumeForCoin(String id, String date);
	
	
}
