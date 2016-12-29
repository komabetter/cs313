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

@WebServlet("/betweenDate")
public class betweenDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public betweenDate() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		System.out.println("Start : DATE : "+date);
		System.out.println("END   : DATE : "+date2);
		
		emplyoeeDAO em = new emplyoeeDAO();
		ArrayList<topup> list = new ArrayList<topup>();
		list = em.lookBetweenDate(date, date2);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("listcode", list);
		response.sendRedirect("summaryIncome.jsp"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
