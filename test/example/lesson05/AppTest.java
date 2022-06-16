package example.lesson05;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppTest {

	@Test
	public void test() throws Exception {
		this.test(this.getBaseUrl() + "/example/lesson05/hello1.html");
		this.test(this.getBaseUrl() + "/example/lesson05/hello2.html");
	}
	
	private void test(String url) throws Exception {
		WebClient webClient = new WebClient();
		WebRequest request = new WebRequest(new URL(url));
		HtmlPage page = webClient.getPage(request);
		assertThat(page.getBody().getTextContent(), equalTo("Hello, world!"));
		webClient.close();
	}
	
	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

}
