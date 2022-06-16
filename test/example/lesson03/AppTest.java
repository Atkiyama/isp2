package example.lesson03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppTest {

	@Test
	public void test() throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage loginPage = null;
		HtmlPage indexPage = null;

		String url = this.getBaseUrl() + "/example/lesson03/login.html";
		WebRequest request = new WebRequest(new URL(url));
		loginPage = webClient.getPage(request);
	    ((HtmlInput)loginPage.getElementById("login_form_username_field")).setValueAttribute("test");
	    
		indexPage = ((HtmlInput) loginPage.getElementById("login_form_submit")).click();
		assertEquals("?さん", indexPage.getElementById("navigation_user_name").asText());
		
		HtmlPage logoutPage = ((HtmlInput) indexPage.getElementById("navigation_logout_form_submit")).click();
		assertEquals("?さんはログアウトしました．", logoutPage.getElementById("logout_box_message").asText());
		
		loginPage = ((HtmlAnchor) logoutPage.getElementById("logout_box_login_link")).click();
		assertNotNull(loginPage);

		webClient.close();
	}
	
	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

}
