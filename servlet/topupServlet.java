package gameCenter.servlet;

import gameCenter.control.loginDAO;
import gameCenter.control.memberDAO;
import gameCenter.model.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class topupServlet
 */
@WebServlet("/topupServlet")
public class topupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public topupServlet() {
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TOPUP SERVLET");
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(c.getTime());
		memberDAO member = new memberDAO();
		String code = request.getParameter("code");
		String pass = request.getParameter("pass");
		String memberID = request.getParameter("memberid");
		String time = request.getParameter("clock");
		String page = request.getParameter("page");
		boolean check = true;
		loginDAO lo = new loginDAO();
	

		check = lo.checkCodeAvailable(code, pass);
		HttpSession session = null;

		System.out.println("Check :"+check);
		
		
		if(check==true) {
			System.out.println("code : " + code);
			System.out.println("pass : " + pass);
			System.out.println("pamemberIDss : " + memberID);

			ArrayList<topup> list = new ArrayList<topup>();

			member m = new member();
			topup top = new topup();

			m = member.getTimeMember(memberID);
			top = lo.getTimeOfCode(code, pass);

			int total = 0;
			total = m.getTime().getHours() + top.getTime().getHours();
			System.out.println("Member : " + m.getTime().getHours());
			System.out.println("ADD : " + top.getTime().getHours());
			System.out.println("Total : " + total);
			boolean update = true;
			boolean fonfirm = true;
			String x = total + ":" + m.getTime().getMinutes() + ":"
					+ m.getTime().getSeconds();
			System.out.println("Time total : " + x);
			update = member.topUpTimeMember(memberID, x);

			if (!update) {
				System.out.println("Top Up Error");
			} else {
				System.out.println("Top Up Complete");
				fonfirm = member.confirmCode(top.getCodeId(), m.getMemberID(),date);
				if (fonfirm) {
					System.out.println("Everything complete");
				}

				m = member.getTimeMember(memberID);
				list = member.getHistoryTopup(m.getMemberID());

				session = request.getSession(true);
				session.setAttribute("arraylist", list);
				session.setAttribute("currentSessionUser", m);
				response.sendRedirect("member.jsp"); // logged-in page
			}

		} else {
			System.out.println("Code used by another");
			boolean a = true;
			String remain = request.getParameter("clock");
			String logoutId = request.getParameter("memberid");
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
			response.sendRedirect(page+".jsp"); // logged-in page
		}
	}

}
