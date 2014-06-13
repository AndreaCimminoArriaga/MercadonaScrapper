package payment;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class CreditCard {
	private String cardNumber;
	private String cardOwner;
	private String expirationMonth;
	private String expirationYear;
	
	
	public CreditCard() {
		super();
		this.cardNumber = null;
		this.cardOwner = null;
		this.expirationMonth = null;
		this.expirationYear = null;
	}

	
	public Boolean setCreditCard(String cardNumber, String cardOwner,String expirationMonth, String expirationYear) {
		if (!cardNumber.isEmpty() && !cardOwner.isEmpty() && !expirationMonth.isEmpty() && !expirationYear.isEmpty()) {
			this.cardNumber = cardNumber;
			this.cardOwner = cardOwner;
			this.expirationMonth = expirationMonth;
			this.expirationYear = expirationYear;
			return true;
		}
		return false;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public String getCardOwner() {
		return cardOwner;
	}


	public String getExpirationMonth() {
		return expirationMonth;
	}


	public String getExpirationYear() {
		return expirationYear;
	}
	
	
	
}
