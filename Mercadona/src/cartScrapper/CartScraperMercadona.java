package cartScrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import payment.PaymentMethod;
import payment.PaymentMethod.OutOfProduct;
import payment.PaymentMethod.Payment;
import payment.PaymentMethod.Sending;
import mercadonaStorage.MercadonaProduct;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class CartScraperMercadona {

	private final String LOGIN_PAGE = "https://www.mercadona.es/ns/entrada.php?js=-1";		
	private HtmlUnitDriver driver;
	private String username;
	private String password;
	
	public CartScraperMercadona(){
		super();
		username=null;
		password=null;
	}
	
	/*
	 * Returns a map of integer and Object, if continue anyway parameter is true:
	 * 		if the integer is 0 is a warning that the returned list of missing product to warn the user
	 * 		if the integer is 1 is an error, the date to delivery selected is not available
	 * 		if the integer is 2 the price of the cart is different from the price in the page
	 * 		if the map is empty there are no errors.
	 * 		if null then username or password are wrong
	 * if continue anyway is false the shopping will end if a product is missing
	 */
	
	
	public Map<Integer, Object> processCart(Map<MercadonaProduct,String> virtualCart, PaymentMethod payment, Boolean continueAnyway) {
		if(username==null && password == null){
			System.out.println("Wrong username or password");
			return null;
		}
		this.driver = new HtmlUnitDriver(false);
		Map<Integer, Object> errors = new HashMap<Integer,Object>();
		// Login
		this.driver.get(LOGIN_PAGE);
		WebElement inputUsername = this.driver.findElementByTagName("form").findElement(By.name("username"));
		WebElement inputPassword = this.driver.findElementByTagName("form").findElement(By.name("password"));
		WebElement inputSubmit = this.driver.findElementByTagName("form").findElement(By.id("ImgEntradaAut"));
		inputUsername.clear();
		inputPassword.clear();
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		inputSubmit.submit();
	
		// Processing the cart
		List<MercadonaProduct> missingProducts = new ArrayList<MercadonaProduct>();
		for (MercadonaProduct product : virtualCart.keySet()) {
			// Search the products:
			WebElement nombreProduct = this.driver.findElementById("busc_ref");
			WebElement button = this.driver.findElementById("imgBuscar");
			nombreProduct.clear();
			nombreProduct.sendKeys(product.getName());
			button.click();
			
			// Adding the product to the real cart
			try{
				WebElement amounth = this.driver.findElementById("cantidad");
				WebElement addButton = this.driver.findElementById("imgCesta");
				amounth.clear();
				amounth.sendKeys(virtualCart.get(product));
				addButton.click();
			}catch (Exception e){
				missingProducts.add(product);
			}
		}
		
		// If Some product dosn't exists anymore in the supermarket catalog
		// returns the list of missing products.
		if(missingProducts.size() > 0 && !continueAnyway){
			errors.put(0, missingProducts);
			
		}
		
		// Processing the order
		WebElement processOrderButton = this.driver.findElementByXPath("//div[@id='trBotones']/div[5]/a");
		processOrderButton.click();
	
		// Payment Method
		String paymentMethod = "";
		if(payment.getPayment() == Payment.CASH){
			paymentMethod = "ID_MP_1";
			WebElement paymentButton = this.driver.findElementById(paymentMethod).findElement(By.tagName("a"));
			paymentButton.click();
		}	else if (payment.getPayment() == Payment.CREDIT_CARD){
			paymentMethod = "ID_MP_3";
			WebElement paymentButton = this.driver.findElementById(paymentMethod).findElement(By.tagName("a"));
			paymentButton.click();
			// card number
			WebElement creditCard = this.driver.findElementByClassName("dades5");
			creditCard.findElement(By.id("NumeroTarjeta1")).sendKeys(payment.getCreditCard().getCardNumber().split("-")[0]);
			creditCard.findElement(By.id("NumeroTarjeta2")).sendKeys(payment.getCreditCard().getCardNumber().split("-")[1]);
			creditCard.findElement(By.id("NumeroTarjeta3")).sendKeys(payment.getCreditCard().getCardNumber().split("-")[2]);
			creditCard.findElement(By.id("NumeroTarjeta4")).sendKeys(payment.getCreditCard().getCardNumber().split("-")[3]);
			// card owner
			this.driver.findElementById("NombreTarjeta").sendKeys(payment.getCreditCard().getCardOwner());
			// expiration date
			for(WebElement option:this.driver.findElementById("MesTarjeta").findElements(By.tagName("option"))){
				String month = option.getAttribute("value");
				if(month.equals(payment.getCreditCard().getExpirationMonth())){
					option.click();
					break;
				}
			}
			for(WebElement option:this.driver.findElementById("AnyTarjeta").findElements(By.tagName("option"))){
				String year = option.getAttribute("value");
				if(year.equals(payment.getCreditCard().getExpirationYear())){
					option.click();
					break;
				}
			}
			
		}else if (payment.getPayment() == Payment.SUPERMARKET_CARD){
			paymentMethod = "ID_MP_9";
			WebElement paymentButton = this.driver.findElementById(paymentMethod).findElement(By.tagName("a"));
			paymentButton.click();
			// supermarket card
			WebElement creditCard = this.driver.findElementByClassName("dades5");
			// first four numbers
			String firstNumbersOfSupermarketCard = payment.getSupermarketCard().getCardNumber().split("-")[0];
			for(WebElement option:creditCard.findElement(By.id("NumeroTarjeta1")).findElements(By.tagName("option"))){
				if(option.getAttribute("value").equals(firstNumbersOfSupermarketCard)){
					option.click();
					break;
				}
			}			
			creditCard.findElement(By.id("NumeroTarjeta2")).sendKeys(payment.getSupermarketCard().getCardNumber().split("-")[1]);
			creditCard.findElement(By.id("NumeroTarjeta3")).sendKeys(payment.getSupermarketCard().getCardNumber().split("-")[2]);
			creditCard.findElement(By.id("NumeroTarjeta4")).sendKeys(payment.getSupermarketCard().getCardNumber().split("-")[3]);
			// card owner
			this.driver.findElementById("NombreTarjeta").sendKeys(payment.getSupermarketCard().getCardOwner());

		}
	
		
		// In case the product is out of stock
		List<WebElement> options = this.driver.findElementById("Falta_Servicio").findElements(By.tagName("option"));		
		for (WebElement option : options) {
			if (payment.getOutOfProduct().equals(OutOfProduct.REPLACE) && option.getText().equals("Sustituir por similar")) {
				option.click();
			} else {
				option.click();
			}
		}
		

		// Cif & Billing
		if(payment.getWithBill()){
			this.driver.findElementById("ConFactura").click();
			this.driver.findElementById("CIFEmpresa").sendKeys(payment.getCiff());
		}
		
		
		
		// Check cart price
		Double price = 0.0;
		Double less= 0.0;
		if(errors.containsKey(0)){
			@SuppressWarnings("unchecked")
			List<MercadonaProduct> productsToQuit = (ArrayList<MercadonaProduct>)(errors.get(0));
			for(MercadonaProduct productToQuit: productsToQuit){
				less +=Double.valueOf(productToQuit.getPrice()) * Double.valueOf(virtualCart.get(productToQuit));
				String stringPrice = String.format("%.2f", less);
				less = Double.valueOf(stringPrice.replace(",", "."));
			}
		}
		for (MercadonaProduct product : virtualCart.keySet()) {
			price += Double.valueOf(product.getPrice())* Double.valueOf((virtualCart.get(product)));
		}

		price = Double.valueOf(String.format("%.2f", (price-less)).replace(",", "."));
		
		List<WebElement> tables = this.driver.findElements(By.tagName("td"));
		Double realPrice = 0.0;
		for (WebElement element : tables) {
			
			if(element.getAttribute("class").equals("Leyendas tdrig27") && element.getAttribute("headers").equals("cab1 cab02")){
				
				realPrice = Double.valueOf(element.getText().replace(",", ".")) - 7.21;
				String stringPrice = String.format("%.2f", realPrice);
				realPrice = Double.valueOf(stringPrice.replace(",", "."));
			}
		}
		System.out.println("real:"+realPrice);
		System.out.println(price);
			
		// if the price is different and we want to continue anyway
		if(price>realPrice && !continueAnyway){
			errors.put(2, "The app price is bigger than the supermarket price: OFFERT IS COMING! >>"+price);	
		}else if (price<realPrice && !continueAnyway){
			errors.put(3, "The app price is below the supermarket price: Ups! something change in the supermarket >>"+realPrice);
		}
			
		

		

		// Sending Date
		WebElement deliveryDates = this.driver.findElementById("tramoReparto");
		Boolean somethingSelected = false;
		for (WebElement option : deliveryDates.findElements(By.tagName("option"))) {
			if (!option.getText().contains("- - - - - - - - -")) {
				String[] deliveryInfo = option.getText().split(" ");
				Sending dayMoment = calculateDayMoment(deliveryInfo[3]);
				Boolean theSameDay = isTheSameDay(deliveryInfo[1],payment.getDateToDelivery());
				if (dayMoment.equals(payment.getSendingMoment()) && theSameDay) {
					option.click();
					somethingSelected = true;
					break;
				}
			}
		}
		if(!somethingSelected)
			errors.put(1, "Selected date not available");
		
		if(somethingSelected && (continueAnyway || errors.size()==0)){
			// Sending
			System.out.println("Sended");
			//this.driver.findElementByClassName("botonsubmit").click();
		}
		this.driver.close();
		return errors;
		 
	}
	
	
	public Boolean availableDayMoment(Date date, Sending moment){
		this.driver = new HtmlUnitDriver(false);

		if(username==null && password == null){
			System.out.println("Wrong username or password");
			return null;
		}
		// Login
		this.driver.get(LOGIN_PAGE);
		WebElement inputUsername = this.driver.findElementByTagName("form")
				.findElement(By.name("username"));
		WebElement inputPassword = this.driver.findElementByTagName("form")
				.findElement(By.name("password"));
		WebElement inputSubmit = this.driver.findElementByTagName("form")
				.findElement(By.id("ImgEntradaAut"));
		inputUsername.clear();
		inputPassword.clear();
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		inputSubmit.submit();
		// Select something from the catalog, search the products: carne
		WebElement nombreProduct = this.driver.findElementById("busc_ref");
		WebElement button = this.driver.findElementById("imgBuscar");
		nombreProduct.clear();
		nombreProduct.sendKeys("carne");
		button.click();
		WebElement amounth = this.driver.findElementById("cantidad");
		WebElement addButton = this.driver.findElementById("imgCesta");
		amounth.clear();
		amounth.sendKeys("1");
		addButton.click();
		// Processing the order
		WebElement processOrderButton = this.driver
				.findElementByXPath("//div[@id='trBotones']/div[5]/a");
		processOrderButton.click();
		// Finding the date data
		WebElement deliveryDates = this.driver.findElementById("tramoReparto");

		for (WebElement option : deliveryDates.findElements(By.tagName("option"))) {
			if (!option.getText().contains("- - - - - - - - -")) {
				String[] deliveryInfo = option.getText().split(" ");
				Sending dayMoment = calculateDayMoment(deliveryInfo[3]);
				Boolean theSameDay = isTheSameDay(deliveryInfo[1],date);
				if (dayMoment.equals(moment) && theSameDay) {
					return true;
					
				}
			}
		}
		this.driver.close();
		return false;
	}
	
	
	public Set<String> availableDatesToDelivery(){
		if(username==null && password == null){
			System.out.println("Wrong username or password");
			return null;
		}
		this.driver = new HtmlUnitDriver(false);
		Set<String> availableDays = new HashSet<String>();
		// Login
		this.driver.get(LOGIN_PAGE);
		WebElement inputUsername = this.driver.findElementByTagName("form").findElement(By.name("username"));
		WebElement inputPassword = this.driver.findElementByTagName("form").findElement(By.name("password"));
		WebElement inputSubmit = this.driver.findElementByTagName("form").findElement(By.id("ImgEntradaAut"));
		inputUsername.clear();
		inputPassword.clear();
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		inputSubmit.submit();
		// Select something from the catalog, search the products: carne
		WebElement nombreProduct = this.driver.findElementById("busc_ref");
		WebElement button = this.driver.findElementById("imgBuscar");
		nombreProduct.clear();
		nombreProduct.sendKeys("carne");
		button.click();
		WebElement amounth = this.driver.findElementById("cantidad");
		WebElement addButton = this.driver.findElementById("imgCesta");
		amounth.clear();
		amounth.sendKeys("1");
		addButton.click();
		// Processing the order
		WebElement processOrderButton = this.driver.findElementByXPath("//div[@id='trBotones']/div[5]/a");
		processOrderButton.click();	
		// Finding the date data
		WebElement deliveryDates = this.driver.findElementById("tramoReparto");

		for (WebElement option : deliveryDates.findElements(By.tagName("option"))) {
			if (!option.getText().contains("- - - - - - - - -")) {
				String[] deliveryInfo = option.getText().split(" ");
				Sending dayMoment = calculateDayMoment(deliveryInfo[3]);
				availableDays.add(deliveryInfo[1]+"-"+dayMoment);
			}
		}
		this.driver.close();
		return availableDays;
	}
	
	@SuppressWarnings("deprecation")
	private Boolean isTheSameDay(String date1, Date date2){
		Date supermarketDate = CardScraperUtils.stringToDate(date1);
		String[] stringDate1 = supermarketDate.toGMTString().split(" ");
		String[] stringDate2 = date2.toGMTString().split(" ");
		if( stringDate1[0].equals(stringDate2[0]) && stringDate1[1].equals(stringDate2[1]) && stringDate1[2].equals(stringDate2[2]) ){
			return true;
		}
		return false;
	}
	
	private Sending calculateDayMoment(String moment){
		String numbers = moment.split(":")[0];
		if(Integer.valueOf(numbers) < 13){
			return Sending.MORNING;
		}else{
			return Sending.EVENING;
		}
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


}
