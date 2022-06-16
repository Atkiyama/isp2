package example.lesson06;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppTest {

	@Test
	public void test() throws Exception {
		this.test(this.getBaseUrl() + "/example/lesson06/index.html");
	}
	
	private void test(String url) throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		WebRequest request = new WebRequest(new URL(url));
		HtmlPage indexPage = webClient.getPage(request);
		((HtmlButton) indexPage.getElementById("header-login_button")).click();
	    ((HtmlInput)indexPage.getElementById("login_dialog-username_field")).setValueAttribute("test");
	    ((HtmlButton) indexPage.getElementById("login_dialog-login_button")).click();
		assertEquals("test", indexPage.getElementById("header-username").asText());
		((HtmlButton) indexPage.getElementById("header-logout_button")).click();
		assertEquals("test", indexPage.getElementById("logout_dialog-username").asText());
		((HtmlButton) indexPage.getElementById("logout_dialog-close_button")).click();
		assertEquals("?", indexPage.getElementById("header-username").asText());
		
		webClient.close();
	}
	
	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

}
