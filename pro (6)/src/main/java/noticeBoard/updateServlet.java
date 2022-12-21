package noticeBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import join1.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;


@WebServlet("/noticeBoard/update")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		
		JSONObject jsonMember = new JSONObject(jsonStr);
		String number = jsonMember.getString("number"); 
		String title = jsonMember.getString("title"); 
		String content = jsonMember.getString("content");
		
		NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
		NoticeBoard noticeBoard = new NoticeBoard();
		JSONObject jsonResult = new JSONObject();

		noticeBoard.setNumber(Integer.parseInt(number));
		noticeBoard.setTitle(title);
		noticeBoard.setContent(content);
		
		try {
			int count = noticeBoardDAO.updateNoticeBoard(noticeBoard);			
			
			if (count == 1) {
				jsonResult.put("status", true);
				jsonResult.put("message", "공지글이 수정되었습니다");
				
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "공지글 수정 실패");
			}
	    	
			noticeBoardDAO.updateNoticeBoard(noticeBoard);
			jsonResult.put("status", true);
			jsonResult.put("message", "수정 성공");
			jsonResult.put("url", "/jsp/noticeBoard/boardList.jsp");
			
		} catch (Exception e) {
			jsonResult.put("status", false);
			jsonResult.put("message", "작성 중에 오류가 발생했습니다.");
			e.printStackTrace();
		}
				
		PrintWriter out = response.getWriter();
		out.println(jsonResult.toString());

}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
