package exercise.lesson05;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.IsEqual.equalTo;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AppTest {

	@Test
	public void test() throws Exception {
		String url = this.getBaseUrl() + "/exercise/lesson05/index.html";
		WebClient webClient = new WebClient();
		WebRequest request = new WebRequest(new URL(url));
		HtmlPage page = webClient.getPage(request);
		String actualMessage = page.getBody().getTextContent();
		Pattern pattern = Pattern.compile("^(\\d+)秒のときの秒針の位置は\\(-?\\d+, -?\\d+\\)です$");
		Matcher matcher = pattern.matcher(actualMessage);
		if (matcher.find()) {
			int length = 100;
			int second = Integer.parseInt(matcher.group(1));
		    double theta = second * Math.PI / 30.0;
		    int x = (int) Math.round(length * Math.sin(theta));
		    int y = (int) Math.round(length * Math.cos(theta));
			String expectedMessage = String.format("%d秒のときの秒針の位置は(%d, %d)です", second, x, y);
			assertThat(actualMessage, equalTo(expectedMessage));	
		}
		else {
			fail();
		}
		webClient.close();
	}

	
	protected String getBaseUrl() {
		return "http://localhost:8080/isp2";
	}

}
