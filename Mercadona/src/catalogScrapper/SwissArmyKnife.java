package catalogScrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mercadonaCategorizer.MercadonaCategorizer;
import mercadonaStorage.*;
import org.apache.http.HttpResponse;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class SwissArmyKnife {

	public static String extractHtml(HttpResponse response) {
		String body = null;
		InputStream inputV;
		try {
			inputV = response.getEntity().getContent();
			BufferedReader content = new BufferedReader(new InputStreamReader(
					inputV, "utf8"), 8);
			StringBuilder body2 = new StringBuilder();
			String line = null;
			while ((line = content.readLine()) != null) {
				body2.append(line);
			}
			inputV.close();
			body = body2.toString();
			return body;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return body;
	}

	public static void escribeFichero(MercadonaStorage ms) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("productos.txt");
			pw = new PrintWriter(fichero);

			for (MercadonaProductsTable table : ms.getProducts()) {
				pw.append("Table " + table.getTagLevel1() + " > "
						+ table.getTaglevel2() + " > " + table.getTagLevel3()
						+ " > " + table.getTagLevel4() + "\n");
				for (MercadonaProduct product : table.getProducts()) {
					pw.append(product.toString() + "\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void createTreeDirectory(String supermarket,Map<String, List<MercadonaProduct>> m) throws IOException {

		File mainFolder = new File("Catalogs");

		if (!mainFolder.exists())
			mainFolder.mkdir();

		File supermarketFolder = new File(mainFolder.getAbsolutePath() + "/"
				+ supermarket);

		if (!supermarketFolder.exists())
			supermarketFolder.mkdir();

		String timeStamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date()).replace(" ", "-");
		String fileName = supermarketFolder.getAbsolutePath() + "/"+ supermarket;

		exportToXML(m, timeStamp, fileName);
	}

	public static void exportToXML(Map<String, List<MercadonaProduct>> p, String timestamp, String fileName) throws IOException {
		try {
			// CREATE HEADER
			Element dataset = new Element("dataset");
			dataset.setAttribute(new Attribute("name", "supermarket"));

			// ATTRIBUTES LIST
			Element property0 = new Element("property");
			property0.setAttribute("name", "weight");
			property0.setText("0.1");
			Element metadata0 = new Element("metadata");
			metadata0.addContent(property0);
			Element attribute0 = new Element("attribute");
			attribute0.setAttribute(new Attribute("name", "category0"));
			attribute0.setAttribute(new Attribute("type", "string"));
			attribute0.addContent(metadata0);

			Element property1 = new Element("property");
			property1.setAttribute("name", "weight");
			property1.setText("0.1");
			Element metadata1 = new Element("metadata");
			metadata1.addContent(property1);
			Element attribute1 = new Element("attribute");
			attribute1.setAttribute(new Attribute("name", "category1"));
			attribute1.setAttribute(new Attribute("type", "string"));
			attribute1.addContent(metadata1);

			Element property2 = new Element("property");
			property2.setAttribute("name", "weight");
			property2.setText("0.3");
			Element metadata2 = new Element("metadata");
			metadata2.addContent(property2);
			Element attribute2 = new Element("attribute");
			attribute2.setAttribute(new Attribute("name", "category2"));
			attribute2.setAttribute(new Attribute("type", "string"));
			attribute2.addContent(metadata2);

			Element property3 = new Element("property");
			property3.setAttribute("name", "weight");
			property3.setText("0.6");
			Element metadata3 = new Element("metadata");
			metadata3.addContent(property3);
			Element attribute3 = new Element("attribute");
			attribute3.setAttribute(new Attribute("name", "category3"));
			attribute3.setAttribute(new Attribute("type", "string"));
			attribute3.addContent(metadata3);

			Element property4 = new Element("property");
			property4.setAttribute("name", "weight");
			property4.setText("0.0");
			Element metadata4 = new Element("metadata");
			metadata4.addContent(property4);
			Element attribute4 = new Element("attribute");
			attribute4.setAttribute(new Attribute("name", "name"));
			attribute4.setAttribute(new Attribute("type", "string"));
			attribute4.addContent(metadata4);

			Element property5 = new Element("property");
			property5.setAttribute("name", "weight");
			property5.setText("0.0");
			Element metadata5 = new Element("metadata");
			metadata5.addContent(property5);
			Element attribute5 = new Element("attribute");
			attribute5.setAttribute(new Attribute("name", "price"));
			attribute5.setAttribute(new Attribute("type", "string"));
			attribute5.addContent(metadata5);

			Element property6 = new Element("property");
			property6.setAttribute("name", "weight");
			property6.setText("0.0");
			Element metadata6 = new Element("metadata");
			metadata6.addContent(property6);
			Element attribute6 = new Element("attribute");
			attribute6.setAttribute(new Attribute("name", "ownCategory"));
			attribute6.setAttribute(new Attribute("type", "string"));
			attribute6.addContent(metadata6);

			Element property7 = new Element("property");
			property7.setAttribute("name", "weight");
			property7.setText("0.0");
			Element metadata7 = new Element("metadata");
			metadata7.addContent(property7);
			Element attribute7 = new Element("attribute");
			attribute7.setAttribute(new Attribute("name", "PPW"));
			attribute7.setAttribute(new Attribute("type", "string"));
			attribute7.addContent(metadata7);

			Element property8 = new Element("property");
			property8.setAttribute("name", "weight");
			property8.setText("0.0");
			Element metadata8 = new Element("metadata");
			metadata8.addContent(property8);
			Element attribute8 = new Element("attribute");
			attribute8.setAttribute(new Attribute("name", "tradeMark"));
			attribute8.setAttribute(new Attribute("type", "string"));
			attribute8.addContent(metadata8);

			
			Element attributes = new Element("attributes");
			attributes.addContent(attribute0);
			attributes.addContent(attribute1);
			attributes.addContent(attribute2);
			attributes.addContent(attribute3);
			attributes.addContent(attribute4);
			attributes.addContent(attribute5);
			attributes.addContent(attribute6);
			attributes.addContent(attribute7);

			Element header = new Element("header");
			header.addContent(attributes);
			dataset.addContent(header);

			// CREATING BODY
			Element body = new Element("body");
			Element instances = new Element("instances");
			MercadonaCategorizer categorizer = new MercadonaCategorizer();
			for (String s : p.keySet()) {
				String[] ar = s.split(" >> ");
				for (MercadonaProduct pr : p.get(s)) {
					String categoryProduct = categorizer.categorize(generateKey(ar[0], ar[1], ar[2], ar[3]));
					
					Element producto = new Element("instance");
					
					for (int i = 0; i < ar.length; i++)
						producto.addContent(new Element("value").setText(ar[i].replaceAll("-", " ")));
					if (ar.length == 3) {
						producto.addContent(new Element("value").setText("?"));
					} else if (ar.length == 2) {
						producto.addContent(new Element("value").setText("?"));
						producto.addContent(new Element("value").setText("?"));
					}
					
					/*
					 * Treat the name to delete characters such * on the product name
					 */
					String name = new String(pr.getName().toLowerCase().getBytes(),"UTF-8");
					String productDescription = new String(pr.getProductDescription().toLowerCase().getBytes(),"UTF-8");
					String price = new String(pr.getPrice().toLowerCase().getBytes(),"UTF-8");
					String ppw = new String(pr.getIndicationAboutPriceWeight().toLowerCase().getBytes(),"UTF-8");
					String tradeMark = new String(pr.getTradeMark().toLowerCase().getBytes(),"UTF-8");
					if(pr.getName().contains("\\*")){
						System.out.println(pr.getName());
						String[] nameSplitted = pr.getName().split("*");
						name = nameSplitted[0];
					}
							
					producto.addContent(new Element("value").setText(name+" "+productDescription));
					producto.addContent(new Element("value").setText(price));
					producto.addContent(new Element("value").setText(categoryProduct));
					producto.addContent(new Element("value").setText(ppw));
					producto.addContent(new Element("value").setText(tradeMark));
					
					instances.addContent(producto);
				}
			}
			body.addContent(instances);
			dataset.addContent(body);

			Document doc = new Document(dataset);

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(fileName + ".xrff"));

			System.out.println("File succesfully created!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}
	
	public static String generateKey(String s1, String s2, String s3, String s4){
		
		if(s1.isEmpty()){
			s1 = "?";
		}
		if(s2.isEmpty()){
			s2 = "?";
		}
		if(s3.isEmpty()){
			s3 = "?";
		}
		if(s4.isEmpty()){
			s4 = "?";
		}
		String key = s1 +" >> "+s2 +" >> "+s3 +" >> "+s4 +" >> ";
		return key;
	}
	
	public static String getEncodedString(String notCoded){
		String result = "";
		if(notCoded != null){
			result = notCoded.replace("á", "&aacute;");
			result = result.replace("é", "&eacute;");
			result = result.replace("í", "&iacute;");
			result = result.replace("ó", "&oacute;");
			result = result.replace("ú", "&uacute;");
			result = result.replace("ñ", "&ntilde;");
			result = result.replace("Á", "&Aacute;");
			result = result.replace("É", "&Eacute;");
			result = result.replace("Í", "&Iacute;");
			result = result.replace("Ó", "&Oacute;");
			result = result.replace("Ú", "&Uacute;");
			result = result.replace("Ñ", "&Ntilde;");
			result = result.replace("º", "&deg;");
		}
		return result;
	}
	
}