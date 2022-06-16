package exercise.lesson04;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/exercise/lesson04/execute")
public class AdderServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String input1 = (request.getParameter("input1") == null) ? ""
				: (String) request.getParameter("input1");
		String input2 = (request.getParameter("input2") == null) ? ""
				: (String) request.getParameter("input2");
		String output = null;

		Integer augend = null;
		try {
			augend = Integer.decode(input1);
		} catch (NumberFormatException e) {
		}

		Integer addend = null;
		try {
			addend = Integer.decode(input2);
		} catch (NumberFormatException e) {
		}

		if (augend != null && addend != null) {
			Adder adder = new Adder();
			adder.setAugend(augend.intValue());
			adder.setAddend(addend.intValue());
			adder.execute();
			int sum = adder.getSum();
			output = String.valueOf(sum);
		} else {
			output = "";
		}

		response.setContentType("text/html");
		Writer writer = response.getWriter();
		writer.write("<!DOCTYPE html>\n");
		writer.write("<html>\n");
		writer.write("<head>\n");
		writer.write("<title>Adder</title>\n");
		writer.write("</head>\n");
		writer.write("<body>\n");
		writer.write("[<span id=\"input1\">" + input1 + "</span>,\n");
		writer.write(" <span id=\"input2\">" + input2 + "</span>]=\n");
		writer.write("{<span id=\"output\">" + output + "</span>}\n");
		writer.write("</body>\n");
		writer.write("</html>\n");
	}


}
