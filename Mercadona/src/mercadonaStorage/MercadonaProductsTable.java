package mercadonaStorage;

import java.util.ArrayList;
import java.util.List;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class MercadonaProductsTable {

	private String tagLevel1;
	private String taglevel2;
	private String tagLevel3;
	private String tagLevel4;
	private String url;
	private Integer numberOfProducts = 0;
	private List<MercadonaProduct> products;
	
	public MercadonaProductsTable(String tagLevel1, String taglevel2,
			String tagLevel3, String tagLevel4, String url) {
		super();
		this.tagLevel1 = tagLevel1;
		this.taglevel2 = taglevel2;
		this.tagLevel3 = tagLevel3;
		this.tagLevel4 = tagLevel4;
		this.url = url;
		this.products = new ArrayList<MercadonaProduct>();
	
	}

	public String getTagLevel1() {
		return tagLevel1;
	}

	public String getTaglevel2() {
		return taglevel2;
	}

	public String getTagLevel3() {
		return tagLevel3;
	}

	public String getTagLevel4() {
		return tagLevel4;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "MercadonaProductTable [tagLevel1=" + tagLevel1 + ", taglevel2="
				+ taglevel2 + ", tagLevel3=" + tagLevel3 + ", tagLevel4="
				+ tagLevel4 + ", url=" + url + "]";
	}

	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}
	
	public List<MercadonaProduct> getProducts(){
		return this.products;
	}
	
	public void addProductToTable(MercadonaProduct product){
		this.products.add(product);
	}
	
}
