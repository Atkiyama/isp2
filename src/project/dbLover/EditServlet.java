package project.dbLover;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/dbLover/edit")
public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       		request.setCharacterEncoding("UTF-8");
		String keishiki = (request.getParameter("keishiki") == null) ? ""
				: (String) request.getParameter("keishiki");

		String tate = (request.getParameter("height") == null) ? ""
				: (String) request.getParameter("height");

		String yoko = (request.getParameter("width") == null) ? ""
				: (String) request.getParameter("width");

		String csvName = (request.getParameter("csvName") == null) ? ""
				: (String) request.getParameter("csvName");

		HttpSession session = request.getSession();
		Database database = null;
		if((keishiki.equals("self") && !tate.equals("") && !yoko.equals("")) || (keishiki.equals("read") && !csvName.equals(""))){
		    if(keishiki.equals("self")){
			Integer height = null;
			Integer width = null;
			try {
			    height = Integer.decode(tate);
			    width = Integer.decode(yoko);
			} catch (NumberFormatException e) {
			}
			database = new Database(height, width);
		    }else if(keishiki.equals("read")){
			csvName += ".csv";
			database = new Database(csvName);
			database.read();
		    }else{
		    }
		    session.setAttribute("database", database);
		    response.sendRedirect("database.html");
		}else{
		    response.sendRedirect("top.html");
		}
	}
}
