package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.RepostBean;

public class RepostDao {
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
	 * 回帖列表（发帖编号搜索） ******************************
	 */
	public static List<RepostBean> selectRepost(String pId) {
		String sql = "SELECT * FROM repost WHERE rppid=? ORDER BY rptime DESC";
		RepostBean repost = null;
		List<RepostBean> repostList = new ArrayList<RepostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pId));
			rs = ps.executeQuery();

			while (rs.next()) {
				repost = new RepostBean(rs.getInt("rpid"), rs.getInt("rppid"), rs.getString("rpcontent"),
						rs.getString("rpuname"), rs.getString("rptime"));
				repostList.add(repost);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return repostList;
	}

	/*
	 * 回帖列表（回帖人搜索） ******************************
	 */
	public static List<RepostBean> selectUnameRepost(String uName) {
		String sql = "SELECT * FROM repost WHERE rpuname=? ORDER BY rptime DESC";
		RepostBean repost = null;
		List<RepostBean> repostList = new ArrayList<RepostBean>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			rs = ps.executeQuery();

			while (rs.next()) {
				repost = new RepostBean(rs.getInt("rpid"), rs.getInt("rppid"), rs.getString("rpcontent"),
						rs.getString("rpuname"), rs.getString("rptime"));
				repostList.add(repost);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return repostList;
	}

	/*
	 * 回帖子 ******************************
	 */
	public static boolean insertRepost(String rpPid, String rpContent, String rpUname, String rpTime) {
		String sql = "INSERT INTO repost (rppid,rpcontent,rpuname,rptime) VALUES (?,?,?,?)";

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(rpPid));
			ps.setString(2, rpContent);
			ps.setString(3, rpUname);
			ps.setString(4, rpTime);
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
	 * 计算回帖数 ******************************
	 */
	public static List<String> selectRepostUname(String pId) {
		String sql = "SELECT rpuname FROM repost WHERE rppid=?";
		String uName = null;
		List<String> repostUnameList = new ArrayList<String>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pId));
			rs = ps.executeQuery();

			while (rs.next()) {
				uName = new String(rs.getString("rpuname"));
				repostUnameList.add(uName);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return repostUnameList;
	}

	/*
	 * 删除回帖 ******************************
	 */
	public static boolean deleteRepost(String rpId) {
		String sql = "DELETE FROM repost WHERE rpid=?";

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, rpId);
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
