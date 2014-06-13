package catalogScrapper;

import java.io.IOException;
import java.util.List;

import mercadonaStorage.*;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class CatalogScraperMercadona {


	private ApacheConnector apacheConnector;
	private final String CATALOG_PAGE = "https://www.mercadona.es/ns/menu.php";
	private final String BASE_PAGE = "https://www.mercadona.es/";
	private String username;
	private String password;
	private String username2;
	private String password2;
	
	
	public CatalogScraperMercadona(){
		this.apacheConnector = null;
		username=null;
		password=null;
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean login(){
		this.apacheConnector = new ApacheConnector();
		if(username!= null && password!= null){
			return apacheConnector.login(username, password);
		}else{
			System.out.println("Username or password Wrong");
			return false;
		}
	}
	
	public void logout(){
		this.apacheConnector = null;
	}
	// JSOUP - Get the catalog without products
		private MercadonaStorage getSupermarketDataStorage(){
			Response httpReponse = null;
			Connection httpConnection = Jsoup.connect(CATALOG_PAGE);
			MercadonaStorage supermarket = new MercadonaStorage();
			try {
				
				httpReponse = httpConnection.execute();
				Element ulmenu = httpReponse.parse().getElementsByClass("ulmenu").first();
				Elements lis = ulmenu.getElementsByTag("li");

				for(Element li:lis){				
					if(li.attr("class").equals("linivel1  display_block") && !li.getElementsByTag("a").first().html().equals("TOP AHORRO")){
						if(li.getElementsByTag("ul").size() == 0){
							String productLevel1Link = li.getElementsByTag("a").first().attr("href");
							String mainCategory = li.getElementsByTag("a").first().html();
							MercadonaProductsTable table = new MercadonaProductsTable(mainCategory, "", "", "", productLevel1Link);
							supermarket.addProduct(table);
						}else{
							processLiTag(li, supermarket);
						}
					}
				}
				return supermarket;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return supermarket;
		}
		
	// Process a list tag
	private void processLiTag(Element li, MercadonaStorage supermarket) {
		String mainCategory = li.getElementsByTag("a").first().html();
		// Menu level 2		
		Elements liTagsLevel2 = li.getElementsByTag("ul").first().getElementsByTag("li");
		for (Element liLevel2 : liTagsLevel2) {
			String categoryLevel2 = liLevel2.getElementsByTag("a").first().html();
			if (liLevel2.getElementsByTag("ul").size() == 0) {
				// Menu level 2
				String productLevel2Link = liLevel2.getElementsByTag("a").first().attr("href");
				MercadonaProductsTable table = new MercadonaProductsTable(mainCategory, categoryLevel2, "", "", productLevel2Link);
				supermarket.addProduct(table);
			} else {
				// Menu level 3
				Elements liTagsLevel3 = liLevel2.getElementsByTag("ul").first().getElementsByTag("li");
				for(Element liLevel3: liTagsLevel3){
					String categoryLevel3 = liLevel3.getElementsByTag("a").first().html();
					if (liLevel3.getElementsByTag("ul").size() == 0) {
						// Menu level 3
						String productLevel3Link = liLevel3.getElementsByTag("a").first().attr("href");
						MercadonaProductsTable table = new MercadonaProductsTable(mainCategory, categoryLevel2, categoryLevel3, "", productLevel3Link);
						supermarket.addProduct(table);
					}else{
						//Menu level 4
						Elements liTagsLevel4 = liLevel3.getElementsByTag("ul").first().getElementsByTag("li");
						for(Element liLevel4: liTagsLevel4){
							String categoryLevel4 = liLevel4.getElementsByTag("a").first().html();
							String productLevel4Link = liLevel4.getElementsByTag("a").first().attr("href");
							MercadonaProductsTable table = new MercadonaProductsTable(mainCategory, categoryLevel2, categoryLevel3, categoryLevel4, productLevel4Link);
							supermarket.addProduct(table);
						}
					}
				}
			}
		
		}

	}

	// Fill the catalog with products
/*	public MercadonaStorage getMercadonaProductsCatalogApache(){
		MercadonaStorage supermarketStorage = this.getSupermarketDataStorage(); 
		Integer totalTables = supermarketStorage.getProducts().size();
		Integer actualTable = 1;
		System.out.println("Number of tables to process: "+(totalTables));
		for(MercadonaProductsTable table:  supermarketStorage.getProducts()){
			System.out.println(String.format("%.2f",Double.valueOf((100.0*((actualTable*1.0)/totalTables))))+" %");
			actualTable = actualTable+1;
			
			String url = BASE_PAGE +"ns/"+table.getUrl();

			try {
				int random = (int)(Math.random()*10 + 1)*1000;
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			processTable(url, table);
			if(!continueDownloading)
				break;
		}
		return supermarketStorage;
	}*/
	
	
	
	
	
	// Fill the catalog with products
	public MercadonaStorage getMercadonaProductsCatalogApache(){
		MercadonaStorage supermarketStorage = this.getSupermarketDataStorage(); 
		int total = supermarketStorage.getProducts().size();
		Integer one  = (Integer) total/3;
		Integer two  = one*2;
		
		List<MercadonaProductsTable> tableSetOne = supermarketStorage.getProducts().subList(0, one);
		List<MercadonaProductsTable> tableSetTwo = supermarketStorage.getProducts().subList(one, two);
		List<MercadonaProductsTable> tableSetThree = supermarketStorage.getProducts().subList(two, total);

		String usernameOne = this.username;
		String passwordOne = this.password;
		
		System.out.println("Tale one of three: "+ tableSetOne.size());
		Integer actualTable = 1;
		Integer totalTables = tableSetOne.size();
		
		
		try {
			int random = (int)(Math.random()*10 + 1)*1000;
			Thread.sleep(random);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.login();
		
		for(MercadonaProductsTable table:  tableSetOne){
			System.out.println(String.format("%.2f",Double.valueOf((100.0*((actualTable*1.0)/totalTables))))+" %");
			actualTable = actualTable+1;
			
			String url = BASE_PAGE +"ns/"+table.getUrl();
			if(!continueDownloading)
				break;
			try {
				int random = (int)(Math.random()*5 + 1)*1000;
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			processTable(url, table);
			if(!continueDownloading)
				break;
		}
		continueDownloading = true;
		this.logout();
		this.setUsername(username2);
		this.setPassword(password2);
		this.login();
	
		
		System.out.println("Tale two of three: "+ tableSetTwo.size());
		actualTable = 1;
		totalTables = tableSetTwo.size();
		for(MercadonaProductsTable table:  tableSetTwo){
			System.out.println(String.format("%.2f",Double.valueOf((100.0*((actualTable*1.0)/totalTables))))+" %");
			actualTable = actualTable+1;
			
			String url = BASE_PAGE +"ns/"+table.getUrl();
			if(!continueDownloading)
				break;
			try {
				int random = (int)(Math.random()*5 + 1)*1000;
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			processTable(url, table);
			if(!continueDownloading)
				break;
		}
		
		continueDownloading = true;
		this.logout();
		this.setUsername(usernameOne);
		this.setPassword(passwordOne);
		this.login();
		
		System.out.println("Tale three of three: "+ tableSetThree.size());
		actualTable = 1;
		totalTables = tableSetThree.size();
		for(MercadonaProductsTable table:  tableSetThree){
			System.out.println(String.format("%.2f",Double.valueOf((100.0*((actualTable*1.0)/totalTables))))+" %");
			actualTable = actualTable+1;
			
			String url = BASE_PAGE +"ns/"+table.getUrl();
			if(!continueDownloading)
				break;
			try {
				int random = (int)(Math.random()*5 + 1)*1000;
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			processTable(url, table);
			if(!continueDownloading)
				break;
		}
		
		
		return supermarketStorage;
	}
	
	public String getUsername2() {
		return username2;
	}


	public void setUsername2(String username2) {
		this.username2 = username2;
	}


	public String getPassword2() {
		return password2;
	}


	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public static Boolean continueDownloading = true;
	// Process a table of products taking the most important things of them.
	private int processTable(String url, MercadonaProductsTable table){
		
		String html = this.apacheConnector.getPageContent(url);
		Element doc = Jsoup.parse(html);
		Elements scripts = doc.getElementsByTag("script");
		String scriptWithProducts = null;
		int numberOfProductsOnTheTable = 0;
		try{
		// Extracting the link with the products info.
		for(Element script:scripts){
			if(script.html().contains("function MuestraPagina")){
				scriptWithProducts = script.html();
				break;
			}
		}
		
		// Processing the String with the info
		for(String productLine : scriptWithProducts.split(";")){
			if(productLine.contains("InsertaLinea")){
				numberOfProductsOnTheTable++;
				MercadonaProduct product = new MercadonaProduct();
				product.parseProductFromString(productLine);
				table.addProductToTable(product);
			}
		}
		
		// Check if exists another page and process it.
		String posicionString = url.substring(url.length()-2, url.length());
		Integer posicion = new Integer(posicionString);
		Integer nuevaPosicion = posicion + 20;
		
		if(	doc.html().contains("&posicion_actual=20") && !url.contains("&posicion_actual=")){
			String nextPageLink = url+"&posicion_actual=20";
			numberOfProductsOnTheTable  += this.processTable(nextPageLink, table);
		}else if(doc.html().contains("&posicion_actual="+nuevaPosicion.toString()) && url.contains("&posicion_actual="+posicion.toString())){
			String nextPageLink = url.replace(posicionString, nuevaPosicion.toString());
			numberOfProductsOnTheTable  += this.processTable(nextPageLink, table);
		}
		
		table.setNumberOfProducts(numberOfProductsOnTheTable);
		
		}catch(Exception e){
			System.out.println(e.toString());
			continueDownloading = false;
		}
		return numberOfProductsOnTheTable ;
	}
	
}
