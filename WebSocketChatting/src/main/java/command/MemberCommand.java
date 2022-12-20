package command;


import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;

import board.BoardDAO;
import board.BoardDTO;
import control.Paging;
import control.PagingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberBean;
import member.MemberDAO;

public class MemberCommand{
	MemberDAO memberDAO = new MemberDAO();
	
	
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String user_id = request.getParameter("userid");
		String user_pwd = request.getParameter("password");

		MemberBean memberbean = new MemberBean();
		memberbean.setId(user_id);
		memberbean.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberbean);
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", "loginned");
			session.setAttribute("id", user_id);
			session.setAttribute("login.pwd", user_pwd);
		}
		
		return "/index.jsp";
	}
	
	public String join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberBean memberBean = new MemberBean(
				request.getParameter("userid"), 
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("sex"),
				request.getParameter("email"),
				request.getParameter("address"), 
				request.getParameter("phone"),null,null
				);
		memberDAO.insertMember(memberBean);
		memberDAO.close();
		return "/index.jsp";
	}
	
	
	public String logout(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("isLogon", null);
		session.setAttribute("id", null);
		session.setAttribute("login.pwd", null);
		return "/index.jsp";
	}
	
	
	public String memberDrop(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String userid=(String) session.getAttribute("id");
		if(memberDAO.dropMember(userid)==1) {
			session.setAttribute("isLogon", null);
			session.setAttribute("id", null);
			session.setAttribute("login.pwd", null);
			System.out.println("Drop"+userid);
			try {
				memberDAO.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("fail Drop"+userid);
		
		return "/index.jsp";
	}
	
	
	public String findInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		// TODO Auto-generated method stub
		MemberBean memberBean = new MemberBean();
		boolean code;
		if(request.getParameterMap().containsKey("name")) {
			String key =request.getParameter("name");
			code = true;
			memberBean = memberDAO.findMember(key,code);
			request.setAttribute("id",memberBean.getId());
		}
		else if(request.getParameterMap().containsKey("id")){
			String key =request.getParameter("id");
			code = false;
			memberBean = memberDAO.findMember(key,code);
			request.setAttribute("pwd",memberBean.getPwd());
		}
		
		return "/jsp/member/findinfo_result.jsp";
	}
	
	
	public String infoView(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		HttpSession session = request.getSession();
		MemberBean memberBean = new MemberBean();
		
		String userid=(String) session.getAttribute("id");
		memberBean = memberDAO.getMember(userid);
		request.setAttribute("memberBean",memberBean);
		
		return "/jsp/member/memberInfo.jsp";
	}
	
	public String setUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		MemberBean member = new MemberBean();
		String userid=(String) session.getAttribute("id");
		member = memberDAO.getMember(userid);
		request.setAttribute("member", member);
		
		return "/jsp/member/setMemberUpdate.jsp";
	}
	
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		MemberBean memberBean = new MemberBean(
				request.getParameter("id"), 
				null,
				request.getParameter("name"),
				null,
				request.getParameter("email"),
				request.getParameter("address"), 
				request.getParameter("phone"),null,null
				);
		memberDAO.memberUpdate(memberBean);
		memberDAO.close();
		return "mypage.zan";
	}
	
	public String memberListAccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/jsp/admin/adminMain.jsp";
	}
	
	public JSONObject memberList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String text = request.getParameter("text");
		String pageNoStr = request.getParameter("pageNo");
		String listSizeStr = request.getParameter("listSize");
		
		BoardDAO boardDAO = new BoardDAO();
		if ("".equals(pageNoStr) || null == pageNoStr) 
			pageNoStr = "1";
		
		int pageNo = Integer.parseInt(pageNoStr);		
		int pageSize = 10;
		int listSize = Integer.parseInt(listSizeStr);
		Paging page = new Paging(pageNo,pageSize,listSize,boardDAO.totalPageNo(text,listSize));
		List<MemberBean> list = memberDAO.listMembers(text, pageNo, listSize);
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("status", true);
		jsonResult.put("membersList", list);
		jsonResult.put("pageHtml",PagingService.retPageService(page));
		jsonResult.put("url", "/WebSocketChatting/jsp/admin/adminMain.jsp");
		return jsonResult;
		
		
	}
}
