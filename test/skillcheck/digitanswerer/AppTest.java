package skillcheck.digitanswerer;

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
	public void testCheck() throws Exception {
		this.checkServletPackageName();
		String url = this.getBaseUrl() + "/skillcheck/digitanswerer/index.html";
		assertEquals("1", this.get(url, "1"));
		assertEquals("2", this.get(url, "10"));
		assertEquals("3", this.get(url, "100"));
	}

	private String get(String url, String integer) throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage indexPage = null;

		WebRequest request = new WebRequest(new URL(url));
		indexPage = webClient.getPage(request);
	    ((HtmlInput) indexPage.getElementById("integer")).setValueAttribute(integer);
	    ((HtmlButton) indexPage.getElementById("execute_button")).click();
		for (int i = 0; i < 10; i++) {
            if (!indexPage.getElementById("digit").asText().isEmpty()) {
                break;
            }
            synchronized (indexPage) {
                indexPage.wait(500);
            }
        }		
		String digit = indexPage.getElementById("digit").asText();
		
		webClient.close();
		
		return digit;
	}
	
	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

	protected void checkServletPackageName() {
		assertEquals("skillcheck.digitanswerer.DigitAnswererServlet", skillcheck.digitanswerer.DigitAnswererServlet.class.getName());
	}

}