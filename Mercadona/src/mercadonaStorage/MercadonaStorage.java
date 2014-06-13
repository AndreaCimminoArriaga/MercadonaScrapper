package mercadonaStorage;


import java.util.ArrayList;
import java.util.List;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class MercadonaStorage {
	
	private List<MercadonaProductsTable> productsTable;

	public MercadonaStorage() {
		productsTable = new ArrayList<MercadonaProductsTable>();
	}
	
	public void addProduct(MercadonaProductsTable product){
		this.productsTable.add(product);
	}
	
	public List<MercadonaProductsTable> getProducts(){
		return this.productsTable;
	}

	
	
	
}

