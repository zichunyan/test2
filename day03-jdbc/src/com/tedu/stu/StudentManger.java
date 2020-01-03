package com.tedu.stu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import com.tedu.jdbc.JdbcCRUD;
import com.tedu.util.JdbcUtil;
public class StudentManger {
	/**
	 * 学生信息管理系统
	 * 运行程序,根据选项,可以对学生信息进行增删改查操作。
	 */
	private static Scanner sc  =new Scanner(System.in);
	public static void main(String[] args) {
		while(true) {
			//1.提示选择操作选项
			System.out.println("a)查找学生数据");
			System.out.println("b)删除学生数据\t");
			System.out.println("c)修改学生数据\t");
			System.out.println("d)增加学生数据\t");
			System.out.println("请输入想要进行的学生操作");
			String in = sc.nextLine();
			//2.根据选项执行不同操作
			if("a".equalsIgnoreCase(in)) {
				findAll();
			}else if("b".equalsIgnoreCase(in)) {
				delete();
			}else if("c".equalsIgnoreCase(in)) {
				update();
			}else if("d".equalsIgnoreCase(in)) {
				Insert();
			}else {
				System.out.println("输入错误，请重新输入！！！！");
			}
		}
	}
	private static void Insert() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		System.out.println("请输入学生编号：");
		String stuid = sc.nextLine();
		System.out.println("请输入学生姓名:");
		String name = sc.nextLine();
		System.out.println("请输入学生性别:");
		String gender = sc.nextLine();
		System.out.println("请输入学生住址:");
		String addr = sc.nextLine();
		System.out.println("请输入学生分数:");
		double score = Double.parseDouble(sc.nextLine());
		try {
			conn = JdbcCRUD.getConn();
			String sql="insert into stu values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stuid);
			ps.setString(2,name);
			ps.setString(3, gender);
			ps.setString(4, addr);
			ps.setDouble(5, score);
			int row = ps.executeUpdate();
			if(row>0) {
				System.out.println("添加成功");
			}
		} catch (Exception e) {
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
	private static void delete() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		System.out.println("请输入学生编号：");
		String stuid = sc.nextLine();
		try {
			conn = JdbcCRUD.getConn();
			String sql="delete from stu where stuid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stuid);
			int row = ps.executeUpdate();
			if(row>0) {
				System.out.println("删除成功");
			}
		} catch (Exception e) {
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
	private static void update() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		System.out.println("请输入要修改学生编号：");
		String stuid = sc.nextLine();
		System.out.println("请输入要修改学生姓名:");
		String name = sc.nextLine();
		System.out.println("请输入要修改学生性别:");
		String gender = sc.nextLine();
		System.out.println("请输入要修改学生住址:");
		String addr = sc.nextLine();
		System.out.println("请输入要修改学生分数:");
		double score = Double.parseDouble(sc.nextLine());
		try {
			conn = JdbcCRUD.getConn();
			String sql ="update stu set name=?,gender=?,addr=?,score=? where stuid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, addr);
			ps.setDouble(4, score);
			ps.setString(5, stuid);
			int row = ps.executeUpdate();
			if(row>0) {
				System.out.println("修改成功");
			}
		} catch (Exception e) {
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
	private static void findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcCRUD.getConn();
			String sql = "select * from stu";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//相当于一个指针从上到下查找用Query其他用Update
			//这里一定要赋值给rs处理结果不然会出现空指针的错误也就是找不到处理结果的指针
			while(rs.next()){
				String stuid = rs.getString("stuid");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String addr = rs.getString("addr");
				double score = rs.getDouble("score");
				System.out.println( stuid+"，"+name+"，"+gender
						+"，"+addr+"，"+score );
			}
			System.out.println("------------------------------------------------------------------------------");
		} catch (Exception e) {
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
}
