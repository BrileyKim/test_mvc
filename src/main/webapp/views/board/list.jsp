<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href='../../resources/css/board/list.css' rel="stylesheet" type="text/css">
<style>
@charset "UTF-8";
.center {
  text-align: center;
}
.pagination {
  display: inline-block;
}
.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  margin: 0 4px;
}
.pagination a.active {
  background-color: #A5A5A5;
  color: white;
  border: 1px solid #A5A5A5;
}
.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<%@ include file="../include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="book_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일자</th>
						</tr>
					</thead>
					<tbody>
						<%@page import="com.gn.board.vo.Board, java.util.*,java.time.LocalDateTime
								,java.time.format.DateTimeFormatter" %>
						<%
							List<Board> list = (List<Board>)request.getAttribute("resultList");
							// String pageBar = (String)request.getAttribute("pageBar");
							Board paging = (Board)request.getAttribute("paging");
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
							for(int i = 0 ; i < list.size(); i++){ %>
								<tr>
									<td><%=((paging.getNowPage()-1)*10)+i+1%></td>
									<td><%=list.get(i).getBoard_title()%></td>
									<td><%=list.get(i).getBoard_writer()%></td>
									<td><%=dtf.format(list.get(i).getReg_date())%></td>
								</tr>
						<%}%>
					</tbody>
				</table>
			</div>
		</div>
	</section>	
	<%-- <%=pageBar %> --%>
	<%if(paging != null){ %>
		<div class='center'>
			<div class='pagination'>
				<% if(paging.isPrev()){ %>
					<a href='/board/list?nowPage=<%=(paging.getPageBarStart()-1)%>'>&laquo;</a>
				<% } %>
				<% for(int i = paging.getPageBarStart() ; i<= paging.getPageBarEnd() ; i++){ %>
					<a href='/board/list?nowPage=<%=i%>' <%=paging.getNowPage() == i ? "class='active'" : "" %>><%=i%></a>
				<% }%>
				<% if(paging.isNext()) {%>
					<a href='/board/list?nowPage=<%=(paging.getPageBarEnd()+1)%>'>&raquo;</a>
				<% }%>
			</div>
		</div>
	<%} %>
</body>
</html>