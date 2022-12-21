package member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.dao.MemberDAO;
import member.entity.Member;

public class MemberAction {

	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/member/loginForm.jsp";
	}
	
	public JSONObject login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);

			JSONObject jsonMember = new JSONObject(jsonStr);

			HttpSession session = request.getSession();

			String uid = jsonMember.getString("uid");
			String pwd = jsonMember.getString("pwd");
			MemberDAO memberDAO = new MemberDAO();
			Member member = memberDAO.viewMember(uid);
			JSONObject jsonResult = new JSONObject();

			if (member != null && member.getPwd().equals(pwd)) {
				session.setAttribute("session_member", member);
				jsonResult.put("status", true);
				jsonResult.put("message", "로그인되었습니다");
				jsonResult.put("url", request.getServletContext().getContextPath() + "/main.do");
				session.setAttribute("session_member", member);
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "해당아이디는 현재 DB에 존재하지 않습니다");
			}

			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public JSONObject logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		HttpSession session = request.getSession();		
		session.invalidate();

		JSONObject jsonResult = new JSONObject();

		jsonResult.put("status", true);
		jsonResult.put("message", "로그아웃되었습니다");
		jsonResult.put("url", request.getServletContext().getContextPath() + "/main.do");
		
		return jsonResult;
	}

	public JSONObject findUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);

			JSONObject jsonMember = new JSONObject(jsonStr);

			MemberDAO memberDAO = new MemberDAO();
			String name = jsonMember.getString("name2");
			String phone = jsonMember.getString("phone");

			Member member = memberDAO.findUidMemberByPhone(name, phone);
			JSONObject jsonResult = new JSONObject();

			if (member != null) {
				jsonResult.put("status", true);
				jsonResult.put("message", "아이디 = " + member.getUid());

			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "해당아이디는 현재 DB에 존재하지 않습니다");
			}

			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public JSONObject findPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);

			JSONObject jsonMember = new JSONObject(jsonStr);

			MemberDAO memberDAO = new MemberDAO();
			String uid = jsonMember.getString("uid");
			String name = jsonMember.getString("name2");
			String phone = jsonMember.getString("phone");

			Member member = memberDAO.findPwdMemberByPhone(uid, name, phone);
			JSONObject jsonResult = new JSONObject();

			if (member != null) {
				jsonResult.put("status", true);
				jsonResult.put("message", "비밀번호 = " + member.getPwd());

			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "해당 비밀번호는 현재 DB에 존재하지 않습니다");
			}
			
			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String viewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/viewForm.jsp";
	}
	
	public String updateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/updateForm.jsp";
	}

	public JSONObject update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);

			JSONObject jsonMember = new JSONObject(jsonStr);

			HttpSession session = request.getSession();

			MemberDAO memberDAO = new MemberDAO();
			String uid = jsonMember.getString("uid");
			String pwd = jsonMember.getString("pwd");
			String name = jsonMember.getString("name2");
			String sex = jsonMember.getString("sex");
			String address = jsonMember.getString("address");
			String phone = jsonMember.getString("phone");
			String email = jsonMember.getString("email");
			Member member = new Member(uid, pwd, name, sex, address, phone, email, "", null);
			int count = memberDAO.updateMember(member);
			JSONObject jsonResult = new JSONObject();

			if (count == 1) {
				jsonResult.put("status", true);
				jsonResult.put("message", "수정되었습니다");
				session.setAttribute("session_member", member);
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "해당아이디는 현재 DB에 존재하지 않습니다");
			}

			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String registerForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/registerForm.jsp";
	}

	public JSONObject register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);

			JSONObject jsonMember = new JSONObject(jsonStr);

			HttpSession session = request.getSession();

			MemberDAO memberDAO = new MemberDAO();
			String uid = jsonMember.getString("uid");
			String pwd = jsonMember.getString("pwd");
			String name = jsonMember.getString("name2");
			String sex = jsonMember.getString("sex");
			String address = jsonMember.getString("address");
			String phone = jsonMember.getString("phone");
			String email = jsonMember.getString("email");
			Member member = new Member(uid, pwd, name, sex, address, phone, email, "", null);
			int count = memberDAO.updateMember(member);
			JSONObject jsonResult = new JSONObject();

			if (count == 1) {
				jsonResult.put("status", true);
				jsonResult.put("message", "수정되었습니다");
				session.setAttribute("session_member", member);
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "해당아이디는 현재 DB에 존재하지 않습니다");
			}

			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public JSONObject delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String jsonStr = in.readLine();
			System.out.println("jsonStr = " + jsonStr);

			JSONObject jsonMember = new JSONObject(jsonStr);
			String pwd = jsonMember.getString("pwd");

			HttpSession session = request.getSession();
			Member member = (Member) session.getAttribute("session_member");
			JSONObject jsonResult = new JSONObject();
			System.out.println(member);
			if (member.getPwd().equals("pwd")) {
				MemberDAO memberDAO = new MemberDAO();

				int count = memberDAO.deleteMember(member);
				if (count == 1) {
					jsonResult.put("status", true);
					jsonResult.put("message", "회원탈퇴가 정상적으로 수행했습니다.");
					session.setAttribute("session_member", member);
				} else {
					jsonResult.put("status", false);
					jsonResult.put("message", "회원삭제가 실패되었습니다.");
				}
			} else {
				jsonResult.put("status", false);
				jsonResult.put("message", "비밀번호가 틀렸습니다.");
			}

			return jsonResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsp/member/deleteForm.jsp";
	}
	
}
