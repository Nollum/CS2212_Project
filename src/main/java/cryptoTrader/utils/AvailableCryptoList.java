package cryptoTrader.utils;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * AvailableCryptoList class connects with the crypto API to find available crypto for trade
 * 
 * @author Professor Kontogiannis
 */

public class AvailableCryptoList implements AvailableCryptoListInterface {
	private static AvailableCryptoList instance = null;
	
	private Map<String, String> availableCryptosMap = new HashMap<>();
	private List<String> availableCryptosList = new ArrayList<>();
	
	/**
	 * getInstance method creates an instance of AvailableCryptoList
	 * @return AvailableCryptoList
	 */
	public static AvailableCryptoList getInstance() {
		if (instance == null)
			instance = new AvailableCryptoList();
		
		return instance;
	}
	
	/**
	 * AvailableCryptoList constructor calls the findAvailableCryptos method
	 */
	private AvailableCryptoList() {
		findAvailableCryptos();
	}
	

	public void call() {
		String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=VNEY4VV2AWF1EB51";
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
				System.out.println(inline);
//				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
//				int size = jsonArray.size();
//				
//				String name, id;
//				for (int i = 0; i < size; i++) {
//					JsonObject object = jsonArray.get(i).getAsJsonObject();
//					name = object.get("name").getAsString();
//					id = object.get("id").getAsString();
//					
//					availableCryptosMap.put(name, id);
//					availableCryptosList.add(name);
//				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	
	/**
	 * findAvailableCryptos method makes a HTTP connection request with the CoinGecko API.
	 * It extracts the available crypto coins information, adding their ID & ticker to the availableCryptosMap.
	 * It adds the crypto coin name to the availableCryptosList.
	 */
	private void findAvailableCryptos() {

		String urlString = 
				"https://api.coingecko.com/api/v3/coins/markets" + 
						"?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
//		ALPHAVANTAGE API KEY = VNEY4VV2AWF1EB51
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
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				
				String id, symbol, name;
				for (int i = 0; i < size; i++) {
					JsonObject object = jsonArray.get(i).getAsJsonObject();
					id = object.get("id").getAsString();
					symbol = object.get("symbol").getAsString().toUpperCase();
					name = object.get("name").getAsString();

					availableCryptosMap.put(symbol, id);
					availableCryptosList.add(name);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * getAvailableCryptos returns the list of available crypto names from the previous API call
	 * @return String[] of the available crypto names called from the API 
	 */
	public String[] getAvailableCryptos() {
		return availableCryptosList.toArray(new String[availableCryptosList.size()]);
	}
	
	/**
	 * getCryptoID gets the associated crypto ID and ticker from the cryptoName
	 * @param String cryptoName
	 * @return String
	 */
	public String getCryptoID(String cryptoName) {
		return availableCryptosMap.get(cryptoName);
	}

}
