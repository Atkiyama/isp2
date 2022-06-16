package exercise.lesson06;

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
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage indexPage = null;

		String url = this.getBaseUrl() + "/exercise/lesson06/index.html";
		WebRequest request = new WebRequest(new URL(url));
		indexPage = webClient.getPage(request);
	    ((HtmlInput)indexPage.getElementById("input1")).setValueAttribute("1");
	    ((HtmlInput)indexPage.getElementById("input2")).setValueAttribute("1");
	    ((HtmlButton) indexPage.getElementById("calculate_button")).click();
		assertEquals("1", indexPage.getElementById("input1").asText());
		assertEquals("1", indexPage.getElementById("input2").asText());
		assertEquals("2", indexPage.getElementById("output").asText());
		
		webClient.close();
	}

	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

}
