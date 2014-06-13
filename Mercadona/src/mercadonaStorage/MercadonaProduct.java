package mercadonaStorage;

import catalogScrapper.SwissArmyKnife;


/*
 * Author: Andrea Cimmino Arriaga.
 */
public class MercadonaProduct {

		private String name;
		private String tradeMark;
		private String productDescription;
		private String price;
		private String indicationAboutPriceWeight;
		private String productCode;
		private String tableLink;

		
		public MercadonaProduct(){
			super();
		}
		
		public void parseProductFromString(String line) {
			
			String lineCoded = SwissArmyKnife.getEncodedString(line);
			String lineProcessed = lineCoded.substring(line.indexOf("("), line.length());
			String lineContent[] = lineProcessed.trim().split("\",\"");
			String importantLines[] = lineContent[1].split("\",");
			
			String name = lineContent[1].split(",")[0];
			String tradeMark = lineContent[1].split(",")[1];
			String productDescription = importantLines[0].substring(importantLines[0].indexOf(",")+1).substring(importantLines[0].substring(importantLines[0].indexOf(",")+1).indexOf(",")+2);
//					lineContent[1].split(",")[2];
//			String price = lineContent[1].split(",")[3];
			String price = importantLines[1].split(",")[0];
			String indicationAboutPriceWeight = lineContent[lineContent.length-2]+", "+ lineContent[lineContent.length-1];
			String productCode = lineContent[0].substring(lineContent[0].indexOf('(')+1,lineContent[0].indexOf(',') );
				
			this.name = name;
			this.tradeMark = tradeMark;
			this.productDescription = (productDescription.substring(0, productDescription.length()-1));
			this.price = price.trim();
			this.indicationAboutPriceWeight = indicationAboutPriceWeight;
			this.productCode = productCode.trim();
			this.tableLink = "";
			System.out.println(this.name+", "+ this.productDescription);
		}


		public String getTradeMark() {
			return tradeMark;
		}

		public String getName() {
			return name;
		}

		public String getProductDescription() {
			return productDescription;
		}

		public String getPrice() {
			return price;
		}

		public String getIndicationAboutPriceWeight() {
			return indicationAboutPriceWeight;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getTableLink() {
			return tableLink;
		}

		public void setTableLink(String tableLink) {
			this.tableLink = tableLink;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setTradeMark(String tradeMark) {
			this.tradeMark = tradeMark;
		}

		public void setProductDescription(String productDescription) {
			this.productDescription = productDescription;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public void setIndicationAboutPriceWeight(String indicationAboutPriceWeight) {
			this.indicationAboutPriceWeight = indicationAboutPriceWeight;
		}
		
		
}
