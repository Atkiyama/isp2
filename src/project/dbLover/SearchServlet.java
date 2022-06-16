package project.dbLover;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

@WebServlet("/project/dbLover/search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();
	    Database database = (Database)session.getAttribute("database");

	    String search = (request.getParameter("search") == null) ? ""
				: (String) request.getParameter("search");

	    ArrayList<ArrayList<String>> db = new ArrayList<>();
	    
	    db = database.search(search);
	    int i = 0;
	    StringBuilder builder = new StringBuilder();
	    builder.append("{");
	    builder.append("\"search\":[");
	    for(ArrayList<String> list : db){
		if(i != 0){
		    builder.append(",");
		}
		i = 1;
		builder.append("[");
		for(String data : list){
		    if(i != 1){
			builder.append(",");
		    }else{
			i = 2;
		    }
		    builder.append("\"").append(data).append("\"");
		}
		builder.append("]");
	    }
	    builder.append("]");
	    builder.append("}");
	    String json = builder.toString();
	    System.out.println(json);
	    response.setContentType("application/json");
	    PrintWriter writer = response.getWriter();
	    writer.append(json);
	    writer.flush();
	}
}
