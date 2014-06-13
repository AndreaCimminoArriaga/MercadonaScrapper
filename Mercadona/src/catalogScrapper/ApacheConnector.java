package catalogScrapper;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
 * Author: Andrea Cimmino Arriaga.
 */
public class ApacheConnector {

	private List<String> cookies;
	private HttpClient client ;
	private final String USER_AGENT;
	private final String LOGIN_PAGE = "https://www.mercadona.es/ns/entrada.php?js=1";
	private final String HOME_PAGE = "https://www.mercadona.es/ns/principal.php?entrado=1";

	
	public ApacheConnector(){
		this.client = HttpClientBuilder.create().build();
		this.USER_AGENT = "Mozilla/5.0";
		this.cookies = new ArrayList<String>();
	}

	
	public boolean login(String username, String password){
		CookieHandler.setDefault(new CookieManager());
		String getLogin = this.getPageContent(LOGIN_PAGE); 
		List<NameValuePair> postParams = getFormParams(getLogin, username, password);
		sendPost(postParams);
		
		String title = Jsoup.parse(this.getPageContent(HOME_PAGE)).getElementsByTag("title").first().html();
		return title.contains("Mercadona - Compr@ Online");
	}
	
	private List<NameValuePair> getFormParams(String html, String username,String password) {
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();

		Document doc = Jsoup.parse(html);
		Element form = doc.getElementById("principal");
		for (Element input : form.getElementsByTag("input")) {
			String inputName = input.attr("name");
			String inputValue = "";
			if (inputName.equals("username")) {
				inputValue = username;
			} else if (inputName.equals("password")) {
				inputValue = password;
			} else {
				inputValue = input.attr("value");
			}
			postParams.add(new BasicNameValuePair(inputName, inputValue));
		}

		return postParams;

	}

	public String getPageContent(String url){
		HttpResponse response;
		String html = "";
		HttpGet request = new HttpGet(url);
		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		for(String cookie:cookies)
			request.setHeader("Set-Cookie",cookie);
		
		try {
			response = client.execute(request);
						
			if(response.getHeaders("Set-Cookie") != null){
				addCookies(response.getHeaders("Set-Cookie"));
			}
			return SwissArmyKnife.extractHtml(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 	
		return html;
	}
	
	private void sendPost(List<NameValuePair> postParams){
		HttpPost post = new HttpPost(LOGIN_PAGE);
		HttpResponse response;

		post.setHeader("Host", "www.mercadona.es");
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		
		for(String cookie:cookies)
			post.setHeader("Set-Cookie",cookie);
		
		
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			response = client.execute(post);
			
			//for(Header header:post.getAllHeaders())
			//	System.out.println(header.toString());
			
			if(response.getHeaders("Set-Cookie") != null){
				addCookies(response.getHeaders("Set-Cookie"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	
	  }

	public List<String> getCookies(){
		return this.cookies;
	}


	public void addCookies(Header[] cookies){
		for(Header cookie: cookies){
			String processedCookie = cookie.toString().replace("Set-Cookie: ", "");
			addCookie(processedCookie);
		}
	}
	

	public void addCookie(String cookie){
		String cookieId = cookie.split("=")[0];
		String containedCookie = "";
		for(String cookieStored : this.cookies){
			if(cookieStored.contains(cookieId) && cookieId.equals("cesta") ){
				containedCookie = cookieStored;
				break;
			}
		}
		if(!containedCookie.equals(""))
			this.cookies.remove(containedCookie);
		this.cookies.add(cookie);
	}


	
}
