package project.dbLover;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/project/dbLover/update")
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
       		request.setCharacterEncoding("UTF-8");
		String update = (request.getParameter("update") == null) ? ""
				: (String) request.getParameter("update");
		String[] datas = update.split(";;", -1);
		HttpSession session = request.getSession();
		Database database = (Database)session.getAttribute("database");
		ArrayList<ArrayList<String>> db = new ArrayList<>();
		for(int i = 0; i < datas.length; i++){
		    ArrayList<String> list = new ArrayList<>(Arrays.asList(datas[i].split(",,", -1)));
		    db.add(list);
		}
		database.setDb(db);
		session.setAttribute("database", database);
	}
}
