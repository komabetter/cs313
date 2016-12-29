package gameCenter.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;

import gameCenter.connect.connect;
import gameCenter.model.*;

public class emplyoeeDAO {

	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	public boolean registerMember(member m) {
		String sql = "INSERT INTO member (username,password,fristname,lastname,time) "
				+ "VALUES(?,?,?,?,?)";
		boolean insert = true;
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m.getUsername());
			pstm.setString(2, m.getPassword());
			pstm.setString(3, m.getFristName());
			pstm.setString(4, m.getLastName());
			pstm.setTime(5, java.sql.Time.valueOf("01:00:02"));
			pstm.executeUpdate();
			System.out.println("Insert  Complete");

		} catch (Exception salException) {
			System.out.println("Error " + salException);
			insert = false;
		}

		return insert;
	}
	
	
	
	
	
	
	
	

	public ArrayList<topup> codeAvailable() {
		String sql = "SELECT * FROM codetopup WHERE sell=?";
		topup top;
		ArrayList<topup> list = new ArrayList<topup>();
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, 1);
			rs = pstm.executeQuery();
			while (rs.next()) {
				top = new topup();
				top.setCodeId(rs.getInt("codeID"));
				top.setUser(rs.getString("user"));
				top.setPass(rs.getString("pass"));
				top.setValue(rs.getInt("value"));
				top.setTime(rs.getTime("time"));
				list.add(top);
			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println("Error" + sqlException);
		}

		return list;
	}
	
	
	
	
	

	public boolean insertCode(topup top) {
		String sql = "INSERT INTO codetopup (user,pass,value,sell,time,avilable) VALUE(?,?,?,?,?,?)";
		System.out.println("insertCode Here");
		System.out.println("user : " + top.getUser());
		System.out.println("pass : " + top.getPass());
		System.out.println("time : " + top.getTime());
		System.out.println("value : " + top.getValue());
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, top.getUser());
			pstm.setString(2, top.getPass());
			pstm.setInt(3, top.getValue());
			pstm.setInt(4, 1);
			pstm.setTime(5, top.getTime());
			pstm.setInt(6, 1);
			pstm.executeUpdate();

			pstm.close();
			con.close();
		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		System.out.println("Back to servlet");
		return true;
	}

	public boolean checkUsercode(String user) {
		System.out.println("Check Usercode");
		String sql = "select * from codetopup where user=?";
		boolean check = true;
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			if (rs.next()) {
				check = false;
			} else {
				check = true;
			}

			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {

		}

		System.out.println("Back to Servlet");
		return check;
	}

	public member getProfileMember(String id) {
		System.out.println("getProfileMember");
		String sql = "SELECT * FROM member WHERE memberID = ?";
		member mem = new member();

		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				mem.setFristName(rs.getString("fristname"));
				mem.setLastName(rs.getString("lastname"));
				mem.setUsername(rs.getString("username"));
				mem.setPassword(rs.getString("password"));
				mem.setTime(rs.getTime("time"));
				con.close();
				pstm.close();
				rs.close();

			} else {
				System.out.println("No member");
			}

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return mem;
	}

	public boolean sellCode(String id,String date){
		boolean sell = true;
		String sql = "UPDATE codeTopup SET sell = ?,date = ? WHERE codeID=?";
		
		try{
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setString(2, date);
			pstm.setInt(3, Integer.parseInt(id));
			pstm.executeUpdate();
			
			con.close();
			pstm.close();
		}catch(Exception sqlException){
			System.out.println(sqlException);
			return false;
		}
		
		
		return sell;
	}

	public topup getUserPassCode(String id){
		topup top = new topup();
		String sql="select  * from codetopup where codeID=?";
		
		try{
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(id));
			rs = pstm.executeQuery();
			
			if(rs.next()){
				top.setCodeId(rs.getInt("codeID"));
				top.setUser(rs.getString("user"));
				top.setPass(rs.getString("pass"));
				top.setTime(rs.getTime("time"));
				top.setValue(rs.getInt("value"));
				con.close();
				pstm.close();
				rs.close();
				
			}else{
				System.out.println("Error getUserPassCode");
			}
		}catch(Exception sqlException){
			System.out.println(sqlException);
		}
		
		
		return top;
	}
	
	public int getTotalMoneyIncome(){
		System.out.println("getTotalMoneyIncome");
		String sql = "SELECT SUM(value) FROM codetopup WHERE sell=?";
		int total = 0 ;
		try{
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, 0);
			rs = pstm.executeQuery();
			if(rs.next()){
				total=rs.getInt("SUM(value)");
			}
			con.close();
			pstm.close();
			rs.close();
			
		}catch(Exception sqlException){
			System.out.println(sqlException);
		}
		System.out.println("Total : "+total);
		return total ;
	}
	
	
	public ArrayList<topup> codeUnavailable() {
		String sql = "SELECT * FROM codetopup WHERE sell=?";
		topup top;
		ArrayList<topup> list = new ArrayList<topup>();
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, 0);
			rs = pstm.executeQuery();
			while (rs.next()) {
				top = new topup();
				top.setCodeId(rs.getInt("codeID"));
				top.setUser(rs.getString("user"));
				top.setPass(rs.getString("pass"));
				top.setValue(rs.getInt("value"));
				top.setTime(rs.getTime("time"));
				top.setDate(rs.getDate("date"));
				list.add(top);
			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println("Error" + sqlException);
		}

		return list;
	}
	
	public ArrayList<topup> lookBetweenDate(String date1,String date2){
		topup top ;
		ArrayList<topup> list = new ArrayList<topup>();
		String sql="SELECT * FROM codetopup WHERE sell=0 AND date BETWEEN ? AND ? order by date desc";
		
		try{
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, date1);
			pstm.setString(2, date2);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				top = new topup();
				top.setCodeId(rs.getInt("codeID"));
				top.setUser(rs.getString("user"));
				top.setPass(rs.getString("pass"));
				top.setValue(rs.getInt("value"));
				top.setTime(rs.getTime("time"));
				top.setDate(rs.getDate("date"));
				list.add(top);
			}
			
			
		}catch(Exception sqlException){
			System.out.println(sqlException);
		}
		
		
		
		return list;
	}
	
	
	
}
