package qnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import join1.Member;
import join1.MemberDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.json.JSONObject;

@WebServlet("/qnaBoard/delete")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String jsonStr = in.readLine();
		System.out.println("jsonStr = " + jsonStr);
		
		JSONObject jsonMember = new JSONObject(jsonStr);
		String number = jsonMember.getString("number"); 
		String pwd = jsonMember.getString("pwd"); 
		
		QnaBoardDAO qnaBoardDAO = new QnaBoardDAO();
		QnaBoard qnaBoard = new QnaBoard();
		
		MemberDAO memberDAO = new MemberDAO();
		Member member = new Member();
		
		JSONObject jsonResult = new JSONObject();

		qnaBoard.setNumber(Integer.parseInt(number));
		member.setPwd(pwd);
		try {
			int count = qnaBoardDAO.updateQnaBoard(qnaBoard);			
			
			if (count == 1) {
				jsonResult.put("status", true);
				jsonResult.put("message", "Qna가 삭제되었습니다");
				
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "Qna 삭제 오류");
			}
	    	
			qnaBoardDAO.deleteQnaBoard(qnaBoard);
			jsonResult.put("status", true);
			jsonResult.put("message", "Qna가 삭제됐습니다.");
			jsonResult.put("url", "/pro/jsp/qnaBoard/qnaList.jsp");
			
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