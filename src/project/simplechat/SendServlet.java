package project.simplechat;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/simplechat/send")
public class SendServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		Chat chat = (Chat) context.getAttribute("chat");
		if (chat == null) {
			chat = new Chat();
			context.setAttribute("chat", chat);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			String message = request.getParameter("message");
			ServletContext context = this.getServletContext();		
			Chat chat = (Chat) context.getAttribute("chat");
			Statement statement = new Statement();
			statement.setUser(user);
			statement.setMessage(message);
			chat.addStatement(statement);
		}	
	}

}
