package skillcheck.dayoftheweekanswerer;

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
	public void testExecute() throws Exception {
		this.checkServletPackageName();
		assertEquals("Sunday", this.get("2004-02-29"));
		assertEquals("Monday", this.get("1988-02-29"));
		assertEquals("Tuesday", this.get("2000-02-29"));
		assertEquals("Wednesday", this.get("2008-11-12"));
		assertEquals("Thursday", this.get("1996-02-29"));
		assertEquals("Friday", this.get("2008-02-29"));
		assertEquals("Saturday", this.get("1992-02-29"));
	}
	
	private String get(String date) throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage indexPage = null;

		String url = "http://localhost:8080/isp2/skillcheck/dayoftheweekanswerer/index.html";
		WebRequest request = new WebRequest(new URL(url));
		indexPage = webClient.getPage(request);
	    ((HtmlInput) indexPage.getElementById("date")).setValueAttribute(date);
	    ((HtmlButton) indexPage.getElementById("execute_button")).click();
		for (int i = 0; i < 10; i++) {
            if (!indexPage.getElementById("weekday").asText().isEmpty()) {
                break;
            }
            synchronized (indexPage) {
                indexPage.wait(500);
            }
        }		
		String weekday = indexPage.getElementById("weekday").asText();
		
		webClient.close();
		
		return weekday;
	}

	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

	protected void checkServletPackageName() {
		assertEquals("skillcheck.dayoftheweekanswerer.DayOfTheWeekAnswererServlet", skillcheck.dayoftheweekanswerer.DayOfTheWeekAnswererServlet.class.getName());
	}

}