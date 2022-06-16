package example.lesson07;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/example/lesson07/echo")
public class EchoServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		String method = request.getParameter("method");
		this.echo(message, method, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		String method = request.getParameter("method");
		this.echo(message, method, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = request.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] b = new byte[1];
		while(true) {
			int size = inputStream.read(b);
			if(size == -1) {
				break;
			}
			outputStream.write(b);
		}
		String urlEncodedString = new String(outputStream.toByteArray());
		Pattern pattern = java.util.regex.Pattern.compile("message=(.+)&method=(.+)");
		Matcher matcher = pattern.matcher(urlEncodedString);
		if(matcher.matches()) {
			String message = matcher.group(1);
			String method = matcher.group(2);
			this.echo(message, method, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		String method = request.getParameter("method");
		this.echo(message, method, response);
	}
	
	private void echo(String message, String method, HttpServletResponse response) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append('{');
		builder.append("\"message\":\"").append(message).append("\",");
		builder.append("\"method\":\"").append(method).append("\"");
		builder.append('}');
		String json = builder.toString();
		System.out.println(json);
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.append(json);
		writer.flush();
	}

}
