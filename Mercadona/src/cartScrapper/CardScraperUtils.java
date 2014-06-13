package cartScrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class CardScraperUtils {

	public static Date stringToDate(String date){
		Integer day = Integer.valueOf(date.split("/")[0]);
		String corectedDate = String.valueOf(day+1)+"/"+date.split("/")[1]+"/"+date.split("/")[2];
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaDate = null;
	    try {
	        fechaDate = formato.parse(corectedDate);
	    } 
	    catch (ParseException ex){
	    }
	    return fechaDate;
	}
}
