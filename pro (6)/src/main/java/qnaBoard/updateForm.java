package qnaBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import join1.Member;
import join1.MemberDAO;

import java.io.IOException;

@WebServlet("/qnaBoard/updateForm")
public class updateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String number = request.getParameter("number"); 
			QnaBoardDAO qnaBoardDAO = new QnaBoardDAO();
			QnaBoard qnaBoard = qnaBoardDAO.viewQnaBoard(number, false);
			System.out.println("qnaBoard = " + qnaBoard);
			request.setAttribute("qnaBoard", qnaBoard);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/qnaBoard/updateForm.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}