package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class UserDao {
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
	 * user身份验证 ******************************
	 */
	public static UserBean checkUser(String uId, String uPassword) {
		String sql = "SELECT * FROM user WHERE uid=? and upassword=?";
		UserBean user = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(uId));
			ps.setString(2, uPassword);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new UserBean(rs.getInt("uid"), rs.getString("upassword"), rs.getString("uname"),
						rs.getString("uhead"), rs.getString("upost"), rs.getString("urepost"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			restore(conn, ps, rs);
		}

		return user;
	}

	/*
	 * 更新发帖数 ******************************
	 */
	public static boolean updatePost(String uName) {
		String sql = "UPDATE user SET upost=upost+1 WHERE uname=?";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
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
	 * 更新回帖数 ******************************
	 */
	public static boolean updateRepost(String uName) {
		String sql = "UPDATE user SET urepost=urepost+1 WHERE uname=?";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
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
