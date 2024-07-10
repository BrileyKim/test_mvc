package com.gn.board.dao;

import static com.gn.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Board;

public class BoardDao {
	
	public int createBoard(Board b, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0; 
		try {
			String sql = "INSERT INTO `board`(board_title,board_content,board_writer,ori_thumbnail,new_thumbnail) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoard_title());
			pstmt.setString(2, b.getBoard_content());
			pstmt.setString(3, b.getBoard_writer());
			pstmt.setString(4, b.getOri_thumbnail());
			pstmt.setString(5, b.getNew_thumbnail());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Board> selectBoardList(Board b ,Connection conn){
		List<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board";
		try {
			if(b.getBoard_title() != null) {
				sql = "SELECT * FROM board WHERE board_title LIKE CONCAT('%',?,'%')";
			} 
			sql += " LIMIT "+b.getLimit_pageNo()+", "+b.getNumPerPage();
			pstmt = conn.prepareStatement(sql);
			if(b.getBoard_title() != null) {
				pstmt.setString(1, b.getBoard_title());
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board resultVo = new Board(rs.getInt("board_no"),
						rs.getString("board_title"),
						rs.getString("board_content"),
						rs.getString("board_writer"),
						rs.getTimestamp("reg_date").toLocalDateTime(),
						rs.getTimestamp("mod_date").toLocalDateTime(),
						rs.getString("ori_thumbnail"),
						rs.getString("new_thumbnail"));
				list.add(resultVo);
			}

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectBoardCount(Board b, Connection conn) {
		int result = 0; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM board";
		try {
			if(b.getBoard_title() != null) {
				sql += "WHERE board_title LIKE CONCAT('%',?,'%')";
			}
			pstmt = conn.prepareStatement(sql);
			if(b.getBoard_title() != null) {
				pstmt.setString(1, b.getBoard_title());
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

}
