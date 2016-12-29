package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.model.topup;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class summaryIncome
 */
@WebServlet("/summaryIncome")
public class summaryIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public summaryIncome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		emplyoeeDAO em = new emplyoeeDAO();
		ArrayList<topup> list = new ArrayList<topup>();
		
		list = em.codeUnavailable();
		
		
		System.out.println("List Go JSP");
		HttpSession session = request.getSession(true);
		session.setAttribute("listcode", list);
		
		response.sendRedirect("summaryIncome.jsp"); // logged-in page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
