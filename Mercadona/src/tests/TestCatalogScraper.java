package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import catalogScrapper.CatalogScraperMercadona;
import catalogScrapper.SwissArmyKnife;
import mercadonaStorage.*;

public class TestCatalogScraper {

	
	public static void main(String[] args) {
		
		CatalogScraperMercadona sc = new CatalogScraperMercadona();
		String user = "PERSONAHM";
		String password = "descent10";
		
		sc.setUsername(user);
		sc.setPassword(password);
		sc.setUsername2("PERSONAHM");
		sc.setPassword2("descent10");
		
		Boolean connect = sc.login();
		System.out.println("Connecting");

		if(connect){
			System.out.println("Downloading...");
			MercadonaStorage mS = sc.getMercadonaProductsCatalogApache();
			Map<String, List<MercadonaProduct>> products = new HashMap<String, List<MercadonaProduct>>();
			System.out.println("Procesing data");
			for (MercadonaProductsTable table : mS.getProducts()) {
				String key = SwissArmyKnife.generateKey(table.getTagLevel1(),table.getTaglevel2(),table.getTagLevel3(),table.getTagLevel4());
				System.out.println(table.getProducts().size());
				products.put(key, table.getProducts());
			}
			try {
				SwissArmyKnife.createTreeDirectory("Mercadona", products);
			} catch (IOException e) {
				//e.printStackTrace();
			}
			

		}
		System.out.println("end of the process");
	}
	


}
