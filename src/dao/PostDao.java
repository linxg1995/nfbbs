package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.PostBean;
import bean.RepostBean;

public class PostDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/nfbbs?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/*
	 * 连接MySQL数据库 ******************************
	 */
	public static Connection getConn() {
		try {
			// 加载驱动
			Class.forName(DRIVER);
			// 获取连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * 首页帖子列表 ******************************
	 */
	public static List<PostBean> selectHomePost(String pType) {
		String sql = "SELECT * FROM post WHERE ptype=? ORDER BY prptime DESC LIMIT 0,5";
		PostBean post = null;
		List<PostBean> postList = new ArrayList<PostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pType);
			rs = ps.executeQuery();

			while (rs.next()) {
				post = new PostBean(rs.getInt("pid"), rs.getString("ptitle"), rs.getString("ptype"),
						rs.getString("pcontent"), rs.getString("puname"), rs.getString("ptime"), rs.getInt("prepost"),
						rs.getString("prptime"));
				postList.add(post);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return postList;
	}

	/*
	 * 帖子列表（所有搜索） ******************************
	 */
	public static List<PostBean> selectAllPost() {
		String sql = "SELECT * FROM post ORDER BY prptime DESC";
		PostBean post = null;
		List<PostBean> postList = new ArrayList<PostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				post = new PostBean(rs.getInt("pid"), rs.getString("ptitle"), rs.getString("ptype"),
						rs.getString("pcontent"), rs.getString("puname"), rs.getString("ptime"), rs.getInt("prepost"),
						rs.getString("prptime"));
				postList.add(post);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return postList;
	}

	/*
	 * 帖子列表（分类搜索） ******************************
	 */
	public static List<PostBean> selectTypePost(String pType) {
		String sql = "SELECT * FROM post WHERE ptype=? ORDER BY prptime DESC";
		PostBean post = null;
		List<PostBean> postList = new ArrayList<PostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pType);
			rs = ps.executeQuery();

			while (rs.next()) {
				post = new PostBean(rs.getInt("pid"), rs.getString("ptitle"), rs.getString("ptype"),
						rs.getString("pcontent"), rs.getString("puname"), rs.getString("ptime"), rs.getInt("prepost"),
						rs.getString("prptime"));
				postList.add(post);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return postList;
	}

	/*
	 * 帖子列表（关键字搜索） ******************************
	 */
	public static List<PostBean> selectKeywordPost(String keyword) {
		String sql = "SELECT * FROM post WHERE ptitle LIKE '%" + keyword + "%' ORDER BY prptime DESC";
		PostBean post = null;
		List<PostBean> postList = new ArrayList<PostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				post = new PostBean(rs.getInt("pid"), rs.getString("ptitle"), rs.getString("ptype"),
						rs.getString("pcontent"), rs.getString("puname"), rs.getString("ptime"), rs.getInt("prepost"),
						rs.getString("prptime"));
				postList.add(post);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return postList;
	}

	/*
	 * 帖子列表（发帖人搜索） ******************************
	 */
	public static List<PostBean> selectUnamePost(String uName) {
		String sql = "SELECT * FROM post WHERE puname=? ORDER BY prptime DESC";
		PostBean post = null;
		List<PostBean> postList = new ArrayList<PostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			rs = ps.executeQuery();

			while (rs.next()) {
				post = new PostBean(rs.getInt("pid"), rs.getString("ptitle"), rs.getString("ptype"),
						rs.getString("pcontent"), rs.getString("puname"), rs.getString("ptime"), rs.getInt("prepost"),
						rs.getString("prptime"));
				postList.add(post);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return postList;
	}

	/*
	 * 帖子详情 ******************************
	 */
	public static PostBean selectPost(String pId) {
		String sql = "SELECT * FROM post WHERE pid=?";
		PostBean post = null;

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pId));
			rs = ps.executeQuery();

			while (rs.next()) {
				post = new PostBean(rs.getInt("pid"), rs.getString("ptitle"), rs.getString("ptype"),
						rs.getString("pcontent"), rs.getString("puname"), rs.getString("ptime"), rs.getInt("prepost"),
						rs.getString("prptime"));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return post;
	}

	/*
	 * 发帖子 ******************************
	 */
	public static boolean insertPost(String pTitle, String pType, String pContent, String pUname, String pTime,
			String pRptime) {
		String sql = "INSERT INTO post (ptitle,ptype,pcontent,puname,ptime,prepost,prptime) VALUES (?,?,?,?,?,?,?)";

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pTitle);
			ps.setString(2, pType);
			ps.setString(3, pContent);
			ps.setString(4, pUname);
			ps.setString(5, pTime);
			ps.setInt(6, 0);
			ps.setString(7, pRptime);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			restore(conn, ps, rs);
		}

		return true;
	}

	/*
	 * 回帖列表标题（回帖人搜索） ******************************
	 */
	public static List<String> selectRepostPtitle(String uName) {
		String sql = "SELECT rp.*,p.ptitle FROM (SELECT * FROM repost WHERE rpuname=?) rp JOIN post p ON rp.rppid=p.pid ORDER BY rp.rptime DESC";
		String pTitle = null;
		List<String> repostListTitle = new ArrayList<String>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			rs = ps.executeQuery();

			while (rs.next()) {
				pTitle = new String(rs.getString("p.ptitle"));
				repostListTitle.add(pTitle);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return repostListTitle;
	}

	/*
	 * 更新回帖数 ******************************
	 */
	public static boolean updateRepost(String pId, String pRptime) {
		String sql = "UPDATE post SET prepost=prepost+1,prptime=? WHERE pid=?";

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pRptime);
			ps.setString(2, pId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			restore(conn, ps, rs);
		}

		return true;
	}

	/*
	 * 更新回帖数（删除） ******************************
	 */
	public static boolean subtractRepost(String pId) {
		String sql = "UPDATE post SET prepost=prepost-1 WHERE pid=?";

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			restore(conn, ps, rs);
		}

		return true;
	}

	/*
	 * 删除帖子 ******************************
	 */
	public static boolean deletePost(String pId) {
		String sql = "DELETE FROM post WHERE pid=?";

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			restore(conn, ps, rs);
		}

		return true;
	}

	/*
	 * 关闭连接 ******************************
	 */
	public static void restore(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			conn = null;
			ps = null;
			rs = null;
		} catch (SQLException e) {
			// TODO: handle exception
			conn = null;
			ps = null;
			rs = null;
		}
	}
}
