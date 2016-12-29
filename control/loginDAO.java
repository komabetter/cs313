package gameCenter.control;

import gameCenter.connect.connect;
import gameCenter.model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class loginDAO {

	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	@SuppressWarnings("deprecation")
	public guest getTime(String user, String pass) {
		String sql = "SELECT * FROM login WHERE username=? AND password=?";
		guest g = new guest();
		System.out.println("SQL : " + sql);
		System.out.println("Username : " + user);
		System.out.println("Password : " + pass);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			boolean valid = rs.next();

			if (valid) {
				g.setTimeToPlay(rs.getInt("remain"));
				g.setGuestID(rs.getInt("codeLoginID"));
				g.setTime(rs.getTime("date"));
				System.out.println(g.getTime());
				System.out.println(g.getTime().getHours());
				System.out.println(g.getTime().getMinutes());
				System.out.println(g.getTime().getSeconds());
				g.setValid(true);

			} else if (!valid) {
				g.setValid(false);

			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return g;
	}

	public boolean logout(String id, String time) {
		String sql = "";

		System.out.println("Guest ID : " + id);
		System.out.println("remain : " + time);

		sql = "UPDATE codetopup SET time = ? WHERE codeID=?";
		System.out.println("Time : " + time);
		System.out.println("SQL : " + sql);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setTime(1, java.sql.Time.valueOf(time));
			pstm.setInt(2, Integer.parseInt(id));
			pstm.executeUpdate();
			con.close();
			pstm.close();
			System.out.println("Update Complete");

		} catch (Exception sqlException) {
			System.out.println("Error : " + sqlException);
			return false;
		}

		return true;
	}

	public boolean logout2(String id, String time) {
		String sql = "";

		System.out.println("Guest ID : " + id);
		System.out.println("remain : " + time);

		sql = "UPDATE guest SET time = ? WHERE guestID=?";
		System.out.println("Time : " + time);
		System.out.println("SQL : " + sql);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setTime(1, java.sql.Time.valueOf(time));
			pstm.setInt(2, Integer.parseInt(id));
			pstm.executeUpdate();
			con.close();
			pstm.close();
			System.out.println("Update Complete");

		} catch (Exception sqlException) {
			System.out.println("Error : " + sqlException);
			return false;
		}

		return true;
	}

	public member loginMember(String user, String pass) {
		String sql = "SELECT * FROM member WHERE username=? AND password=?";
		member mem = new member();
		System.out.println("SQL : " + sql);
		System.out.println("Username : " + user);
		System.out.println("Password : " + pass);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			boolean valid = rs.next();

			if (valid) {
				mem.setMemberID(rs.getInt("memberID"));
				mem.setFristName(rs.getString("fristname"));
				mem.setLastName(rs.getString("lastname"));
				mem.setTime(rs.getTime("time"));
				mem.setValid(true);

			} else if (!valid) {
				mem.setValid(false);

			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return mem;
	}

	public guest loginGuest(String user, String pass) {
		String sql = "SELECT * FROM codetopup WHERE user=? AND pass=?";
		guest gu = new guest();
		System.out.println("SQL : " + sql);
		System.out.println("Username : " + user);
		System.out.println("Password : " + pass);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			boolean valid = rs.next();

			if (valid) {
				gu.setGuestID(rs.getInt("codeID"));
				gu.setTime(rs.getTime("time"));
				gu.setValid(true);

			} else if (!valid) {
				gu.setValid(false);

			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return gu;
	}

	public employee loginEmployee(String user, String pass) {
		String sql = "SELECT * FROM employee WHERE username=? AND password=?";
		employee gu = new employee();
		System.out.println("SQL : " + sql);
		System.out.println("Username : " + user);
		System.out.println("Password : " + pass);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			boolean valid = rs.next();

			if (valid) {
				gu.setEmployeeID(rs.getInt("employeeID"));
				gu.setFristname(rs.getString("fristname"));
				gu.setLastname(rs.getString("lastname"));
				gu.setSalary(rs.getDouble("salary"));
				gu.setValid(true);

			} else if (!valid) {

				gu.setValid(false);
			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return gu;
	}

	public boolean logoutMember(String id, String time) {
		String sql = "";

		System.out.println("Guest ID : " + id);
		System.out.println("remain : " + time);

		sql = "UPDATE member SET time = ? WHERE memberID=?";
		System.out.println("SQL : " + sql);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setTime(1, java.sql.Time.valueOf(time));
			pstm.setInt(2, Integer.parseInt(id));
			pstm.executeUpdate();
			con.close();
			pstm.close();
			System.out.println("Update Complete");

		} catch (Exception sqlException) {
			System.out.println("Error : " + sqlException);
			return false;
		}

		return true;
	}

	public topup getTimeOfCode(String user, String pass) {
		System.out.println("**************GetTimeOfCode**************");
		String sql = "SELECT * FROM codeTopup WHERE user=? AND pass=?";
		topup top = new topup();
		System.out.println("SQL : " + sql);
		System.out.println("user : " + user);
		System.out.println("pass : " + pass);
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			boolean valid = rs.next();

			if (valid) {
				top.setCodeId(rs.getInt("codeID"));
				top.setUser(rs.getString("user"));
				top.setPass(rs.getString("pass"));
				top.setValue(rs.getInt("value"));
				top.setTime(rs.getTime("time"));

			} else if (!valid) {

			}
			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}
		System.out.println("Back to Servket");
		return top;
	}

	public boolean checkCodeAvailable(String user, String pass) {
		System.out.println("checkCodeAvailable DAO ");
		boolean check = true;
		String sql = "";
		System.out.println("User : " + user);
		System.out.println("Pass : " + pass);

		sql = "SELECT avilable FROM codetopup WHERE user=? AND pass=?";
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();

			if (rs.next()) {
				int a = rs.getInt("avilable");
				System.out.println("avilable : " + a);
				if (a == 1) {
					check = true;
				} else {
					check = false;
				}
			} else {
				check = false;
			}

			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println("Error : " + sqlException);
			return false;
		}
		System.out.println("Go to servlet :" + check);
		return check;
	}
}
