package cryptoTrader.utils;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * DataFetcher class connects to the CoinGecko API to extract crypto data for the application
 * 
 * @author Professor Kontogiannis
 * @author Rustam Mamedov, Shruthi Sundararaman, Hanniya Zohdi
 */

public class DataFetcher {
	
	private static DataFetcher instance;
	
	/**
	 * getInstance creates a new instance of DataFetcher
	 * @return DataFetcher
	 */
	public static DataFetcher getInstance() {
		if (instance == null)
			instance = new DataFetcher();

		return instance;
	}

	/**
	 * getDataForCrypto method makes an HTTP request call to the CoinGecko API
	 * @param String id
	 * @param String date
	 * @return JsonObject
	 */
	private JsonObject getDataForCrypto(String id, String date) {

		String urlString = String.format(
				"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	
	/**
	 * getPriceForCoin returns the price data of a crypto coin on a certain day
	 * @param String id
	 * @param String date
	 * @return double price
	 */
	public double getPriceForCoin(String id, String date) {
		double price = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			price = currentPrice.get("cad").getAsDouble();
		}
		
		return price;
	}
	
	/**
	 * getMarketCapForCoin returns the market data of a crypto coin on a certain day
	 * @param String id
	 * @param String date
	 * @return double marketCap
	 */
	public double getMarketCapForCoin(String id, String date) {
		double marketCap = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
			marketCap = currentPrice.get("cad").getAsDouble();
		}
		
		return marketCap;
	}
	
	/**
	 * getVolumeForCoin returns the volume data of a crypto coin on a certain day
	 * @param String id
	 * @param String date
	 * @return double volume
	 */
	public double getVolumeForCoin(String id, String date) {
		double volume = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
			volume = currentPrice.get("cad").getAsDouble();
		}
		
		return volume;
	}
	
	public static void main(String[] args) {
		DataFetcher fetcher = new DataFetcher();
		double price = fetcher.getPriceForCoin("ethereum", "08-09-2021");
		double marketCap = fetcher.getMarketCapForCoin("XRP", "08-09-2021");
		double volume = fetcher.getVolumeForCoin("XRP", "08-09-2021");
		
		System.out.println("xrp=>\tPrice: " + price + 
								"\n\t\tMarket Cap: " + marketCap + 
								"\n\t\tVolume: "+volume);
		
	}
}
