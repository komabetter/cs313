package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.control.loginDAO;
import gameCenter.model.topup;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/codeAvailableServlet")
public class codeAvailableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public codeAvailableServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		emplyoeeDAO em = new emplyoeeDAO();
		ArrayList<topup> list = new ArrayList<topup>();
		
		list = em.codeAvailable();
		
		System.out.println("List Go JSP");
		HttpSession session = request.getSession(true);
		session.setAttribute("listcode", list);
	
		response.sendRedirect("codelist.jsp"); // logged-in page		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
