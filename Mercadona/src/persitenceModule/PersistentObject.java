package persitenceModule;


import java.util.HashMap;
import java.util.Map;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class PersistentObject {
	public enum SupermarketEnumerated {MERCADONA, HIPERCOR};
	
	private String name;
	private String tradeMark;
	private String subcategory;
	private String ppw;
	private Map<SupermarketEnumerated, String> productInstance ;
	
	public PersistentObject(){
		super();
		this.name = null;
		this.tradeMark = null;
		this.subcategory = null;
		this.ppw = null;
		productInstance = new HashMap<SupermarketEnumerated, String>();
	}
	
	public void createFormString(String name, String tradeMark, String subcategory, String price, String ppw, SupermarketEnumerated supermarket){
		this.name = name;
		this.tradeMark = tradeMark;
		this.subcategory = subcategory;
		this.ppw = ppw;
		if(!productInstance.containsKey(supermarket)){
			productInstance.put(supermarket, price);
		}
	}
	
	public void createFormString(String name, String tradeMark, String subcategory, String price, String ppw, String supermarket){
		this.name = name;
		this.tradeMark = tradeMark;
		this.subcategory = subcategory;
		this.ppw = ppw;
		if(supermarket.equals("mercadona") || Levenshtein.LevenshteinDistance(supermarket.toLowerCase(), "mercadona") < 3){
				if(!productInstance.containsKey(supermarket)){
					productInstance.put(SupermarketEnumerated.MERCADONA, price);
				}
		}
		if(supermarket.equals("hipercor")|| Levenshtein.LevenshteinDistance(supermarket.toLowerCase(), "hipercor") < 3){
			if(!productInstance.containsKey(supermarket)){
				productInstance.put(SupermarketEnumerated.HIPERCOR, price);
			}
		}
	
	}

	public void addInstance(SupermarketEnumerated supermarket, String price) {
		if (!productInstance.containsKey(supermarket)) {
			productInstance.put(supermarket, price);
		}
	}

	public void addInstance(String supermarket, String price) {
		if (supermarket.equals("mercadona")) {
			if (!productInstance.containsKey(supermarket)) {
				productInstance.put(SupermarketEnumerated.MERCADONA, price);
			}
		}
		if (supermarket.equals("hipercor")) {
			if (!productInstance.containsKey(supermarket)) {
				productInstance.put(SupermarketEnumerated.HIPERCOR, price);
			}
		}
	}
	
	public void addInstance(Map<SupermarketEnumerated, String> instance) {
		SupermarketEnumerated key = (SupermarketEnumerated) (instance.keySet().toArray())[0];
		if(!this.getProductInstance().containsKey(key)){
			this.productInstance.put(key, instance.get(key));
			
		}	
	}

	public String getName() {
		return name;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public String getPpw() {
		return ppw;
	}

	public Map<SupermarketEnumerated, String> getProductInstance() {
		return productInstance;
	}
	
	
	
	
	
}