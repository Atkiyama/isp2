package project.simplechat;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class AppTest {

	@Test
	public void test() throws Exception {
		this.checkServeltPackageName();
		
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage indexPage = null;
		HtmlPage mainPage = null;

		String url = this.getBaseUrl() + "/project/simplechat/index.html";
		WebRequest request = new WebRequest(new URL(url));
		
		indexPage = webClient.getPage(request);
	    ((HtmlInput)indexPage.getElementById("name")).setValueAttribute("Test");
	    
	    mainPage = ((HtmlSubmitInput)indexPage.getElementById("start_button")).click();
	    ((HtmlInput)mainPage.getElementById("message")).setValueAttribute("Hogehoge");
	    ((HtmlButton) mainPage.getElementById("send_button")).click();
		String statementList = null;
		for(int i = 0; i < 10; i++) {
			statementList = mainPage.getElementById("statement_list").asText();
			if(!statementList.isEmpty()) {
				break;
			}
            synchronized(mainPage) {
                mainPage.wait(500);
            }
        }
		assertEquals("Test:\tHogehoge", statementList);
		
	    indexPage = ((HtmlSubmitInput)mainPage.getElementById("end_button")).click();
		String name = ((HtmlInput)indexPage.getElementById("name")).getValueAttribute();
		assertEquals(name, "");
     	
		webClient.close();
	}

	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}
	
	protected void checkServeltPackageName() {
		assertEquals("project.simplechat.StartServlet", project.simplechat.StartServlet.class.getName());
		assertEquals("project.simplechat.SendServlet", project.simplechat.SendServlet.class.getName());
		assertEquals("project.simplechat.UpdateServlet", project.simplechat.UpdateServlet.class.getName());
		assertEquals("project.simplechat.EndServlet", project.simplechat.EndServlet.class.getName());		
	}

}
