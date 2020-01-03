package com.tedu.jdbc;
//有返回值要定义一个变量承接 Alt+Shift+L
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**Jdbc 的快速入门程序*/
public class JdbcDemo1 {
	public static void main(String[] args) {
		//1.注册数据库驱动
		Connection conn=null;
		ResultSet rs=null;
		Statement stat=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//获得全局限定名
			//2.获取数据库连接
			//?characterEncoding=utf-8是为了防止出现乱码
			//如果连接的是本机并且端口号是3306，可以把localhost：3306去掉
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jt_db?characterEncoding=utf-8","root","root");//前面是连接的端口号，后面两个是用户名和密码
			//3.获取传输器
			stat = conn.createStatement();
			//4.发送sql到服务器执行，并返回结果
			String sql ="select * from account";
			rs = stat.executeQuery(sql);
			//stat.executeUpdate(sql);//增删改都用Update
			//5.处理结果（打印到控制台）
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double money = rs.getDouble("money");
				System.out.println(id+":"+name+":"+money);
			}
		}
		//6.释放资源
		catch (Exception e) {
		} finally {
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
}