package tests;

import java.io.UnsupportedEncodingException;

import catalogScrapper.SwissArmyKnife;
import mercadonaCategorizer.MercadonaCategorizer;
import mercadonaStorage.MercadonaProduct;

public class Prueba {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		MercadonaCategorizer categorizer = new MercadonaCategorizer();
		
		MercadonaProduct producto = new MercadonaProduct();
		producto.parseProductFromString("InsertaLinea(45313,3,\"\",\"JABON  BAÑO BEBE LIQUIDO ALOE, NENUCO, BOTELLA 750 cc\",2.73,2,454.23378,0,1,true,9661,\"\",\"100 CC: 0,36 Euros\",\"100 CC: 61 Ptas\")");
		

		
	}

}
