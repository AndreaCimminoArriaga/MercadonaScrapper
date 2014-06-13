package tests;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import payment.*;
import payment.PaymentMethod.OutOfProduct;
import payment.PaymentMethod.Sending;
import cartScrapper.CardScraperUtils;
import cartScrapper.CartScraperMercadona;
import mercadonaStorage.MercadonaProduct;


public class TestCartScraper {


	public static void main(String[] args) {
		// Payment info
		/*SupermarketCard supermarketCard = new SupermarketCard();
		supermarketCard.setSupermarketCard("8060-yyyy-yyyy-yyyy", "Sherlock");
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCard("xxxx-xxxx-xxxx-xxxx", "John Doe", "06", "2016");
		Date theDate = CardScraperUtils.stringToDate("16/5/2014");
		// Payment method
		PaymentMethod payment = new PaymentMethod();
		payment.setCashPayment(theDate, Sending.EVENING, OutOfProduct.NOT_SEND);
		
		// Cart
		String line = "InsertaLinea(11858,3,\"\",\"CAFE CAPSULA (COMPATIBLE CON CAFETERA SISTEMA TASSIMO) JACOBS XL CREMA ***NOVEDAD***, TASSIMO, PAQUETE 16 u - 132.80 g\",3.45,2,574.0317,0,1,true,11189,\"\",\"1 KILO: 25,98 Euros\",\"1 KILO: 4.323 Ptas\")";
		MercadonaProduct product2 = new MercadonaProduct();
		product2.parseProductFromString(line);
		
		String line3 = "InsertaLinea(11858,3,\"\",\"CAFE ALGO, TASSIMO, PAQUETE 16 u - 132.80 g\",3.45,2,574.0317,0,1,true,11189,\"\",\"1 KILO: 25,98 Euros\",\"1 KILO: 4.323 Ptas\")";
		MercadonaProduct product3 = new MercadonaProduct();
		product3.parseProductFromString(line3);
		
		Map<MercadonaProduct, String> virtualCart = new HashMap<MercadonaProduct,String>();
		virtualCart.put(product2, "2");
		virtualCart.put(product3, "3");
		
		// Execute
		CartScraperMercadona scraperM = new CartScraperMercadona();
		scraperM.setUsername("TONICANCELA");
		scraperM.setPassword("descent10");
		System.out.println(scraperM.processCart(virtualCart, payment, false));*/
		CartScraperMercadona scraperM = new CartScraperMercadona();
		scraperM.setUsername("ANDREA CIMMINO");
		scraperM.setPassword("ascension00");
		System.out.println(scraperM.availableDatesToDelivery());
		//System.out.println(scraperM.availableDayMoment( theDate, Sending.EVENING));
	}

}
