package Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Restaurant.RestaurantBean;

public class MenuDAO {
	private Connection conn;

	public MenuDAO(Connection conn) {
		this.conn = conn;
	}

	// 新增菜色
	public boolean insertMenu(MenuBean menuData) {
		try {
			String sqlString = "insert into menu values('" + menuData.getProductName() + "','" + menuData.getRemark()
					+ "','" + menuData.getPrice() + "')";

			Statement stmt = conn.createStatement();
			System.out.println(sqlString);
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("新增菜色時發生錯誤:" + e);
			return false;
		}
	}

	// 刪除菜色
	public boolean deletefromMenu(MenuBean menuData) {

		try {
			String sqlString = "delete from menu where productname=?";
			PreparedStatement stmt = conn.prepareStatement(sqlString);
			stmt.setString(1, menuData.getProductName());			

			int deletecount = stmt.executeUpdate();
			stmt.close();
			System.out.println("delete OK!");

			if (deletecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("刪除時發生錯誤: " + e);
			return false;
		}
	}

	// 修改菜色
	public boolean alterMenu(MenuBean menuData) {
		try {
			String sqlString = "UPDATE menu SET price = ?, remark = ? where productname = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlString);
			stmt.setString(1, menuData.getPrice());
			stmt.setString(2, menuData.getRemark());
			stmt.setString(3, menuData.getProductName());

			int updatecount = stmt.executeUpdate();
			stmt.close();
			if (updatecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("更新時發生錯誤:" + e);
			return false;
		}
	}

	// 查詢菜色
	public Vector<MenuBean> findallMenu() throws SQLException {

		String sqlString = "select * from menu";
		PreparedStatement stmt = conn.prepareStatement(sqlString);
		ResultSet rs = stmt.executeQuery();
		Vector<MenuBean> menulist = new Vector<MenuBean>();

		while (rs.next()) {
			MenuBean menu = new MenuBean(rs.getString("productname"), rs.getString("remark"), rs.getString("price"));
			System.out.println(
					rs.getString("productname") + "\n" + rs.getString("remark") + "\n" + rs.getString("price") + "\n");
//				 menu.setProductName(rs.getString("productname"));
//				 menu.setRemark(rs.getString("remark"));
//				 menu.setPrice(rs.getString("price"));

//				 menulist.add(rs.getString("productname"));
//				 menulist.add(rs.getString("remark"));
//				 menulist.add(rs.getString("price"));
			menulist.add(menu);
		}
		stmt.close();
		return menulist;

	}

	// 查詢某一菜色
	public boolean findMenu(MenuBean menuData) {
		try {
			String sqlString = "select * from menu where productname = ?";
			PreparedStatement stmt = conn.prepareStatement(sqlString);
			stmt.setString(1, menuData.getProductName());
			ResultSet rs = stmt.executeQuery();
			System.out.println(sqlString);
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("查詢某菜色時發生錯誤:" + e);
			return false;
		}
	}

}
