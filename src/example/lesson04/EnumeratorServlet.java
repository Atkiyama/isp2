package example.lesson04;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/example/lesson04/execute")
public class EnumeratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String input1 = (request.getParameter("input1") == null) ? ""
				: (String) request.getParameter("input1");
		String input2 = (request.getParameter("input2") == null) ? ""
				: (String) request.getParameter("input2");
		String output = null;

		Integer lowerEndpoint = null;
		try {
			lowerEndpoint = Integer.decode(input1);
		} catch (NumberFormatException e) {
		}

		Integer upperEndpoint = null;
		try {
			upperEndpoint = Integer.decode(input2);
		} catch (NumberFormatException e) {
		}

		if (lowerEndpoint != null && upperEndpoint != null) {
			Enumerator enumerator = new Enumerator();
			enumerator.setLowerEndpoint(lowerEndpoint.intValue());
			enumerator.setUpperEndpoint(upperEndpoint.intValue());
			enumerator.execute();
			List<Integer> numberList = enumerator.getNumberList();
			output = convertIntoString(numberList);
		} else {
			output = "";
		}

		response.setContentType("text/html");
		Writer writer = response.getWriter();
		writer.write("<!DOCTYPE html>\n");
		writer.write("<html>\n");
		writer.write("<head>\n");
		writer.write("<title>Enumerator</title>\n");
		writer.write("</head>\n");
		writer.write("<body>\n");
		writer.write("[<span id=\"input1\">" + input1 + "</span>,\n");
		writer.write(" <span id=\"input2\">" + input2 + "</span>]=\n");
		writer.write("{<span id=\"output\">" + output + "</span>}\n");
		writer.write("</body>\n");
		writer.write("</html>\n");
	}

	public static String convertIntoString(List<Integer> numberList) {
		StringBuffer buffer = new StringBuffer();
		if (numberList != null && numberList.size() > 0) {
			buffer.append(String.valueOf(numberList.get(0)));
			for (int i = 1; i < numberList.size(); i++) {
				buffer.append(",");
				buffer.append(String.valueOf(numberList.get(i)));
			}
		}
		return new String(buffer);
	}

}
