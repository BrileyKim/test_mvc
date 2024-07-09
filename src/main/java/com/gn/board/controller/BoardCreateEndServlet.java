package com.gn.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/book/createEnd")
public class BoardCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardCreateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. multipart/form-data 방식으로 파일 왔는지 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			response.sendRedirect("/board/create");
		} else {
			// 2. cos.jar에서 제공하는 클래스 MultipartRequest 사용하여
			// 지정된 위치에 파일 정보 저장
			// 2-1. 파일 저장위치 설정
			String dir =request.getSession().getServletContext().getRealPath("upload");
		
			// 2-2. 저장 파일 최대 크기 설정(byte -> MB -> GB -> TB : 1024)
			int maxSize = 1024 * 1024 * 10; // 10MB
			// 2-3. 인코딩 설정
			String encoding = "UTF-8";
			// 2-4. Rename 클래스
			DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			// 2-5. 매개변수 있는 생성자로 MultipartRequest 클래스 생성
			MultipartRequest mr = new MultipartRequest(request,dir,maxSize,encoding,dfr);
			
			
			
			// 3. 파일명 저장
			String oriName = mr.getOriginalFileName("thumbnail");
			String reName = mr.getFilesystemName("thumbnail");
			String title = mr.getParameter("board_title");
			String content = mr.getParameter("board_content");
			String writer = mr.getParameter("board_writer");
			
			Board b = new Board();
			b.setBoard_title(title);
			b.setBoard_content(content);
			b.setBoard_writer(writer);
			b.setOri_thumbnail(oriName);
			b.setNew_thumbnail(reName);
			
			int result = new BoardService().createBoard(b);
			RequestDispatcher view = request.getRequestDispatcher("/views/board/create_fail.jsp");
			if(result > 0) {
				view = request.getRequestDispatcher("/views/board/create_success.jsp");
			} 
			view.forward(request, response);
			
		}
		
		
//		Part part = request.getPart("thumbnail");
//		String fileName = getFilename(part);
//		if (!fileName.isEmpty()) {
//            part.write("/upload/board/"+fileName);
//        }
		
//		File dir = new File("/upload");
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setRepository(dir);
//		factory.setSizeThreshold(1024*1024);
//		ServletFileUpload uplaod = new ServletFileUpload(factory);
//		FileItem fileItem = 
		
//		 String itemName = request.getParameter("thumbnail");
//
//	        Collection<Part> parts = request.getParts();
//
//	        for (Part part : parts) {
//	            Collection<String> headerNames = part.getHeaderNames();
//
//	            //데이터 읽기
//	            InputStream inputStream = part.getInputStream();
//	            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//	            log.info("body={}", body);
//
//	            //파일 저장하기
//	            if (StringUtils.hasText(part.getSubmittedFileName())) {
//	                String fullPath = fileDir + part.getSubmittedFileName();
//	                log.info("파일 저장 fullPath={}", fullPath);
//	                part.write(fullPath);
//	            }
//	        }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
//	private String getFilename(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] split = contentDisp.split(";");
//        for (int i = 0; i < split.length; i++) {
//            String temp = split[i];
//            if (temp.trim().startsWith("filename")) {
//                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
//            }
//        }
//        return "";
//    }

}
