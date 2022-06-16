package project.dbLover;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/dbLover/save")
public class SaveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       		request.setCharacterEncoding("UTF-8");
		
		String name = (request.getParameter("name") == null) ? "database"
				: (String) request.getParameter("name");
		HttpSession session = request.getSession();
		Database database = (Database)session.getAttribute("database");
		int isError;
		database.setName(name);
		isError = database.write();
		StringBuilder builder = new StringBuilder();
		builder.append("{\"isError\":\"").append(isError).append("\"}");
		String json = builder.toString();
		System.out.println(json);
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.append(json);
		writer.flush();
	}
}
