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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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

		List<PostBean> syzgHomePostList = PostDao.selectHomePost("术业专攻");
		List<PostBean> sdrcHomePostList = PostDao.selectHomePost("沙雕日常");
		List<PostBean> xytcHomePostList = PostDao.selectHomePost("校园吐槽");
		List<PostBean> lansHomePostList = PostDao.selectHomePost("恋爱那事");

		request.setAttribute("syzgHomePostList", syzgHomePostList);
		request.setAttribute("sdrcHomePostList", sdrcHomePostList);
		request.setAttribute("xytcHomePostList", xytcHomePostList);
		request.setAttribute("lansHomePostList", lansHomePostList);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
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
