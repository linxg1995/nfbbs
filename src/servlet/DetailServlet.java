package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PostBean;
import bean.RepostBean;
import dao.PostDao;
import dao.RepostDao;
import dao.UserDao;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
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

		String pId = request.getParameter("pId");
		PostBean post = null;
		String postUhead = null;
		List<RepostBean> repostList = null;
		List<String> repostListUhead = null;

		if (pId != null) {
			post = PostDao.selectPost(pId);
			postUhead = UserDao.selectPostUhead(pId);
			repostList = RepostDao.selectRepost(pId);
			repostListUhead = UserDao.selectRepostUhead(pId);

			request.setAttribute("post", post);
			request.setAttribute("postHead", postUhead);
			request.setAttribute("repostList", repostList);
			request.setAttribute("repostListUhead", repostListUhead);
		}

		request.getRequestDispatcher("/postdetail.jsp").forward(request, response);
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
