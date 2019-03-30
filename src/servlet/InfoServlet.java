package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.PostBean;
import bean.RepostBean;
import bean.UserBean;
import dao.PostDao;
import dao.RepostDao;
import dao.UserDao;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/InfoServlet")
@MultipartConfig
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

		request.setCharacterEncoding("utf-8");

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
			repostListTitle = PostDao.selectRepostPtitle(uName);

			request.setAttribute("postList", postList);
			request.setAttribute("repostList", repostList);
			request.setAttribute("repostListTitle", repostListTitle);
		}

		/*
		 * 修改个人信息或头像 ******************************
		 */
		String info = request.getParameter("info");
		if ("basic".equals(info)) {
			String uNewPassword = request.getParameter("uPassword");
			String uNewName = request.getParameter("uName");
			boolean updateBasic = UserDao.updateBasic(user.getuId(), uNewPassword, uNewName);

			if (updateBasic) {
				System.out.println("基本信息 更新成功");

				user = UserDao.checkUser(Integer.toString(user.getuId()), uNewPassword);
				session.setAttribute("user", user);
			} else {
				System.out.println("基本信息 更新失败");
			}

			response.sendRedirect("/nfbbs/InfoServlet");
			return;
		} else if ("head".equals(info)) {
			/*
			 * 处理文件上传 ******************************
			 */
			// 存储路径
			String savePath = request.getServletContext().getRealPath("/img/head");
			// 获取表单中的Part对象
			Part part = request.getPart("uHead");
			// 获取请求头，从请求头中获取文件名的后缀名，Tomcat8.0 可以直接用 getSubmittedFileName()
			String partHeader = part.getHeader("Content-Disposition");
			String suffix = partHeader.substring(partHeader.lastIndexOf(".")).replace("\"", "");
			// UUID包的randomUUID()生成随机ID
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			// 生成文件名，用于写入本地和数据库
			String fileName = uuid + suffix;
			// 写入本地
			part.write(savePath + File.separator + fileName);
			System.out.println("文件上传 " + savePath + File.separator + fileName);

			boolean updateHead = UserDao.updateHead(user.getuId(), fileName);

			if (updateHead) {
				System.out.println("头像 更新成功");

				user = UserDao.checkUser(Integer.toString(user.getuId()), user.getuPassword());
				session.setAttribute("user", user);
			} else {
				System.out.println("头像 更新失败");
			}

			response.sendRedirect("/nfbbs/InfoServlet");
			return;
		}

		String delete = request.getParameter("delete");
		if (delete != null) {
			if ("post".equals(delete)) {
				/*
				 * 删帖子 ******************************
				 */
				// 1、根据帖子的对应回帖数和回帖用户，更新用户的回帖数
				String pId = request.getParameter("pId");
				List<String> repostUnameList = RepostDao.selectRepostUname(pId);
				boolean subtractRepost;
				for (String rpUname : repostUnameList) {
					subtractRepost = UserDao.subtractRepost(rpUname);

					if (subtractRepost) {
						System.out.println(rpUname + " 回帖数-1 成功");
					} else {
						System.out.println(rpUname + " 回帖数-1 失败");
					}
				}
				// 2、删除帖子
				boolean deletePost = PostDao.deletePost(pId);

				if (deletePost) {
					// 3、更新用户的发帖数
					boolean subtractPost = UserDao.subtractPost(uName);

					if (subtractPost) {
						System.out.println("删帖、更新发帖数、更新回帖数 成功");
					} else {
						System.out.println(uName + " 发帖-1 失败");
					}
				} else {
					System.out.println("删帖 失败");
				}

			} else if ("repost".equals(delete)) {
				/*
				 * 删回帖 ******************************
				 */
				String rpId = request.getParameter("rpId");
				String rpPid = request.getParameter("rpPid");

				// 1、删回帖
				boolean deleteRepost = RepostDao.deleteRepost(rpId);

				if (deleteRepost) {
					// 2、更新用户回帖数
					boolean subtractRepost = UserDao.subtractRepost(uName);

					if (subtractRepost) {
						// 3、更新帖子回帖数
						boolean subtractPostRepost = PostDao.subtractRepost(rpPid);

						if (subtractPostRepost) {
							System.out.println("删回帖、更新用户回帖数、更新帖子回帖数 成功");
						} else {
							System.out.println("帖子回帖数-1 失败");
						}
					} else {
						System.out.println("回帖数-1 失败");
					}
				} else {
					System.out.println("删回帖 失败");
				}
			}

			user = UserDao.checkUser(Integer.toString(user.getuId()), user.getuPassword());
			session.setAttribute("user", user);

			response.sendRedirect("/nfbbs/InfoServlet");
			return;
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
