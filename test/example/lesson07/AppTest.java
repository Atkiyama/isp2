package example.lesson07;

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
		this.test(this.getBaseUrl() + "/example/lesson07/index.html", "get");
		this.test(this.getBaseUrl() + "/example/lesson07/index.html", "post");
		this.test(this.getBaseUrl() + "/example/lesson07/index.html", "put");
		this.test(this.getBaseUrl() + "/example/lesson07/index.html", "delete");
	}

	private void test(String url, String buttonName) throws Exception {
		this.checkServletPackageName();

		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		WebRequest request = new WebRequest(new URL(url));
		HtmlPage indexPage = webClient.getPage(request);
	    ((HtmlInput)indexPage.getElementById("message")).setValueAttribute("test");
	    ((HtmlButton) indexPage.getElementById(buttonName + "_button")).click();
		for (int i = 0; i < 4; i++) {
            if(!indexPage.getElementById("echo_message").asText().isEmpty()) {
                break;
            }
            synchronized (indexPage) {
                indexPage.wait(500);
            }
        }		
		assertEquals("test(" + buttonName + ")", indexPage.getElementById("echo_message").asText());
		
		webClient.close();
	}
	
	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}
	
	protected void checkServletPackageName() {
		assertEquals("example.lesson07.EchoServlet", example.lesson07.EchoServlet.class.getName());
	}

}
