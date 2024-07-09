package com.gn.user.dao;

import static com.gn.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gn.user.vo.User;

public class UserDao {
	public int createUser(User u, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0; 
		try {
			String sql = "INSERT INTO `user`(user_id,user_pw,user_name) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getUser_pw());
			pstmt.setString(3, u.getUser_name());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public User loginUser(String id, String pw, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			String sql = "SELECT user_id, user_pw, user_name FROM `user` WHERE user_id = ? AND user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				u = new User(rs.getString(1),rs.getString(2),rs.getNString(3));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return u;
	}
}
