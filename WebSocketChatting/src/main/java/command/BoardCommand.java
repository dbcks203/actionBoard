package command;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import board.BoardDAO;
import board.BoardDTO;
import board.BoardFileDAO;
import board.BoardFileDTO;
import control.Paging;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardCommand{
	BoardDAO boardDAO = new BoardDAO();
	BoardFileDAO boardFileDAO = new BoardFileDAO();
	
	public String articleDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stu
		
		String target = request.getParameter("seq");
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.articleDelete(target);
		try {
			boardDAO.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "articlelist.zan";
	}
	
	
	public String articleEdit(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDTO boardDTO = new BoardDTO();
		
		String seq = (String) request.getParameter("seq");
		String subject = (String) request.getParameter("subject");
		String content = (String) request.getParameter("content");
		
		boardDTO.setSeq(seq);
		boardDTO.setContent(content);
		boardDTO.setSubject(subject);
		boardDAO.articleEdit(boardDTO);
		
		try {
			boardDAO.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "articleview.zan?seq="+seq;
	}
	
	
	
	public String articleList(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		String text = request.getParameter("text");
		String pageNoStr = request.getParameter("pageNo");
		BoardDAO boardDAO = new BoardDAO();
		if ("".equals(pageNoStr) || null == pageNoStr) 
			pageNoStr = "1";
		int pageNo = Integer.parseInt(pageNoStr);
		/*
		int pageSize = 10;
		int totalPageNo = boardDAO.totalPageNo(text);
		int startPageNo = ((pageNo - 1) / pageSize) * pageSize + 1;
		int endPageNo = startPageNo + pageSize - 1;
		
		
		if (endPageNo > totalPageNo) 
			endPageNo = totalPageNo;
		*/
		Paging pc = new Paging(pageNo,boardDAO.totalPageNo(text)); 
		List<BoardDTO> list = boardDAO.listArticles(text, pageNo);
		request.setAttribute("articleList", list);
		/*
		request.setAttribute("totalPageNo", totalPageNo);
		request.setAttribute("startPageNo", startPageNo);
		request.setAttribute("endPageNo", endPageNo) ;
		request.setAttribute("currentPageNo", pageNo) ;
		*/
		request.setAttribute("Paging", pc);
		
		return "/jsp/board/boardLobby.jsp";
	}
	
	
	
	
	
	
	public String articleSetEdit(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {	
		String target = request.getParameter("seq");
		request.setAttribute("boardDTO", boardDAO.getBoardDTO(target));
		
		return "/jsp/board/articleSetEdit.jsp";
	}
	
	public String articleView(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		String target = request.getParameter("seq");
		boardDAO.updateReadCount(target);
		request.setAttribute("boardDTO", boardDAO.getBoardDTO(target));
		
		return "/jsp/board/articleView.jsp";
	}
	
	public JSONObject articleWrite(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		BoardDTO boardDTO = new BoardDTO();
		HttpSession session = request.getSession();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("c:\\upload"));
		ServletFileUpload upload = new ServletFileUpload(factory);

		Map<String, List<FileItem>> mapItems = upload.parseParameterMap(request);
		String subject = mapItems.get("subject").get(0).getString();
		String content = mapItems.get("content").get(0).getString();
		String tag = mapItems.get("tag").get(0).getString();
		String userid = (String) session.getAttribute("id");
		JSONObject jsonResult = new JSONObject();

		try {
			boardDTO.setUserid(userid);
			boardDTO.setContent(content);
			boardDTO.setSubject(subject);
			boardDTO.setTag(tag);
			int number = boardDAO.articleInsert(boardDTO);
			for (FileItem fileItem : mapItems.get("filename1")) {
				if (fileItem.getSize() == 0) continue;
				String real_name = "c:\\upload\\" + System.nanoTime();
				fileItem.write(new File(real_name));
				BoardFileDTO boardFile = new BoardFileDTO(0, number, fileItem.getName(), real_name,
						fileItem.getContentType(), fileItem.getSize());
				boardFileDAO.insertBoardFile(boardFile);

			}
			boardFileDAO.close();
			boardDAO.close();	

			jsonResult.put("status", true);
			jsonResult.put("message", "글쓰기를 성공했습니다.");
			jsonResult.put("url", "/WebSocketChatting/jsp/board/boardLobby.jsp");
			return jsonResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String replyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String number = request.getParameter("number");
	
		BoardDAO boardDAO = new BoardDAO();
		request.setAttribute("boardDTO", boardDAO.getBoardDTO(number));
		
		return "/jsp/board/replyForm.jsp";
	}
	
	
	public JSONObject reply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		HttpSession session = request.getSession();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("c:\\upload"));
		ServletFileUpload upload = new ServletFileUpload(factory);

		Map<String, List<FileItem>> mapItems = upload.parseParameterMap(request);
		String parentNo = mapItems.get("parentNo").get(0).getString();
		String subject = mapItems.get("subject").get(0).getString();
		String content = mapItems.get("content").get(0).getString();
		String tag = mapItems.get("tag").get(0).getString();
		String userid = (String) session.getAttribute("id");
		JSONObject jsonResult = new JSONObject();
		
		try {
			
			boardDTO.setTag(tag);
			System.out.println("pno:"+parentNo);
			System.out.println("sub"+subject);
			System.out.println("cot:"+content);
			System.out.println("uid:"+userid);
			int number = boardDAO.articleInsert(parentNo,subject,content,userid);
			for (FileItem fileItem : mapItems.get("filename1")) {
				if (fileItem.getSize() == 0) continue;
				String real_name = "c:\\upload\\" + System.nanoTime();
				fileItem.write(new File(real_name));
				BoardFileDTO boardFile = new BoardFileDTO(0, number, fileItem.getName(), real_name,
						fileItem.getContentType(), fileItem.getSize());
				boardFileDAO.insertBoardFile(boardFile);

			}
			System.out.println(number);
				boardFileDAO.close();
				boardDAO.close();
				
				jsonResult.put("status", true);
				jsonResult.put("message", "답변글쓰기를 성공했습니다.");
				jsonResult.put("url", "articleview.zan?seq=" + number);
				return jsonResult;
			} catch (SQLException e) {
				jsonResult.put("status", false);
				jsonResult.put("message", "작성 중에 오류가 발생했습니다.");
				e.printStackTrace();
			}
		
		return null;
	}
	
	public String writeForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/jsp/board/writeForm.jsp";
	}
}
