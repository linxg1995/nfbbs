package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PostBean;
import bean.RepostBean;
import bean.UserBean;
import dao.PostDao;
import dao.RepostDao;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoServlet() {
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

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String uName = null;
		List<PostBean> postList = null;
		List<RepostBean> repostList = null;
		List<String> repostListTitle = null;

		if (user != null) {
			uName = user.getuName();
			postList = PostDao.selectUnamePost(uName);
			repostList = RepostDao.selectUnameRepost(uName);
			repostListTitle = RepostDao.selectUnameRepostTitle(uName);

			request.setAttribute("postList", postList);
			request.setAttribute("repostList", repostList);
			request.setAttribute("repostListTitle", repostListTitle);
		}

		request.getRequestDispatcher("/usercenter.jsp").forward(request, response);
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
