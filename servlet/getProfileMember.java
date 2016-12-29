package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.control.memberDAO;
import gameCenter.model.member;
import gameCenter.model.topup;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getProfileMember")
public class getProfileMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getProfileMember() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("getProfileMember");
		System.out.println("ID : "+id);
		member mem = new member();
		ArrayList<topup> list = new ArrayList<topup>();
		
		emplyoeeDAO em = new emplyoeeDAO();
		memberDAO m = new memberDAO();
		
		
		mem = em.getProfileMember(id);
		list = m.getHistoryTopup(Integer.parseInt(id));
		
		System.out.println("Member : "+mem.getFristName());
		
		for(int i=0;i<list.size();i++){
			System.out.println("CODE ID : "+list.get(i).getCodeId());
		}
			
		System.out.println("GO TO memberDetail");
		request.setAttribute("list", list);
		request.setAttribute("mem", mem);
		
		request.getRequestDispatcher("memberDetail.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
