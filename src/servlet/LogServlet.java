package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dao.UserDao;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(true);
		String log = request.getParameter("log");

		/*
		 * 登录验证 ******************************
		 */
		if ("in".equals(log)) {
			String uId = request.getParameter("uId");
			String uPassword = request.getParameter("uPassword");
			UserBean user = UserDao.checkUser(uId, uPassword);

			if (user != null) {
				// 保存登录状态和用户信息到session中
				session.setAttribute("logStatus", "1");
				session.setAttribute("user", user);
				response.sendRedirect("/nfbbs/HomeServlet");
			} else {
				response.sendRedirect("/nfbbs/index.jsp?log=error");
			}
		}

		/*
		 * 登出 ******************************
		 */
		if ("out".equals(log)) {
			// 清除session中的登录状态
			if (session.getAttribute("logStatus") != null) {
				session.removeAttribute("logStatus");
			}
			response.sendRedirect("/nfbbs/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
