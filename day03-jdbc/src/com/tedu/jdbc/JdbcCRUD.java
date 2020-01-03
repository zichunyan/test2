package com.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.jupiter.api.Test;
import com.tedu.util.JdbcUtil;


/**
 Jdbc的增删改查
 CRUD：C(create) R(Retrieve) U(Update) D(delete)
 */

public class JdbcCRUD {
	public static Connection getConn() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");//获得全局限定名
		//2.获取数据库连接
		//?characterEncoding=utf-8是为了防止出现乱码
		//如果连接的是本机并且端口号是3306，可以把localhost：3306去掉
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jt_db?characterEncoding=utf-8","root","root");
		return conn;
	}
	//往account表中插入一条新的记录，name为 "john"，money为30000
	@Test
	public void add() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			//注册驱动并获取连接
			conn = JdbcCRUD.getConn();
			//获取传输器并执行sql语句
			stat = conn.createStatement();
			String sql ="insert into account values (null,'join',3000)";
			int rows=stat.executeUpdate(sql);
			System.out.println("影响行数："+rows);
		} catch (Exception e) {

		}finally {
			JdbcUtil.close(conn, stat, rs);
		}
	}
	//修改account表中name为 "john" 的记录，将金额改为2500
	@Test
	public void update() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			//注册驱动并获取连接
			conn = JdbcCRUD.getConn();
			stat = conn.createStatement();
			String sql ="update account set money = 1500 where name = 'join'";
			int rows = stat.executeUpdate(sql);
			System.out.println("影响行数："+rows);
		} catch (Exception e) {

		}finally {
			JdbcUtil.close(conn, stat, rs);
		}
	}
	//删除account表中name为 "john" 的记录
	@Test
	public void delete() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			conn = JdbcCRUD.getConn();
			stat = conn.createStatement();
			String sql="delete from account where name='join'";
			int row = stat.executeUpdate(sql);
			System.out.println("影响行数："+row);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JdbcUtil.close(conn, stat, rs);
		}
	}
	//查询account表中id为1的记录
	@Test
	public void findByld() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			conn = JdbcCRUD.getConn();
			stat = conn.createStatement();
			String sql="select * from account where id=1";
			rs = stat.executeQuery(sql);//这里如果不赋值给结果会出现空指针异常
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double money = rs.getDouble("money");
				System.out.println(id+":"+name+":"+money);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, stat, rs);
		}
	}
}
