package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.control.loginDAO;
import gameCenter.control.memberDAO;
import gameCenter.model.employee;
import gameCenter.model.guest;
import gameCenter.model.member;
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
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean a = true;
		String remain = request.getParameter("clock");
		String logoutId = request.getParameter("logoutID");
		String gu = request.getParameter("gu");
		loginDAO lo = new loginDAO();

		if(gu==null){
			a = lo.logout2(logoutId, remain);
		}else{
		a = lo.logout(logoutId, remain);
		}
		if (!a) {
			System.out.println("Error ");
		} else {
			System.out.println("Log out Complete");
		}

		HttpSession session = request.getSession(false);
		if (session != null){
			session.invalidate();}
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		ArrayList<topup> list = new ArrayList<topup>();
		member m = new member();
		guest g = new guest();
		employee em = new employee();
		
		loginDAO login = new loginDAO();
		memberDAO member = new memberDAO();
		m = login.loginMember(username, password);
		g = login.loginGuest(username, password);
		em = login.loginEmployee(username, password);

		if (g.isValid()) {
			
			System.out.println("Guest Go JSP");
			HttpSession session = request.getSession(true);
			session.setAttribute("currentSessionUser", g);
			
			response.sendRedirect("guest.jsp"); // logged-in page
		}

		else if (m.isValid()) {
			list = member.getHistoryTopup(m.getMemberID());
			System.out.println("Time "+m.getTime().toString());
			HttpSession session = request.getSession(true);
			session.setAttribute("arraylist", list);
			session.setAttribute("currentSessionUser", m);
			
			if("00:00:00".equals(m.getTime().toString())){
				response.sendRedirect("topupTimeZero.jsp"); // logged-in page
			}else{
				System.out.println("Member Go JSP");
				
				response.sendRedirect("member.jsp"); // logged-in page
			}
		}

		else if (em.isValid()) {
			System.out.println("Employee Go JSP");
			HttpSession session = request.getSession(true);
			session.setAttribute("currentSessionUser", em);
			request.getRequestDispatcher("employee.jsp")
			.forward(request, response);
			//response.sendRedirect("employee.jsp"); 
		} else {

			request.setAttribute("test", "Login fail Plase Try Again");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}

}
