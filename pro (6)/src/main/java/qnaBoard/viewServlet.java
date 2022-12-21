package qnaBoard;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/qnaBoard/*")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = request.getParameter("number");
	
		QnaBoardDAO qnaBoardDAO = new QnaBoardDAO();
		QnaBoard viewQnaBoard = qnaBoardDAO.viewQnaBoard(number, true);
		if( viewQnaBoard != null) {
			request.setAttribute("viewQnaBoard", viewQnaBoard);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/qnaBoard/viewForm.jsp");
		dispatcher.forward(request, response);
		}

	}
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
