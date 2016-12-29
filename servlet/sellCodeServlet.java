package gameCenter.servlet;

import gameCenter.control.emplyoeeDAO;
import gameCenter.model.topup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sellCodeServlet
 */
@WebServlet("/sellCodeServlet")
public class sellCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sell Code Servlet");
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(c.getTime());
		
		
		String id = request.getParameter("id");
		emplyoeeDAO em = new emplyoeeDAO();
		boolean sell = em.sellCode(id,date);
		topup top = new topup();
		top = em.getUserPassCode(id);
		
		
		String nameOfTextFile = "E:/imp.txt";
		try {   
		    PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfTextFile));
		    pw.println("****************");
		    pw.println("CodeID : "+top.getCodeId());
		    pw.println("User : "+top.getUser());
		    pw.println("Pass : "+top.getPass());
		    pw.println("Time : "+top.getTime());
		    pw.println("Vlue : "+top.getValue());
		    
		    pw.println("****************");
		    //clean up
		    pw.close();
		} catch(IOException e) {
		   System.out.println(e.getMessage());
		}
		
		if(sell==true){
			
			response.sendRedirect("codeAvailableServlet");
		}else{
			System.out.println("Error Sell Code Servlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
