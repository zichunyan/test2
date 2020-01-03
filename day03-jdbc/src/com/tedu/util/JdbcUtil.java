package com.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 Jdbc 工具类
 */
public class JdbcUtil {
	/**
	 释放Jdbc程序中的资源
	 conn 连接对象
	 rs 结果集对象
	 stat 传输器对象
	 */
	public static Connection getConn() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");//获得全局限定名
		//2.获取数据库连接
		//?characterEncoding=utf-8是为了防止出现乱码
		//如果连接的是本机并且端口号是3306，可以把localhost：3306去掉
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jt_db?characterEncoding=utf-8","root","root");
		return conn;
	}
	public static void close(Connection conn,Statement stat,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				rs=null;
			}
		}
		if(stat!=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
				stat=null;
			}
		}			
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				conn=null;
			}
		}
	}
}
