package mercadonaCategorizer;

import java.io.UnsupportedEncodingException;

import catalogScrapper.SwissArmyKnife;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class MercadonaCategorizer {
	
	public Categorizer categorizer;
	
	public MercadonaCategorizer(){
		super();
		categorizer = new Categorizer();
		insertMercadonaRules();
	}
	
	
	public String categorize(String categories) throws UnsupportedEncodingException{
		String category = "ofertas y novedades";
		
		String[] tags = categories.trim().toLowerCase().split(">>");
		
		
		for(String key:categorizer.getRules().keySet()){
			String[] ruleTags = key.toLowerCase().trim().split(">");
			int tagNumber = 0;
			Boolean isTheSameTag = true;
		
			for(String rule:ruleTags){
				String ruleTrated= SwissArmyKnife.getEncodedString(rule.trim().replace("�", "�"));
				String tag = SwissArmyKnife.getEncodedString(tags[tagNumber].trim());
				isTheSameTag = isTheSameTag && tag.equals(ruleTrated);
				tagNumber++;
				
				//System.out.println(isTheSameTag+" rule: "+ruleTrated+"  tag: "+tag);
				
			}
			//System.out.println("rule: "+relga);
			//System.out.println("-------\n\n");
			
			if(isTheSameTag){
				
				category = categorizer.getRules().get(key);
				category = category.toLowerCase().replace("�", "�");
				category = SwissArmyKnife.getEncodedString(category);
				break;
			}
		
		}
		
	
		return SwissArmyKnife.getEncodedString(category);
	}
	
	
	private void insertMercadonaRules(){
		categorizer.addRule("Aperitivos", "aperitivos");
		categorizer.addRule("Alimentaci�n > huevos", "carnicer�a");
		categorizer.addRule("Carnes", "carniceria");
		categorizer.addRule("Alimentaci�n > conservantes dulces > membrillo y jaleas", "charcuteria");
		categorizer.addRule("Charcuteria", "charcuteria");
		categorizer.addRule("Aliementaci�n > conservas de pescado", "pescaderia");
		categorizer.addRule("Pescaderia", "pescaderia");
		categorizer.addRule("Alimentaci�n > conservas vegetales", "frutas y verduras");
		categorizer.addRule("Alimentaci�n > conservantes dulces > confituras ", "frutas y verduras");
		categorizer.addRule("Frutas y verduras", "frutas y verduras");
		categorizer.addRule("Alimentaci�n > preparados de postre", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > desayuno y cremas de cacao", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > harinas", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > cereales", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > conservantes dulces > mermelada", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > conservantes dulces > miel", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > azucar y endulcorantes", "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > cafes e infusiones", "horno, dulces y desayunos");
		categorizer.addRule("Horno y Boller�a",  "horno, dulces y desayunos");
		categorizer.addRule("Alimentaci�n > arroz y legumbres > legumbres cocidas", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentaci�n > arroz y legumbres > legumbres secas", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentaci�n > pastas", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentaci�n > pizza y rosca pan", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentaci�n > arroz y legumbres > arroz", "legumbres, arroces y pasta");
		categorizer.addRule("Congelados", "congelados");
		categorizer.addRule("Lacteos", "lacteos");
		categorizer.addRule("Alimentaci�n > diet�ticos", "dietetica");
		
		categorizer.addRule("Alimentaci�n > ali�os y condimentos", "ali�os y condimentos");
		categorizer.addRule("Alimentaci�n > platos preparados", "otros");
		categorizer.addRule("Alimentaci�n > golosinas", "otros");
		categorizer.addRule("Alimentaci�n > Sopas Caldos y pure", "otros");
		categorizer.addRule("Bebes > alimentaci�n infantil", "alimentacion infantil");
		categorizer.addRule("Bebes > ba�o", "ba�o e higiene");
		categorizer.addRule("Bebes > pa�ales", "ba�o e higiene");
		categorizer.addRule("Bebes > toallitas", "ba�o e higiene");
		categorizer.addRule("Bebes > pericultura", "pericultura");
		categorizer.addRule("Bebidas > zumos y nectares", "zumos y batidos");
		categorizer.addRule("Bebidas > vinos blancos", "vinos");
		categorizer.addRule("Bebidas > vinos rosados", "vinos");
		categorizer.addRule("Bebidas > vinos tintos", "vinos");
		categorizer.addRule("Bebidas > vinos espumosos", "vinos");
		categorizer.addRule("Bebidas > sangr�as y combinados a base de vinos", "vinos");
		categorizer.addRule("Bebidas > cavas, champagne y sidras", "vinos");
		categorizer.addRule("Bebidas > finos y dulces", "vinos");
		categorizer.addRule("Bebidas > vermouth y apertivos", "vinos");
		categorizer.addRule("Bebidas > cervezas", "cerveza");
		categorizer.addRule("Bebidas > energeticos", "refrescos");
		categorizer.addRule("Bebidas > refrescos", "refrescos");
		categorizer.addRule("Bebidas > isotonicos", "refrescos");
		categorizer.addRule("Bebidas > concentrados y jarabes", "licores");
		categorizer.addRule("Bebidas > licores", "licores");
		categorizer.addRule("Bebidas > licores sin alcohol", "licores");
		categorizer.addRule("Bebidas > bebida blanca", "licores");
		categorizer.addRule("Bebidas > whisky y bourbon", "licores");
		categorizer.addRule("Bebidas > brandy", "licores");
		categorizer.addRule("Bebidas > agua", "agua");
		categorizer.addRule("Perfumer�a > parafarmacia", "parafarmacia");
		categorizer.addRule("Perfumer�a > higiene bucal", "parafarmacia");
		categorizer.addRule("Perfumer�a > accesorios perfumer�a y belleza", "perfumeria");
		categorizer.addRule("Perfumer�a > l�neas perfumer�a", "perfumeria");
		categorizer.addRule("Perfumer�a > colonias y perfumes", "perfumeria");
		categorizer.addRule("Perfumer�a > laca de u�as", "perfumeria");
		categorizer.addRule("Perfumer�a > lotes de perfumer�a", "perfumeria");
		categorizer.addRule("Perfumer�a > cosm�tica facial", "maquillaje");
		categorizer.addRule("Perfumer�a > cosm�tica decorativa", "maquillaje");
		categorizer.addRule("Perfumer�a > cabello", "higiene");
		categorizer.addRule("Perfumer�a > protecci�n e higiene �ntima", "higiene");
		categorizer.addRule("Perfumer�a > ba�o", "higiene");
		categorizer.addRule("Perfumer�a > desodorante", "higiene");
		categorizer.addRule("Perfumer�a > afeitado", "afeitado y depilacion");
		categorizer.addRule("Perfumer�a > depilacion", "afeitado y depilacion");
		categorizer.addRule("Perfumer�a > productos solares", "miscelanea");
		categorizer.addRule("Perfumer�a > accesorios y utiles higiene", "miscelanea");
		categorizer.addRule("Perfumer�a > manos", "miscelanea");
		categorizer.addRule("Perfumer�a > productos corporales", "miscelanea");
		categorizer.addRule("Droger�a > ambientadores", "ambientadores");
		categorizer.addRule("Droger�a > calzado", "calzado");
		categorizer.addRule("Droger�a > desechables higiene personal", "celulosa");
		categorizer.addRule("Droger�a > complementos tratamiento ropa", "tratamiento ropa");
		categorizer.addRule("Droger�a > detergente lavado a mano", "lavado ropa");
		categorizer.addRule("Droger�a > detergente lavado a m�quina", "lavado ropa");
		categorizer.addRule("Droger�a > detergente prendas delicadas", "lavado ropa");
		categorizer.addRule("Droger�a > lavado ropa", "lavado ropa");
		categorizer.addRule("Droger�a > transportar alimentos", "conservación de alimentos");
		categorizer.addRule("Droger�a > insecticidas", "insecticidas");
		categorizer.addRule("Droger�a > lavavajillas y complementos", "lavavajillas");
		categorizer.addRule("Droger�a >  lejias / liquidos fuertes", "lejía, amoniaco y desinfectantes");
		categorizer.addRule("Droger�a > limpiadores del hogar", "limpieza hogar ");
		categorizer.addRule("Droger�a > suavizantes / eliminaolores", "suavizantes ropa");
		categorizer.addRule("Droger�a > utesiles de limpieza", "utensilios limpieza");
		categorizer.addRule("Complementos de hogar > papel de regalo", "articulos de fiesta");
		categorizer.addRule("Droger�a > monousos desechables", "articulos de fiesta");
		categorizer.addRule("Complementos de hogar > otros utensilios", "ferreteria y bricolage");
		categorizer.addRule("Complementos de hogar > pilas", "ferreteria y bricolage");
		categorizer.addRule("Complementos de hogar > herm�ticos", "menaje de cocina");
		categorizer.addRule("Droger�a > fuego y velas sin aroma", "velas y complementos");
		categorizer.addRule("Mascotas > pajaros", "aves");
		categorizer.addRule("Mascotas > perros", "perros");
		categorizer.addRule("Mascotas > gatos", "gatos");
		categorizer.addRule("Mascotas > roedores", "roedores");
		categorizer.addRule("Mascotas > peces/tortugas", "peces y tortugas");
		
	}
	
	/*
	 * 
	 * Alimentaci�n: 
	 * - Aperitivos 					("Aperitivos") 
	 * - Carniceria 					("Alimentaci�n > huevos", 
	 * 									 "Carnes") 
	 * - Charcuteria					("Alimentaci�n > conservantes dulces > membrillo y jaleas",
	 * 									 "Charcuteria") 
	 * - Pescaderia 					("Aliementacion > conservas de pescado",
	 * 									 "Percaderia") 
	 * - Futas y verduras 				("Alimentaci�n > conservas vegetales,
	 * 									 "Alimentaci�n > conservantes dulces > confituras ",
	 * 									 "Frutas y verduras")
	 * - Horno, Dulces y Desaryunos 	("Alimentaci�n > preparados de postre",
	 * 									 "Alimentaci�n > desayuno y cremas de cacao",
	 * 									 "Alimentaci�n > harinas",
	 * 									 "Alimentaci�n > cereales",
	 * 									 "Alimentaci�n > conservantes dulces > mermelada",
	 * 									 "Alimentaci�n > conservantes dulces > miel",
	 * 									 "Alimentaci�n > azucar y endulcorantes",
	 * 									 "Alimentaci�n > cafes e infusiones", 
	 * 									 "Horno y Bolleria")
	 * - Legumbres,arroces y pasta		("Alimentaci�n > arroz y legumbres > legumbres *",
	 * 									 "Alimentaci�n > pastas", 
	 * 									 "Alimentaci�n > pizza y rosca pan")
	 * 									 "Alimentaci�n > arroz y legumbres > arroz") 
	 * - Congelados 					("Congelados") 
	 * - Lacteos 						("Lacteos") 
	 * - Dietetica 						("Alimentaci�n > dieteticos") 
	 * - Ali�os y condimentos 			("Alimentaci�n > ali�os y condimentos") 
	 * - Otros							("Alimentaci�n > platos preparados", 
	 * 									 "Alimentaci�n > golosinas"
	 *									 "Alimentaci�n > Sopas Caldos y pure")
	 * 
	 * 
	 * BEBES 
	 * - Alimentaci�n infantil 			("Bebes > alimentacion infantil") 
	 * - Ba�o e higiene 				("Bebes > ba�o", "Bebes > pa�ales", 
	 * 									 "Bebes > toallitas" ) 
	 * -Pericultura 					("Bebes > pericultura")
	 * 
	 * Bebidas
	 * - Zumos y batidos 				("Bebidas > zumos y nectares") 
	 * - Vinos							("Bebidas > vinos blancos", 
	 * 									 "Bebidas > vinos rosados",
	 * 									 "Bebidas > vinos tintos", 
	 * 									 "Bebidas > vinos espumosos",
	 * 									 "Bebidas > sangrias y combinados a base de vino",
	 * 									 "Bebidas > cavas, champagne y sidras", 
	 * 									 "Bebidas > finos y dulces",
	 * 									 "Bebidas > vermouth y apertivos") 
	 * - Cerveza 						("Bebidas > cervezas") 
	 * - Refrescos 					    ("Bebidas > energeticos", 
	 * 									 "Bebidas > refrescos",
	 * 									 "Bebidas > isotonicos") 
	 * - Licores 						("Bebidas > concentrados y jarabes",
	 * 									 "Bebidas > licores", 
	 * 									 "Bebidas > licores sin alcohol",
	 * 									 "Bebidas > bebida blanca", 
	 * 									 "Bebidas > whisky y bourbon",
	 * 									 "Bebidas > brandy") 
	 * - Agua 							("Bebidas > agua") 
	 * - Otros ()
	 * 
	 * Higiene personal 
	 * - Parafarmacia 					("Perfumer�a > parafarmacia",
	 * 									 "Perfumer�a > higiene bucal")
	 * - Perfumería						("Perfumer�a > accesorios perfumeria y belleza",
	 * 									 "Perfumer�a > lineas perfumeria", 
	 * 									 "Perfumer�a > colonias y perfumes"
	 * 									 "Perfumer�a > lotes de perfumeria" ) 
	 * - Maquillaje						("Perfumer�a > cosmetica facial", 
	 * 									 "Perfumer�a > cosmetica decorativa") 
	 * - Higiene 						("Perfumer�a > cabello",
	 * 									 "Perfumer�a > proteccion e higiene intima",
	 * 									 "Perfumer�a > ba�o",
	 * 									 "Perfumer�a > desodorante") 
	 * - afeitado y depilacion			("Perfumer�a > afeitado", 
	 * 									 "Perfumer�a > depilacion") 
	 * - Miscelanea						("Perfumer�a > productos solares",
	 * 									 "Perfumer�a > accesorios y utiles higiene",
	 * 									 "Perfumer�a > manos",
	 * 									 "Perfumer�a > productos corporales")
	 * 
	 * Droguería 
	 * - Ambientadores 					("Droger�a > ambientadores") 
	 * - Calzado						("Droger�a > calzado") 
	 * - Celulosa						("Droger�a > desechables higiene personal") 
	 * - Cerillas y encendedores 
	 * - Tratamiento ropa 				("Droger�a > complementos tratamiento ropa") 
	 * - Lavado ropa 					("Droger�a > detergente lavado a mano",
	 * 									 "Droger�a > detergente lavado a maquina",
	 * 									 "Droger�a > detergente prendas delicadas", 
	 * 									 "Droger�a > lavado ropa") 
	 * - Conservación de alimentos 		("Droger�a > transportar alimentos") 
	 * - Desechables/Fundas ropa 
	 * - Insecticidas 					("Droger�a > insecticidas") 
	 * - Lavavajillas 					("Droger�a > lavavajillas y complementos") 
	 * - Lejía, amoniaco y desinfectantes ("Droger�a >  lejias / liquidos fuertes") 
	 * - Limpieza hogar 				("Droger�a > limpiadores del hogar") 
	 * - Suavizantes ropa 				("Droger�a > suavizantes / eliminaolores") 
	 * - Utensilios limpieza			("Droger�a > utesiles de limpieza")
	 * 
	 * Hogar 
	 * - Artículos de fiesta			("Complementos de hogar > papel de regalo",
	 * 									 "Droger�a > monousos desechables")  
	 * - Ferretería y bricolaje 		("Complementos de hogar > otros utensilios",
	 * 									 "Complementos de hogar > pilas") 
	 * - Menaje cocina					("Complementos de hogar > hermeticos") 
	 * - Velas y ambientadores			("Droger�a > fuego y velas sin aroma")
	 * 
	 * Mascotas 
	 * - Aves 							("Mascotas > pajaros") 
	 * - Perros 						("Mascotas > perros") 
	 * - Gatos 							("Mascotas > gatos") 
	 * - Roedores 						("Masctas > roedores") 
	 * - Peces							("Mascotas > peces/tortugas")
	 */
}
