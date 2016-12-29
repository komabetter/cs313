package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.model.topup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class insertCode
 */
@WebServlet("/insertCode")
public class insertCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public insertCode() {
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
		// TODO Auto-generated method stub
System.out.println("Insert Servlet");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String time = request.getParameter("time");
		System.out.println("User : "+user);
		System.out.println("pass : "+pass);
		System.out.println("time : "+time);
		boolean checkuser = true;
		emplyoeeDAO em = new emplyoeeDAO();
		checkuser = em.checkUsercode(user);

		if (checkuser == true) {
			topup tp = new topup();
			if ("01:00:00".equals(time)) {
				tp.setValue(50);
			} else {
				tp.setValue(100);
			}

			tp.setUser(user);
			tp.setPass(pass);
			tp.setTime(java.sql.Time.valueOf(time));
			System.out.println("DATE : "+tp.getDate());
			boolean check;
			System.out.println("Goto DAO InsertCode");
			check = em.insertCode(tp);
			if (check == true) {
				

				response.sendRedirect("codeAvailableServlet");
				
			} else {
				System.out.println("Error InsertCode");
			}
		}else{
			System.out.println("Username userd");
			response.sendRedirect("codeAvailableServlet");
		}

	}

}
