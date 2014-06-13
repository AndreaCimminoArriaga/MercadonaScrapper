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
				String ruleTrated= SwissArmyKnife.getEncodedString(rule.trim().replace("ã±", "ñ"));
				String tag = SwissArmyKnife.getEncodedString(tags[tagNumber].trim());
				isTheSameTag = isTheSameTag && tag.equals(ruleTrated);
				tagNumber++;
				
				//System.out.println(isTheSameTag+" rule: "+ruleTrated+"  tag: "+tag);
				
			}
			//System.out.println("rule: "+relga);
			//System.out.println("-------\n\n");
			
			if(isTheSameTag){
				
				category = categorizer.getRules().get(key);
				category = category.toLowerCase().replace("ã±", "ñ");
				category = SwissArmyKnife.getEncodedString(category);
				break;
			}
		
		}
		
	
		return SwissArmyKnife.getEncodedString(category);
	}
	
	
	private void insertMercadonaRules(){
		categorizer.addRule("Aperitivos", "aperitivos");
		categorizer.addRule("Alimentación > huevos", "carnicería");
		categorizer.addRule("Carnes", "carniceria");
		categorizer.addRule("Alimentación > conservantes dulces > membrillo y jaleas", "charcuteria");
		categorizer.addRule("Charcuteria", "charcuteria");
		categorizer.addRule("Aliementación > conservas de pescado", "pescaderia");
		categorizer.addRule("Pescaderia", "pescaderia");
		categorizer.addRule("Alimentación > conservas vegetales", "frutas y verduras");
		categorizer.addRule("Alimentación > conservantes dulces > confituras ", "frutas y verduras");
		categorizer.addRule("Frutas y verduras", "frutas y verduras");
		categorizer.addRule("Alimentación > preparados de postre", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > desayuno y cremas de cacao", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > harinas", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > cereales", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > conservantes dulces > mermelada", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > conservantes dulces > miel", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > azucar y endulcorantes", "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > cafes e infusiones", "horno, dulces y desayunos");
		categorizer.addRule("Horno y Bollería",  "horno, dulces y desayunos");
		categorizer.addRule("Alimentación > arroz y legumbres > legumbres cocidas", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentación > arroz y legumbres > legumbres secas", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentación > pastas", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentación > pizza y rosca pan", "legumbres, arroces y pasta");
		categorizer.addRule("Alimentación > arroz y legumbres > arroz", "legumbres, arroces y pasta");
		categorizer.addRule("Congelados", "congelados");
		categorizer.addRule("Lacteos", "lacteos");
		categorizer.addRule("Alimentación > dietéticos", "dietetica");
		
		categorizer.addRule("Alimentación > aliños y condimentos", "aliños y condimentos");
		categorizer.addRule("Alimentación > platos preparados", "otros");
		categorizer.addRule("Alimentación > golosinas", "otros");
		categorizer.addRule("Alimentación > Sopas Caldos y pure", "otros");
		categorizer.addRule("Bebes > alimentación infantil", "alimentacion infantil");
		categorizer.addRule("Bebes > baño", "baño e higiene");
		categorizer.addRule("Bebes > pañales", "baño e higiene");
		categorizer.addRule("Bebes > toallitas", "baño e higiene");
		categorizer.addRule("Bebes > pericultura", "pericultura");
		categorizer.addRule("Bebidas > zumos y nectares", "zumos y batidos");
		categorizer.addRule("Bebidas > vinos blancos", "vinos");
		categorizer.addRule("Bebidas > vinos rosados", "vinos");
		categorizer.addRule("Bebidas > vinos tintos", "vinos");
		categorizer.addRule("Bebidas > vinos espumosos", "vinos");
		categorizer.addRule("Bebidas > sangrías y combinados a base de vinos", "vinos");
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
		categorizer.addRule("Perfumería > parafarmacia", "parafarmacia");
		categorizer.addRule("Perfumería > higiene bucal", "parafarmacia");
		categorizer.addRule("Perfumería > accesorios perfumería y belleza", "perfumeria");
		categorizer.addRule("Perfumería > líneas perfumería", "perfumeria");
		categorizer.addRule("Perfumería > colonias y perfumes", "perfumeria");
		categorizer.addRule("Perfumería > laca de uñas", "perfumeria");
		categorizer.addRule("Perfumería > lotes de perfumería", "perfumeria");
		categorizer.addRule("Perfumería > cosmética facial", "maquillaje");
		categorizer.addRule("Perfumería > cosmética decorativa", "maquillaje");
		categorizer.addRule("Perfumería > cabello", "higiene");
		categorizer.addRule("Perfumería > protección e higiene íntima", "higiene");
		categorizer.addRule("Perfumería > baño", "higiene");
		categorizer.addRule("Perfumería > desodorante", "higiene");
		categorizer.addRule("Perfumería > afeitado", "afeitado y depilacion");
		categorizer.addRule("Perfumería > depilacion", "afeitado y depilacion");
		categorizer.addRule("Perfumería > productos solares", "miscelanea");
		categorizer.addRule("Perfumería > accesorios y utiles higiene", "miscelanea");
		categorizer.addRule("Perfumería > manos", "miscelanea");
		categorizer.addRule("Perfumería > productos corporales", "miscelanea");
		categorizer.addRule("Drogería > ambientadores", "ambientadores");
		categorizer.addRule("Drogería > calzado", "calzado");
		categorizer.addRule("Drogería > desechables higiene personal", "celulosa");
		categorizer.addRule("Drogería > complementos tratamiento ropa", "tratamiento ropa");
		categorizer.addRule("Drogería > detergente lavado a mano", "lavado ropa");
		categorizer.addRule("Drogería > detergente lavado a máquina", "lavado ropa");
		categorizer.addRule("Drogería > detergente prendas delicadas", "lavado ropa");
		categorizer.addRule("Drogería > lavado ropa", "lavado ropa");
		categorizer.addRule("Drogería > transportar alimentos", "conservaciÃ³n de alimentos");
		categorizer.addRule("Drogería > insecticidas", "insecticidas");
		categorizer.addRule("Drogería > lavavajillas y complementos", "lavavajillas");
		categorizer.addRule("Drogería >  lejias / liquidos fuertes", "lejÃ­a, amoniaco y desinfectantes");
		categorizer.addRule("Drogería > limpiadores del hogar", "limpieza hogar ");
		categorizer.addRule("Drogería > suavizantes / eliminaolores", "suavizantes ropa");
		categorizer.addRule("Drogería > utesiles de limpieza", "utensilios limpieza");
		categorizer.addRule("Complementos de hogar > papel de regalo", "articulos de fiesta");
		categorizer.addRule("Drogería > monousos desechables", "articulos de fiesta");
		categorizer.addRule("Complementos de hogar > otros utensilios", "ferreteria y bricolage");
		categorizer.addRule("Complementos de hogar > pilas", "ferreteria y bricolage");
		categorizer.addRule("Complementos de hogar > herméticos", "menaje de cocina");
		categorizer.addRule("Drogería > fuego y velas sin aroma", "velas y complementos");
		categorizer.addRule("Mascotas > pajaros", "aves");
		categorizer.addRule("Mascotas > perros", "perros");
		categorizer.addRule("Mascotas > gatos", "gatos");
		categorizer.addRule("Mascotas > roedores", "roedores");
		categorizer.addRule("Mascotas > peces/tortugas", "peces y tortugas");
		
	}
	
	/*
	 * 
	 * Alimentación: 
	 * - Aperitivos 					("Aperitivos") 
	 * - Carniceria 					("Alimentación > huevos", 
	 * 									 "Carnes") 
	 * - Charcuteria					("Alimentación > conservantes dulces > membrillo y jaleas",
	 * 									 "Charcuteria") 
	 * - Pescaderia 					("Aliementacion > conservas de pescado",
	 * 									 "Percaderia") 
	 * - Futas y verduras 				("Alimentación > conservas vegetales,
	 * 									 "Alimentación > conservantes dulces > confituras ",
	 * 									 "Frutas y verduras")
	 * - Horno, Dulces y Desaryunos 	("Alimentación > preparados de postre",
	 * 									 "Alimentación > desayuno y cremas de cacao",
	 * 									 "Alimentación > harinas",
	 * 									 "Alimentación > cereales",
	 * 									 "Alimentación > conservantes dulces > mermelada",
	 * 									 "Alimentación > conservantes dulces > miel",
	 * 									 "Alimentación > azucar y endulcorantes",
	 * 									 "Alimentación > cafes e infusiones", 
	 * 									 "Horno y Bolleria")
	 * - Legumbres,arroces y pasta		("Alimentación > arroz y legumbres > legumbres *",
	 * 									 "Alimentación > pastas", 
	 * 									 "Alimentación > pizza y rosca pan")
	 * 									 "Alimentación > arroz y legumbres > arroz") 
	 * - Congelados 					("Congelados") 
	 * - Lacteos 						("Lacteos") 
	 * - Dietetica 						("Alimentación > dieteticos") 
	 * - Aliños y condimentos 			("Alimentación > aliños y condimentos") 
	 * - Otros							("Alimentación > platos preparados", 
	 * 									 "Alimentación > golosinas"
	 *									 "Alimentación > Sopas Caldos y pure")
	 * 
	 * 
	 * BEBES 
	 * - Alimentación infantil 			("Bebes > alimentacion infantil") 
	 * - Baño e higiene 				("Bebes > baño", "Bebes > pañales", 
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
	 * - Parafarmacia 					("Perfumería > parafarmacia",
	 * 									 "Perfumería > higiene bucal")
	 * - PerfumerÃ­a						("Perfumería > accesorios perfumeria y belleza",
	 * 									 "Perfumería > lineas perfumeria", 
	 * 									 "Perfumería > colonias y perfumes"
	 * 									 "Perfumería > lotes de perfumeria" ) 
	 * - Maquillaje						("Perfumería > cosmetica facial", 
	 * 									 "Perfumería > cosmetica decorativa") 
	 * - Higiene 						("Perfumería > cabello",
	 * 									 "Perfumería > proteccion e higiene intima",
	 * 									 "Perfumería > baño",
	 * 									 "Perfumería > desodorante") 
	 * - afeitado y depilacion			("Perfumería > afeitado", 
	 * 									 "Perfumería > depilacion") 
	 * - Miscelanea						("Perfumería > productos solares",
	 * 									 "Perfumería > accesorios y utiles higiene",
	 * 									 "Perfumería > manos",
	 * 									 "Perfumería > productos corporales")
	 * 
	 * DroguerÃ­a 
	 * - Ambientadores 					("Drogería > ambientadores") 
	 * - Calzado						("Drogería > calzado") 
	 * - Celulosa						("Drogería > desechables higiene personal") 
	 * - Cerillas y encendedores 
	 * - Tratamiento ropa 				("Drogería > complementos tratamiento ropa") 
	 * - Lavado ropa 					("Drogería > detergente lavado a mano",
	 * 									 "Drogería > detergente lavado a maquina",
	 * 									 "Drogería > detergente prendas delicadas", 
	 * 									 "Drogería > lavado ropa") 
	 * - ConservaciÃ³n de alimentos 		("Drogería > transportar alimentos") 
	 * - Desechables/Fundas ropa 
	 * - Insecticidas 					("Drogería > insecticidas") 
	 * - Lavavajillas 					("Drogería > lavavajillas y complementos") 
	 * - LejÃ­a, amoniaco y desinfectantes ("Drogería >  lejias / liquidos fuertes") 
	 * - Limpieza hogar 				("Drogería > limpiadores del hogar") 
	 * - Suavizantes ropa 				("Drogería > suavizantes / eliminaolores") 
	 * - Utensilios limpieza			("Drogería > utesiles de limpieza")
	 * 
	 * Hogar 
	 * - ArtÃ­culos de fiesta			("Complementos de hogar > papel de regalo",
	 * 									 "Drogería > monousos desechables")  
	 * - FerreterÃ­a y bricolaje 		("Complementos de hogar > otros utensilios",
	 * 									 "Complementos de hogar > pilas") 
	 * - Menaje cocina					("Complementos de hogar > hermeticos") 
	 * - Velas y ambientadores			("Drogería > fuego y velas sin aroma")
	 * 
	 * Mascotas 
	 * - Aves 							("Mascotas > pajaros") 
	 * - Perros 						("Mascotas > perros") 
	 * - Gatos 							("Mascotas > gatos") 
	 * - Roedores 						("Masctas > roedores") 
	 * - Peces							("Mascotas > peces/tortugas")
	 */
}
