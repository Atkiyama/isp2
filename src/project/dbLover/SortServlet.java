package project.dbLover;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/dbLover/sort")
public class SortServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();
	    Database database = (Database)session.getAttribute("database");

	    String sortType = (request.getParameter("sortType") == null) ? ""
				: (String) request.getParameter("sortType");
	    String sort = (request.getParameter("sort") == null) ? ""
				: (String) request.getParameter("sort");

	    Integer sortNum = null;
	    
	    try {
       		sortNum = Integer.decode(sort);
	    } catch (NumberFormatException e) {
	    }
	    if(sortType.equals("descending")){
		database.sort(Order.DESCENDING, sortNum.intValue());
	    }else if(sortType.equals("ascending")){
		database.sort(Order.ASCENDING, sortNum.intValue());
	    }
	    session.setAttribute("database", database);
	}
}
