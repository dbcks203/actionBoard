package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class PathFinder extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String contextPath;
	
	Map<String, Object> className2ObjectMap = new HashMap<>();
	// 객체 맵
	Map<String, Object> objectMap = new HashMap<>();
	// 메서드 맵
	Map<String, Method> methodMap = new HashMap<>();

	public PathFinder() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		contextPath = config.getServletContext().getContextPath();
		System.out.println(contextPath);
		String actionNames = config.getInitParameter("actionNames");
		actionNames = actionNames.trim();
		
		try {
			for (String line : actionNames.split("\n")) {
				line = line.trim();

				String[] actionInfo = line.split(":");
				// 클래스를 로딩한다
				Class<?> cls = Class.forName(actionInfo[1]);
				// 클래스명이 존재하는지 확인한다
				if (!className2ObjectMap.containsKey(actionInfo[1])) {
					// 클래스를 이용하여 객체를 생성한다
					Object object = cls.getDeclaredConstructor().newInstance();

					// 생성된 객체를 클래스 명으로 해서 맵에 추가함
					className2ObjectMap.put(actionInfo[1], object);

					// 생성된 객체를 URL 명으로 해서 맵에 추가함
					objectMap.put(actionInfo[0], object);
				} else {

					// 클래스명으로 해서 생성된 객체를 URL 명으로 해서 맵에 추가함
					objectMap.put(actionInfo[0], className2ObjectMap.get(actionInfo[1]));
				}
				Method method = cls.getMethod(actionInfo[2], HttpServletRequest.class, HttpServletResponse.class);
				methodMap.put(actionInfo[0], method);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			actionDo(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			actionDo(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getRequestURI();
		String []tokens = path.split("/");
		path = tokens[tokens.length-1];
		System.out.println(path);
		Object obj = objectMap.get(path);
		System.out.println("obj:"+obj);
		Method method = methodMap.get(path);
		System.out.println("path:"+path);
		if (obj != null && method != null) {
			// action.execute(request, response);
			try {
				Object ret = method.invoke(obj, request, response);
				if (ret.getClass().equals(String.class)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher((String) ret);
					dispatcher.forward(request, response);
				} else if (ret.getClass().equals(JSONObject.class)) {
					JSONObject jsonResult = (JSONObject) ret;
					PrintWriter out = response.getWriter();
					out.println(jsonResult == null ? "{status:false}" : jsonResult.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
