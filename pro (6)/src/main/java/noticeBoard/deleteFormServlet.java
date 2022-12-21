package noticeBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import join1.MemberDAO;

import java.io.IOException;

@WebServlet("/noticeBoard/deleteForm")
public class deleteFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String number = request.getParameter("number"); 
			String pwd = request.getParameter("pwd"); 
			NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
			MemberDAO memberDAO = new MemberDAO();
			NoticeBoard noticeBoard = noticeBoardDAO.viewNoticeBoard(number, false);
			System.out.println("NoticeBoard = " + noticeBoard);
			request.setAttribute("NoticeBoard", noticeBoard);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/noticeBoard/deleteForm.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
