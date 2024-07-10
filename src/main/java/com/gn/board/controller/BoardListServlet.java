package com.gn.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("board_title");
		String nowPage = request.getParameter("nowPage");
		Board b = new Board();
		b.setBoard_title(title);
		if(nowPage != null ) {
			b.setNowPage(Integer.parseInt(nowPage));
		}
		b.setTotalData(new BoardService().selectBoardCount(b));
		List<Board> list = new BoardService().selectBoardList(b);
		
		
		// 페이징 처리
//		String pageBar = "";
//		pageBar += "<div class='center'>";
//		pageBar += "<div class='pagination'>";
//		if(b.isPrev()) {
//			pageBar += "<a href='/board/list?nowPage="+(b.getPageBarStart()-1)+"'>&laquo;</a>";
//		}
//		for(int i = b.getPageBarStart() ; i <= b.getPageBarEnd() ; i++) {
//			pageBar += "<a href='/board/list?nowPage="+i+"'"+(b.getNowPage()==i ? "class='active'":"")+">"+i+"</a>";
//		}
//		if(b.isNext()) {
//			pageBar += "<a href='/board/list?nowPage="+(b.getPageBarEnd()+1)+"'>&raquo;</a>";
//		}
//		pageBar += "</div></div>";
		  	
		//request.setAttribute("pageBar", pageBar);
		request.setAttribute("paging",b);
		request.setAttribute("resultList", list);
		RequestDispatcher rd=request.getRequestDispatcher("/views/board/list.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
