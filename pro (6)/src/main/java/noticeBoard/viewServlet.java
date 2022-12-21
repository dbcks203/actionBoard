package noticeBoard;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/noticeBoard/*")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = request.getParameter("number");
	
		NoticeBoardDAO noticeBoardDAO = new NoticeBoardDAO();
		NoticeBoard viewNoticeBoard = noticeBoardDAO.viewNoticeBoard(number, true);
		if( viewNoticeBoard != null) {
			request.setAttribute("viewNoticeBoard", viewNoticeBoard);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/noticeBoard/viewForm.jsp");
		dispatcher.forward(request, response);
		}

	}
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
