package cryptoTrader.utils;

public interface AvailableCryptoListInterface {
	public void call();
	
	public String[] getAvailableCryptos();
	
	public String getCryptoID(String cryptoName);
}
