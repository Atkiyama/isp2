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

@WebServlet("/project/dbLover/create")
public class CreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       	    request.setCharacterEncoding("UTF-8");
	    String type = (request.getParameter("type") == null) ? ""
				: (String) request.getParameter("type");
	    HttpSession session = request.getSession();
	    Database database = (Database)session.getAttribute("database");
	    int height, width, isSelf;
	    ArrayList<ArrayList<String>> db = new ArrayList<>();
	    if(database.getDb().isEmpty() == true){
		height = database.getHeight();
		width = database.getWidth();
		isSelf = 1;
	    }else{
		db = database.getDb();
		height = db.size();
		width = db.get(0).size();
		isSelf = 0;
	    }
	    if(type.equals("columnMinus")){
		if(height != 0) height--;
	    }else if(type.equals("rowMinus")){
		if(width != 0) width--;
	    }

	    StringBuilder builder = new StringBuilder();
	    builder.append("{");
	    builder.append("\"isSelf\":\"").append(isSelf).append("\",");
	    if(isSelf == 0){
		builder.append("\"DB\":[");
		for(int i = 0; i < height; i++){
		    if(i != 0) builder.append(",");
		    builder.append("[");
		    for(int j = 0; j < width; j++){
			if(j != 0){
			    builder.append(",");
			}
			builder.append("\"").append(db.get(i).get(j)).append("\"");
		    }
		    if(type.equals("rowPlus")) builder.append(",\"\"");
		    builder.append("]");
		}
		if(type.equals("columnPlus")){
		    builder.append(",[");
		    for(int m = 0; m < width; m++){
			if(m != 0) builder.append(",");
			builder.append("\"\"");
		    }
		    builder.append("]");
		}
		builder.append("],");
	    }
	    if(type.equals("columnPlus")){
		height++;
	    }else if(type.equals("rowPlus")){
		width++;
	    }
	    builder.append("\"height\":\"").append(height).append("\",");
	    builder.append("\"width\":\"").append(width).append("\"");
	    builder.append("}");
	    String json = builder.toString();
	    System.out.println(json);
	    response.setContentType("application/json");
	    PrintWriter writer = response.getWriter();
	    writer.append(json);
	    writer.flush();
	}
}
