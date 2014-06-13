package payment;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class SupermarketCard {
	private String cardNumber;
	private String cardOwner;
	
	public SupermarketCard() {
		super();
		this.cardNumber = null;
		this.cardOwner = null;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardOwner() {
		return cardOwner;
	}
	
	public Boolean setSupermarketCard(String cardNumber, String cardOwner){
		if(!cardNumber.isEmpty() && !cardOwner.isEmpty()){
			this.cardNumber = cardNumber;
			this.cardOwner = cardOwner;
			return true;
		}
		return false;
	}
	
	
}
