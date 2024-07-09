package com.gn.board.service;

import static com.gn.common.JDBCTemplate.close;
import static com.gn.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Board;

public class BoardService {

	public int createBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().createBoard(b,conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardList(Board b){
		Connection conn = getConnection();
		List<Board> list= new BoardDao().selectBoardList(b,conn);
		close(conn);
		return list;
	}
	
	public int selectBoardCount(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().selectBoardCount(b,conn);
		close(conn);
		return result;
	}
}
