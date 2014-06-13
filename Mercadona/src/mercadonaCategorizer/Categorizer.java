package mercadonaCategorizer;

import java.util.HashMap;
import java.util.Map;


/*
 * Author: Andrea Cimmino Arriaga.
 */
public class Categorizer {

	private Map<String, String> rules;

	public Categorizer() {
		super();
		rules = new HashMap<String, String>();
	}
	
	public Map<String, String> getRules(){
		return this.rules;
	}
	public boolean addRule(String antecedent, String consequent){
		if(!rules.containsKey(antecedent)){
			rules.put(antecedent, consequent);
			return true;
		}
		return false;
	}
		
}
