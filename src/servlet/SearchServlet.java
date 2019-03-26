package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PostBean;
import dao.PostDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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

		request.setCharacterEncoding("utf-8");

		String post = request.getParameter("post");
		List<PostBean> postList = null;

		/*
		 * 帖子搜索 ******************************
		 */
		if (post != null) {
			switch (post) {
			case "all":
				postList = PostDao.selectAllPost();

				break;
			case "syzg":
				postList = PostDao.selectTypePost("术业专攻");

				break;
			case "sdrc":
				postList = PostDao.selectTypePost("沙雕日常");

				break;
			case "xytc":
				postList = PostDao.selectTypePost("校园吐槽");

				break;
			case "lans":
				postList = PostDao.selectTypePost("恋爱那事");

				break;
			default:
				postList = PostDao.selectKeywordPost(post);
				break;
			}

			request.setAttribute("postList", postList);
		}

		request.getRequestDispatcher("/postlist.jsp").forward(request, response);
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
