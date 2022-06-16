package example.lesson04;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppTest {

	@Test
	public void test() throws Exception {
		this.checkServletPackageName();

		WebClient webClient = new WebClient();

		String url = this.getBaseUrl() + "/example/lesson04/input.html";
		WebRequest request = new WebRequest(new URL(url));
		HtmlPage inputPage = webClient.getPage(request);
		HtmlForm form = inputPage.getForms().get(0);
	    form.getInputByName("input1").setValueAttribute("1");
	    form.getInputByName("input2").setValueAttribute("5");
	    
		HtmlPage outputPage = form.getInputByValue("Execute").click();
		assertEquals("1", outputPage.getElementById("input1").asText());
		assertEquals("5", outputPage.getElementById("input2").asText());
		assertEquals("1,2,3,4,5", outputPage.getElementById("output").asText());
		
		webClient.close();
	}

	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

	protected void checkServletPackageName() {
		assertEquals("example.lesson04.EnumeratorServlet", example.lesson04.EnumeratorServlet.class.getName());
	}

}
