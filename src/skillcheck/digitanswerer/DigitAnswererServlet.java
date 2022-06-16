package skillcheck.digitanswerer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/skillcheck/digitanswerer/index")
public class DigitAnswererServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String input1 = (request.getParameter("integer") == null) ? ""
				: (String) request.getParameter("integer");
		String method = request.getParameter("method");
		String output = null;

		Integer integer = null;
		try {
			integer = Integer.decode(input1);
		} catch (NumberFormatException e) {
		}

		if (integer != null) {
			Number number = new Number();
			number.setValue(integer);
			output = String.valueOf(number.getDigit());
		} else {
			output = "";
		}
		//ここでjsに送信

			StringBuilder builder = new StringBuilder();
			builder.append('{');
			builder.append("\"output\":\"").append(output).append("\",");
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
