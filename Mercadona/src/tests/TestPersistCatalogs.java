package tests;

import java.io.IOException;
import java.util.List;
import persitenceModule.PersistCatalogs;
import persitenceModule.PersistentObject;


public class TestPersistCatalogs {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PersistCatalogs persistCatalog = new PersistCatalogs();
		persistCatalog.readXRFFiles("Catalogs");
		System.out.println("Mercadona products:"+persistCatalog.getProductsMercadona().size());
		System.out.println("Hipercor products:"+persistCatalog.getProductsHipercor().size());
		
		/*
		 * TEST TO FUSIONING PRODUCTS
		 * 
		 */
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		
		List<PersistentObject> objects  = persistCatalog.startAlgotithm(5);
		System.out.println("Numero de productos a persistir: "+ objects.size());
		time_end = System.currentTimeMillis();
		System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
		
		/*
		 * TEST TO SEPARATE THE NAME FROM THE SIZE
		 * 
		 * String name = "mermelada de higos en temporada con tarro 350 g";
		 * System.out.println(persistCatalog.getNameWithoutSize(name));
		 * 
		 */
		
		/*
		 * TEST OF FORMATTING STRING.
		 * 
		 * System.out.println(persistCatalog.formatString("mermelada de higos en temporada con tarro"));
		 */
		
		/*
		 * TEST TO THE DIFFERENT SIZE OF THE PRODUCTS PROBLEM
		 * 
		 *
		 * System.out.println(persistCatalog.sameSize("mermelada de higos en temporada con tarro 350 g", "mermelada de higos en temporada con tarro 350 g"));
		 * 
		 */
		
		
		
	}

}
