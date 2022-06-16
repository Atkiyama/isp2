package exercise.lesson03;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppTest {

	@Test
	public void testExecute() throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage inputPage = null;
		HtmlPage outputPage = null;
		
		String url = this.getBaseUrl() + "/exercise/lesson03/input.html";
		WebRequest request = new WebRequest(new URL(url));
		inputPage = webClient.getPage(request);
		HtmlForm form = inputPage.getForms().get(0);
	    form.getInputByName("input1").setValueAttribute("1");
	    form.getInputByName("input2").setValueAttribute("1");
	    
		outputPage = form.getInputByValue("Execute").click();
		assertEquals("1", outputPage.getElementById("input1").asText());
		assertEquals("1", outputPage.getElementById("input2").asText());
		assertEquals("2", outputPage.getElementById("output").asText());
		
		webClient.close();
	}

	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

}
