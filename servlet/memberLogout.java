package gameCenter.servlet;

import gameCenter.control.loginDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class memberLogout
 */
@WebServlet("/memberLogout")
public class memberLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean a = true;
		String remain = request.getParameter("clock");
		String logoutId = request.getParameter("logoutID");
		loginDAO lo = new loginDAO();
		System.out.println("Time : "+remain);

		a = lo.logoutMember(logoutId, remain);

		if (!a) {
			System.out.println("Error ");
		} else {
			System.out.println("Log out Complete");
		}

		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
