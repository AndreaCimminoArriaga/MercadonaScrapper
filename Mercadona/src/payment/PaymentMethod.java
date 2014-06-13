package payment;

import java.util.Date;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class PaymentMethod {

	public enum Payment {CASH, SUPERMARKET_CARD, CREDIT_CARD};
	public enum OutOfProduct {REPLACE, NOT_SEND, CHANGE_SIZE, CHANGE_TRADEMARK, CALL_ME}
	public enum Sending {MORNING, EVENING}
	
	private Payment payment; 
	private OutOfProduct outOfProduct;
	
	private CreditCard creditCard;
	private SupermarketCard supermarketCard;
	
	private Boolean withBill;
	private String ciff;
	
	private Sending sendingMoment;
	private Date dateToDelivery;
	
	public PaymentMethod() {
		super();
		this.dateToDelivery = null;
		this.payment = null;
		this.outOfProduct = null;
		this.creditCard = null;
		this.supermarketCard = null;
		this.withBill = null;
		this.ciff = null;
		this.sendingMoment = null;
				
	}
	
	// SET PAYMENTS:
	// Cash
	public Boolean setCashPayment(Date dateToDelivery, Sending sendingMoment, OutOfProduct outOfProduct){
		this.payment = Payment.CASH;
		this.dateToDelivery = dateToDelivery;
		this.sendingMoment = sendingMoment;
		this.outOfProduct = outOfProduct;
		this.withBill = false;
		return this.isTheMomentOfSellCorrect() && this.isTheThePaymentMethodCorrect();
	}
	
	public Boolean setCashPaymentWithBill(Date dateToDelivery, Sending sendingMoment, OutOfProduct outOfProduct, String ciff){
		this.payment = Payment.CASH;
		this.dateToDelivery = dateToDelivery;
		this.sendingMoment = sendingMoment;
		this.outOfProduct = outOfProduct;
		this.withBill = true;
		this.ciff = ciff;
		return this.isTheMomentOfSellCorrect() && this.isTheBillCorrect() && this.isTheThePaymentMethodCorrect();
	}

	// Credit card
	public Boolean setCreditCardPayment(Date dateToDelivery, Sending sendingMoment, OutOfProduct outOfProduct, CreditCard creditCard){
		this.payment = Payment.CREDIT_CARD;
		this.dateToDelivery = dateToDelivery;
		this.sendingMoment = sendingMoment;
		this.outOfProduct = outOfProduct;
		this.withBill = false;
		this.creditCard = creditCard;
		return this.isTheMomentOfSellCorrect() && this.isTheThePaymentMethodCorrect();
	}
	

	public Boolean setCreditCardPaymentWithBill(Date dateToDelivery, Sending sendingMoment, OutOfProduct outOfProduct, String ciff, CreditCard creditCard){
		this.payment = Payment.CREDIT_CARD;
		this.dateToDelivery = dateToDelivery;
		this.sendingMoment = sendingMoment;
		this.outOfProduct = outOfProduct;
		this.withBill = true;
		this.ciff = ciff;
		this.creditCard = creditCard;
		return this.isTheMomentOfSellCorrect() && this.isTheBillCorrect() && this.isTheThePaymentMethodCorrect();
	}
	
	// Supermarket card
	public Boolean setSupermarketCardPayment(Date dateToDelivery, Sending sendingMoment, OutOfProduct outOfProduct, SupermarketCard creditCard){
		this.payment = Payment.SUPERMARKET_CARD;
		this.dateToDelivery = dateToDelivery;
		this.sendingMoment = sendingMoment;
		this.outOfProduct = outOfProduct;
		this.withBill = false;
		this.supermarketCard = creditCard;
		return this.isTheMomentOfSellCorrect() && this.isTheThePaymentMethodCorrect();
	}
	
	public Boolean setSupermarketPaymentWithBill(Date dateToDelivery, Sending sendingMoment, OutOfProduct outOfProduct, String ciff, SupermarketCard creditCard){
		this.payment = Payment.SUPERMARKET_CARD;
		this.dateToDelivery = dateToDelivery;
		this.sendingMoment = sendingMoment;
		this.outOfProduct = outOfProduct;
		this.withBill = true;
		this.ciff = ciff;
		this.supermarketCard = creditCard;
		return this.isTheMomentOfSellCorrect() && this.isTheBillCorrect() && this.isTheThePaymentMethodCorrect();
	}
	
	// checking the payments	
	private Boolean isTheThePaymentMethodCorrect(){
		Boolean correct = false;
		if(this.payment.equals(Payment.CASH) && this.outOfProduct!= null){
			correct = true;
		}else if(this.payment.equals(Payment.CREDIT_CARD) && this.outOfProduct!= null && this.creditCard!=null){
			correct = true;
		}else if(this.payment.equals(Payment.SUPERMARKET_CARD) && this.outOfProduct!= null && this.supermarketCard!=null){
			correct = true;
		}
		
		return correct;
	}
	
	// Verifiers
	private Boolean isTheMomentOfSellCorrect(){
		Boolean correct = false;
		Date currentDate = new Date();
		if(this.dateToDelivery!= null && this.dateToDelivery.after(currentDate) && this.sendingMoment!=null){
			correct = true;
		}
		return correct;
	}

	private Boolean isTheBillCorrect(){
		Boolean correct = false;
		if(this.withBill==null){
			correct=false;
		}else if(this.withBill!=null && this.withBill && this.withBill!= null && !this.ciff.isEmpty()){
			correct = true;
		}else if( this.withBill!=null && !this.withBill ){
			correct = true;
		}
		return correct;
	}

	public Payment getPayment() {
		return payment;
	}

	public OutOfProduct getOutOfProduct() {
		return outOfProduct;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public SupermarketCard getSupermarketCard() {
		return supermarketCard;
	}

	public Boolean getWithBill() {
		return withBill;
	}

	public String getCiff() {
		return ciff;
	}

	public Sending getSendingMoment() {
		return sendingMoment;
	}

	public Date getDateToDelivery() {
		return dateToDelivery;
	}
	
	
	
}
