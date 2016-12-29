package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.model.employee;
import gameCenter.model.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerServlet() {
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

		String fristname = request.getParameter("name");
		String lastname = request.getParameter("lname");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		member m = new member();
		m.setFristName(fristname);
		m.setLastName(lastname);
		m.setUsername(user);
		m.setPassword(pass);
		System.out.println("*****************");
		System.out.println(m.getFristName());
		System.out.println(m.getLastName());
		System.out.println(m.getUsername());
		System.out.println(m.getPassword());
		System.out.println("*****************");
		boolean register = true;
		emplyoeeDAO em = new emplyoeeDAO();
		register  = em.registerMember(m);
		
		if(register){
			System.out.println("Insert complete");
			response.sendRedirect("employee.jsp");
		}else{
			System.out.println("Insert Error");
			response.sendRedirect("register.jsp");
		}
		
		

	}

}
