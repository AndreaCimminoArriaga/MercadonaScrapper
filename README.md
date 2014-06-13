MercadonaScrapper
=================

A scrapper for the Mercadona page (https://www.mercadona.es/ns/index.php).
NOTE: This project is a part of another much bigger, but for privacity i just upload this.

Developed in Java with different technologies:
	- For the catalog i used jsoup (http://jsoup.org/) + apache http client (http://hc.apache.org/httpclient-3.x/).
	- For the cart scrapper i used htmlUnitDriver from Selenium (http://docs.seleniumhq.org/)
	- For matching names the program uses Levensthein distance.

The functionalities available are:
	
	- List the Mercadona's catalog, that will be writted in a file called mercadona.xrf in the subforlder Catalogs. Each time this function is called creates a backup copy of the catalog copy in the folder Backups.
	- Create a cart and pay it (with all the possible methods offered by the page).
	- In case the user have a file xrf from another supermarket this library allows him to fusioning products from the same trademark, size and name.
