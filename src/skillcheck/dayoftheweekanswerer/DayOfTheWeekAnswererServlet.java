package skillcheck.dayoftheweekanswerer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DayOfTheWeekAnswererServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String input1 = (request.getParameter("date") == null) ? ""
				: (String) request.getParameter("date");
		String method = request.getParameter("method");
		String output = null;

		if (input1 != null) {
			DayOfTheWeekAnswerer dayOfTheWeekAnswerer = new DayOfTheWeekAnswerer();
			dayOfTheWeekAnswerer.setDate(input1);
			output=dayOfTheWeekAnswerer.getDayOfTheWeekAnswer();
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
