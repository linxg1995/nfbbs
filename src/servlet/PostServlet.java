package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dao.PostDao;
import dao.RepostDao;
import dao.UserDao;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostServlet() {
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
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String post = request.getParameter("post");

		SimpleDateFormat sdf = null;
		boolean insertPost;
		boolean updateUpost;
		boolean inserRepost;
		boolean updateUrepost;
		boolean updatePrepost;

		if ("post".equals(post)) {
			String pTitle = request.getParameter("pTitle");
			String pType = request.getParameter("pType");
			String pContent = request.getParameter("pContent");

			String pUname = user.getuName();

			sdf = new SimpleDateFormat("yyyy-MM-dd");
			String pTime = sdf.format(new Date());

			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String pRptime = sdf.format(new Date());

			insertPost = PostDao.insertPost(pTitle, pType, pContent, pUname, pTime, pRptime);

			if (insertPost) {
				updateUpost = UserDao.updatePost(pUname);

				if (updateUpost) {
					System.out.println("发帖、更新发帖数 成功");

					String uId = Integer.toString(user.getuId());
					String uPassword = user.getuPassword();

					user = UserDao.checkUser(uId, uPassword);
					session.setAttribute("user", user);
				} else {
					System.out.println("更新发帖数 失败");
				}
			} else {
				System.out.println("发帖失败");
			}

			response.sendRedirect("/nfbbs/InfoServlet");
		} else if ("repost".equals(post)) {
			String rpPid = request.getParameter("rpPid");
			String rpContent = request.getParameter("rpContent");
			String rpUname = user.getuName();

			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String rpTime = sdf.format(new Date());

			inserRepost = RepostDao.insertRepost(rpPid, rpContent, rpUname, rpTime);

			if (inserRepost) {
				updateUrepost = UserDao.updateRepost(rpUname);

				if (updateUrepost) {
					String uId = Integer.toString(user.getuId());
					String uPassword = user.getuPassword();

					user = UserDao.checkUser(uId, uPassword);
					session.setAttribute("user", user);

					updatePrepost = PostDao.updateRepost(rpPid, rpTime);

					if (updatePrepost) {
						System.out.println("回帖、更新用户回帖数、更新帖子回帖数 成功");
					} else {
						System.out.println("更新帖子回帖数 失败");
					}
				} else {
					System.out.println("更新回帖数 失败");
				}
			} else {
				System.out.println("回帖失败");
			}

			PrintWriter pw = response.getWriter();
			String html = "<div class='media'>" + "<div class='pull-left'>"
					+ "<img class='media-object' src='img/icon/head.png' alt='头像'>" + "</div>"
					+ "<div class='media-body'>" + "<h4 class='media-heading'>" + user.getuName() + "</h4>"
					+ "<h4><small>" + rpTime + "</small></h4>" + "<p>" + rpContent + "</p>" + "<hr>" + "</div>"
					+ "</div>";
			pw.println(html);
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
