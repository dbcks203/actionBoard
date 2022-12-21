package noticeBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import join1.Member;
import join1.MemberDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

@WebServlet("/noticeBoard/write")
public class writeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("write in");
			HttpSession session = request.getSession();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);
			
			JSONObject jsonMember = new JSONObject(jsonStr);
			String title = jsonMember.getString("title"); 
			String content = jsonMember.getString("content");
			Member member = (Member)session.getAttribute("session_member");
			NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
			JSONObject jsonResult = new JSONObject();

			try {
				noticeBoardDAO.addNoticeBoard(title, content, member.getUid());
				jsonResult.put("status", true);
				jsonResult.put("message", "공지글을 등록했습니다.");
				jsonResult.put("url", "/pro/jsp/noticeBoard/noticeList.jsp");
				
			} catch (SQLException e) {
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
