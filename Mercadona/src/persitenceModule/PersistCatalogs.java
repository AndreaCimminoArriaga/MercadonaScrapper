package persitenceModule;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import persitenceModule.PersistentObject.SupermarketEnumerated;


/*
 * Author: Andrea Cimmino Arriaga.
 */
public class PersistCatalogs {
	
	private List<PersistentObject> productsMercadona;
	private List<PersistentObject> productsHipercor;
	private List<PersistentObject> products;
	
	public PersistCatalogs(){
		super();
		productsMercadona = new ArrayList<PersistentObject>();
		productsHipercor = new ArrayList<PersistentObject>();
		products = new ArrayList<PersistentObject>();
	}
		
	public void readXRFFiles(String catalogsFolder){
		System.out.println("FDirectorio: "+System.getProperty("user.dir"));
		File mainDirectory = new File(catalogsFolder);
		List<String> directories = new ArrayList<String>();
		listXRFFFiles(mainDirectory, directories);
		
		for(String directory: directories){
			readXRFFile(directory);
		}
	
	}
	
	private void readXRFFile(String directory) {
		System.out.println("Reading: "+directory);
		SAXBuilder saxBuilder = new SAXBuilder();
		File xrffFile = new File(directory);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(xrffFile);

			String supermarketName = xrffFile.getName().replace(".xrff", "")
					.replaceAll("\\s", "").toLowerCase();
			BufferedReader bufferReader = new BufferedReader(fileReader);
			if (xrffFile.isFile()) {

				try {
					Document document = saxBuilder.build(bufferReader);
					Element rootNode = document.getRootElement();
					List<Element> instances = rootNode.getChild("body").getChild("instances").getChildren();

					for (Element instance : instances) {

						String name = instance.getChildren().get(4).getText();
						String price = instance.getChildren().get(5).getText();
						String subcategory = instance.getChildren().get(6)
								.getText();
						String ppw = instance.getChildren().get(7).getText();
						String tradeMark = instance.getChildren().get(8)
								.getText();
						PersistentObject productObject = new PersistentObject();
						//productObject.createFormString(name, tradeMark,subcategory, price, ppw, supermarketName);
						productObject.createFormString(name, tradeMark, subcategory, price, ppw, supermarketName);
						addProduct(productObject, supermarketName);
					}

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
		
	private void listXRFFFiles(File folder, List<String> files) {
	    
		String file = "";
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) { 
	        	listXRFFFiles(fileEntry, files);
	        } else {
	            file = fileEntry.getPath();
	            files.add(file);
	           
	        }
	    }
	   
	}

	private void addProduct(PersistentObject productObject, String supermarketName){
		if(Levenshtein.LevenshteinDistance(supermarketName.toLowerCase().replaceAll("\\s", ""), "mercadona") < 2){
			this.productsMercadona.add(productObject);
		  }
		if(Levenshtein.LevenshteinDistance(supermarketName.toLowerCase().replaceAll("\\s", ""), "hipercor") < 2){
			this.productsHipercor.add(productObject);
		   }
	}
	
	/* ALGORITHM
	 * First of all we decide to operate over the shortest list, in the second
	 * place we iterate over that list extracting the tradeMark, the name and
	 * the subcategory of the products. For each product we compare it with the
	 * products of the other supermarket (the longest list), the conditions to
	 * be the same product in different supermarkets are:
	 * 
	 * 1.- Have the same trade mark. 
	 * 2.- Have the same subcategory. 
	 * 3.- The Levenshtein distance has to be less than (experimental value).
	 * 
	 * If all that conditions are satisfied then the product is the same and we
	 * will treat it to unify both and add it to the attribute products list. In
	 * the case the products are different the first product (belonger to the
	 * shortest list) is added to the product list attribute and the other
	 * product (belonger to the longest list) to another list called
	 * removalObjects.
	 * 
	 * When all the iterations are end in the product list atribute we will have
	 * all the products that have been fusioned and the products that weren't of
	 * the shortest list. To add from the longest list we just use the methos
	 * add all, but first we remove the products that were unify before.
	 * 
	 * Notice that the products are splitted by their supermarket at the
	 * beggining, that make much more easy the classify task. And the products
	 * attribute is a set so we will have no problems with repeated products
	 */
	
	public List<PersistentObject> startAlgotithm(Integer levenshteinMaxDistance){
		Integer progress = 0;
		Double duration = 0.0;
		if (this.productsMercadona.size() < this.productsHipercor.size()) {
			Set<PersistentObject> removalObjects = new HashSet<PersistentObject>();
			
			for (PersistentObject product0 : this.productsMercadona) {
				//
				long time_start, time_end;
				time_start = System.currentTimeMillis();
				//
				progress++;
				System.out.println("Progress: "+ String.format("%.2f",(Double.valueOf(progress * 1.0/ this.productsMercadona.size()) * 100))+" %");
				
				String tradeMark0 = product0.getTradeMark().toLowerCase().replace("\\s","");;
				String name0 = product0.getName();
				
				if (!tradeMark0.equals("hacendado")) {
					
					for (PersistentObject product1 : this.productsHipercor) {
						
						String tradeMark1 = product1.getTradeMark().toLowerCase().replace("\\s","");
						String name1 = product1.getName();
						
						if( !tradeMark0.equals(tradeMark1) || !sameSize(name0, name1))
							break;
						
						
						// treating names withdout prices
						String name0WithoutSize = formatString(getNameWithoutSize(name0));
						String name1WithoutSize = formatString(getNameWithoutSize(name1));
						
						if ( Levenshtein.LevenshteinDistance(name0WithoutSize, name1WithoutSize) <= levenshteinMaxDistance && sameSize(name0, name1)) {
							// The products are the same
							// 1.-Fusioning both into product0
							product0.addInstance(product1.getProductInstance());
							// 2.-Adding the product1 into removalObjects
							removalObjects.add(product1);
							System.out.println("Fusioning: "+ Levenshtein.LevenshteinDistance(name0WithoutSize,name1WithoutSize));
							System.out.println("\t" + name0 + " & " + name1);
							System.out.println("");
							
							break;
						}
					}
					
					
				}
				
				
				/*
				 *   3.- In both cases, the product0 has been unify with another or
				 *   has not we add it to the final product list attribute.
				 */
				this.products.add(product0);
				time_end = System.currentTimeMillis();
				System.out.println("the iteration has taken "+ ( time_end - time_start ) +" milliseconds");
				duration += time_end - time_start;
			}
			/*
			 *  Now we have all the products of the shortest list added into our final list,
			 *  but we still need to add the products that have no been fusioned from the
			 *  longest list, so we use the removalObject list to remove them and add only
			 *  the products that only exists in a supermarket
			 */
			
			long time_start, time_end;
			time_start = System.currentTimeMillis();
			
			this.productsHipercor.removeAll(removalObjects);
		    this.products.addAll(this.productsHipercor);
		    
		    time_end = System.currentTimeMillis();
			duration += time_end - time_start;
			System.out.println("the whole function has taken: "+ duration);
			System.out.println("the whole function in itaration has taken aprox: "+ duration/progress);

			
		}else{
			Set<PersistentObject> removalObjects = new HashSet<PersistentObject>();
			
			for (PersistentObject product0 : this.productsHipercor) {
				progress++;
				System.out.println("Progress: "+ String.format("%.2f",(Double.valueOf(progress * 1.0/ this.productsMercadona.size()) * 100))+" %");
				
				String tradeMark0 = product0.getTradeMark().toLowerCase().replace("\\s","");;
				String name0 = product0.getName();
				
					
					for (PersistentObject product1 : this.productsMercadona) {
						String tradeMark1 = product1.getTradeMark().toLowerCase().replace("\\s","");
						String name1 = product1.getName();

						// treating names withdout prices
						String name0WithoutSize = formatString(getNameWithoutSize(name0));
						String name1WithoutSize = formatString(getNameWithoutSize(name1));
						
						if ( tradeMark0.equals(tradeMark1) && Levenshtein.LevenshteinDistance(name0WithoutSize, name1WithoutSize) <= levenshteinMaxDistance && sameSize(name0, name1)) {
							// The products are the same
							// 1.-Fusioning both into product0
							System.out.println(product1.getProductInstance());
							product0.addInstance(product1.getProductInstance());
							// 2.-Adding the product1 into removalObjects
							removalObjects.add(product1);
							System.out.println("Fusioning: "+ Levenshtein.LevenshteinDistance(name0WithoutSize,name1WithoutSize));
							System.out.println("\t" + name0 + " & " + name1);
							System.out.println("");
							break;
						}
					}
					
				
				
				/*
				 *   3.- In both cases, the product0 has been unify with another or
				 *   has not we add it to the final product list attribute.
				 */
				this.products.add(product0);
				
			}
			/*
			 *  Now we have all the products of the shortest list added into our final list,
			 *  but we still need to add the products that have no been fusioned from the
			 *  longest list, so we use the removalObject list to remove them and add only
			 *  the products that only exists in a supermarket
			 */
			
			
			
			this.productsHipercor.removeAll(removalObjects);
		    this.products.addAll(this.productsMercadona);
		}
		
		return this.products;
	}
	
	/*
	 * Make a backup of the final product list attributes.
	 */
	public void createBackUp() {
		File backup = new File("Backups");
		if(!backup.exists())
			backup.mkdir();
		// Crear un fichero xrff que almacene el objeto products
		String date = new java.util.Date().toString();

		Element dataset = new Element("dataset");
		dataset.setAttribute(new Attribute("date", date));

		for (PersistentObject product : this.products) {
			for (SupermarketEnumerated se : product.getProductInstance()
					.keySet()) {
				Element producto = new Element("instance");
				producto.addContent(new Element("name").setText(product
						.getName()));
				producto.addContent(new Element("trademark").setText(product
						.getTradeMark()));
				producto.addContent(new Element("subcategory").setText(product
						.getSubcategory()));
				producto.addContent(new Element("price").setText(product
						.getProductInstance().get(se)));
				if (se.equals(SupermarketEnumerated.HIPERCOR)) {
					producto.addContent(new Element("supermarket")
							.setText("HIPERCOR"));
				} else if (se.equals(SupermarketEnumerated.MERCADONA)) {
					producto.addContent(new Element("supermarket")
							.setText("MERCADONA"));
				}
				dataset.addContent(producto);
			}
		}
		try {
			Document doc = new Document(dataset);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc,new FileWriter("Backups/"
							+ (date.replace(":", " ")).replace(" ", "-")
							+ ".xrff"));
			System.out.println("Products file created successfully");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Problems occurred");
		}
	}
	
	/*
	 *  GETTERS & SETTERS
	 */
	public String formatString(String characters){
		String string = characters.toLowerCase();
		// Articles
		string = string.replaceAll("\\sel\\s", "");
		string =string.replaceAll("\\sla\\s", "");
		string =string.replaceAll("\\slos\\s", "");
		string =string.replaceAll("\\slas\\s", "");
		string =string.replaceAll("\\sun\\s", "");
		string =string.replaceAll("\\suna\\s", "");
		string =string.replaceAll("\\sunos\\s", "");
		string =string.replaceAll("\\sunas\\s", "");
		// Vowels
		string = string.replaceAll("\\s[aeiou]\\s", "");
		// Any word just of two letters
		string = string.replaceAll("\\s[aeiou][\\d]\\s", "");
		string = string.replaceAll("\\s[\\d][aeiou]\\s", "");
		// Prepositions (not included before like "a")
		string =string.replaceAll("\\sante\\s", "");
		string =string.replaceAll("\\sbajo\\s", "");
		string =string.replaceAll("\\scabe\\s", "");
		string =string.replaceAll("\\scontra\\s", "");
		string =string.replaceAll("\\sde\\s", "");
		string =string.replaceAll("\\sdesde\\s", "");
		string =string.replaceAll("\\sen\\s", "");
		string =string.replaceAll("\\sentre\\s", "");
		string =string.replaceAll("\\shacia\\s", "");
		string =string.replaceAll("\\shasta\\s", "");
		string =string.replaceAll("\\spara\\s", "");
		string =string.replaceAll("\\spor\\s", "");
		string =string.replaceAll("\\ssegun\\s", "");
		string =string.replaceAll("\\sso\\s", "");
		string =string.replaceAll("\\ssobre\\s", "");
		string =string.replaceAll("\\stras\\s", "");
		// Others
		string =string.replaceAll("\\simportacion\\s", "");
		string =string.replaceAll("\\spaquete\\s", "");
		string = string.replaceAll("\\s", "");
		return string;
	}
	
	public Boolean sameSize(String product1, String product2){
		String pattern = "([\\d)]+\\s[\\w]{1,2}(\\s)?)?$";
	
		Pattern compilePattern = Pattern.compile(pattern);
		Matcher matcher1 = compilePattern.matcher(product1);
		matcher1.find();
		String matched1 = matcher1.group(0).replaceAll("\\s", "");
		Matcher matcher2 = compilePattern.matcher(product2);
		matcher2.find();
		String matched2 = matcher2.group(0).replaceAll("\\s", "");

		// Treat here the sizes from differents mesures
		
		return matched2.equals(matched1);
	}
	
	public String getNameWithoutSize(String name){
		String pattern = "([\\d)]+\\s[\\w]{1,2}(\\s)?)?$";
		
		Pattern compilePattern = Pattern.compile(pattern);
		Matcher matcher = compilePattern.matcher(name);
		matcher.find();
		String matched = matcher.group(0);
		return name.replace(matched, "");

	}
	
	public List<PersistentObject> getProductsMercadona() {
		return productsMercadona;
	}

	public List<PersistentObject> getProductsHipercor() {
		return productsHipercor;
	}

	public List<PersistentObject> getProducts() {
		return products;
	}


	
	
}
