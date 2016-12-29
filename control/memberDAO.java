package gameCenter.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import gameCenter.connect.connect;
import gameCenter.model.member;
import gameCenter.model.topup;

public class memberDAO {

	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	public member getTimeMember(String memberid) {
		System.out.println("*********************");
		System.out.println("getTimeMember");
		System.out.println("*********************");
		String sql = "SELECT * FROM member WHERE memberID=?";
		member mem = new member();
		System.out.println("SQL : " + sql);
		System.out.println("Memberid : " + memberid);

		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, memberid);
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

	public ArrayList<topup> getHistoryTopup(int i) {
		System.out.println("*********************");
		System.out.println("getHistoryTopup");
		System.out.println("*********************");
		String sql = "SELECT topupdetail.memberID,topupdetail.codeID,value,codetopup.time,codetopup.datetopup FROM codetopup,topupdetail,member WHERE codetopup.codeID = topupdetail.codeID AND member.memberID = topupdetail.memberID AND topupdetail.memberID = ? order by codetopup.datetopup desc";
		topup top;
		ArrayList<topup> list = new ArrayList<topup>();
		System.out.println("SQL : " + sql);
		System.out.println("Memberid : " + i);

		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, i);
			rs = pstm.executeQuery();

			while (rs.next()) {
				top = new topup();

				top.setCodeId(rs.getInt("codeID"));
				top.setValue(rs.getInt("value"));
				top.setTime(rs.getTime("time"));
				top.setDate(rs.getDate("datetopup"));
				list.add(top);
			}

			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return list;
	}

	public boolean topUpTimeMember(String id, String time) {
		System.out.println("*********************");
		System.out.println("topUpTimeMember");
		System.out.println("*********************");

		String sql = "";
		System.out.println("Member ID : " + id);
		System.out.println("Time : " + time);

		sql = "UPDATE member SET time = ? WHERE memberID=?";
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

	public boolean confirmCode(int id, int memberid,String date) {
		String sql = "";
		System.out.println("===============ConfirmCODE===============");
		System.out.println("Code ID : " + id);
		System.out.println("memberid : " + memberid);

		sql = "UPDATE codeTopup SET avilable = ?, datetopup = ? WHERE codeID=?";
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setString(2, date);
			pstm.setInt(3, id);
			pstm.executeUpdate();
			con.close();
			pstm.close();
			System.out.println("Update Sell Complete");

		} catch (Exception sqlException) {
			System.out.println("Error : " + sqlException);
			return false;
		}

		sql = "INSERT INTO topupdetail (memberID,codeID) VALUES(?,?)";
		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, memberid);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			con.close();
			pstm.close();
			System.out.println("Insert  TopupDetail Complete");
		} catch (Exception sqlException) {
			System.out.println(sqlException);
			return false;
		}

		System.out.println("===============END ConfirmCODE===============");
		return true;
	}

	public ArrayList<member> getmemberlist() {
		System.out.println("*********************");
		System.out.println("getHistoryTopup");
		System.out.println("*********************");
		String sql = "SELECT * from member";
		member top;
		ArrayList<member> list = new ArrayList<member>();
		System.out.println("SQL : " + sql);
		/*System.out.println("Memberid : " + i);*/

		try {
			con = connect.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				top = new member();

				top.setMemberID(rs.getInt("memberID"));
				top.setUsername(rs.getString("username"));
				top.setPassword(rs.getString("password"));
				top.setTime(rs.getTime("time"));
				top.setFristName(rs.getString("fristname"));
				top.setLastName(rs.getString("LastName"));
				list.add(top);
			}

			con.close();
			pstm.close();
			rs.close();

		} catch (Exception sqlException) {
			System.out.println(sqlException);
		}

		return list;
	}

}
