package gameCenter.servlet;

import gameCenter.control.loginDAO;
import gameCenter.control.memberDAO;
import gameCenter.model.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updateTime
 */
@WebServlet("/updateTime")
public class updateTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		boolean a = true;
		String remain = request.getParameter("clock");
		String logoutId = request.getParameter("logoutID");
		String page = request.getParameter("page");
		loginDAO lo = new loginDAO();
		memberDAO member = new memberDAO();
		member m = new member();
		System.out.println("Time : " + remain);
		a = lo.logoutMember(logoutId, remain);
		session = request.getSession(false);
		m = member.getTimeMember(logoutId);
		System.out.println("M : Time : " + m.getTime());
		if (!a) {
			System.out.println("Error ");
		} else {
			System.out.println("Next Page");
		}
		
		
		
		
		session = request.getSession(true);
		session.setAttribute("currentSessionUser", m);
		response.sendRedirect(page + ".jsp"); // logged-in page
	}

}
