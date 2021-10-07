package carts.list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListDAO {

	private Connection conn;
	
	public ListDAO(Connection conn) {
		this.conn = conn;
	}
	public Vector<String> findAllByItemno(int itemNo) throws SQLException {
		String sqlStr = "select * from List where ItemNo = ? ";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, itemNo);
		ResultSet rs = state.executeQuery();
		ListBean list = new ListBean();
		Vector<String> shopping = new Vector<String>();
//		Vector<String> sh = new Vector<String>();
		while(rs.next()) {
			list.setItemNo(rs.getInt("ItemNo"));
			list.setProductName(rs.getString("ProductName"));
			list.setPrice(rs.getInt("Price"));
			list.setNumber(rs.getInt("Number"));
			System.out.println("No.:"+list.getItemNo()+"ProductName:"+list.getProductName()+"Price:"+list.getPrice()+"Number:"+list.getNumber());
			
			shopping.add(rs.getString("ProductName"));
			shopping.add(Integer.toString(rs.getInt("Price")));
			shopping.add(Integer.toString(rs.getInt("Number")));
			
//			sh.addAll(shopping);
			
			
		}
//		System.out.println(shopping);
		return shopping;
	}
	
	public boolean findByItemnoAndProductname(int itemNo, String productName) throws SQLException {
		String sqlStr = "select * from List where ItemNo = ? and ProductName = ?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, itemNo);
		state.setString(2, productName);
		ResultSet rs = state.executeQuery();
		
		boolean i;
		
		ListBean list = new ListBean();
		if(rs.next()) {
			list.setItemNo(rs.getInt("ItemNo"));
			list.setProductName(rs.getString("ProductName"));
			list.setPrice(rs.getInt("Price"));
			list.setNumber(rs.getInt("Number"));
			i = true;
		}else {
			i = false;
		}
		System.out.println("List是否存在 " +i);
		return  i ;
	}
	public void insertList(int itemNo,String productName,int number,int price) throws SQLException {
		String sqlStr = "insert into List (ItemNo, ProductName, Number, Price) values (?,?,?,?)";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, itemNo);
		state.setString(2, productName);
		state.setInt(3, number);
		state.setInt(4, price);
		state.executeUpdate();
		
		System.out.println("建立清單");
	}
	public void updateByItemno(int number , int itemNo , String productNmae) throws SQLException {
//		String sqlStr = "update List set Number = ? where ItemNo = ? and ProductName = ?";
//		PreparedStatement state = conn.prepareStatement(sqlStr);
//		state.setInt(1, number);
//		state.setInt(2, itemNo);
//		state.setString(3, productNmae);
//		state.executeUpdate();
//		
//		System.out.println("更新清單");
		updateByItemnoAndExist(number, "N", itemNo, productNmae);
	}
	public void updateByItemnoAndExist(int number ,String exist, int itemNo , String productNmae) throws SQLException {
		String sqlStr=null;
		String sqlStr1 = "update List set Number = ?  where ItemNo = ? and ProductName = ?";
		String sqlStr2 = "update List set Number = ? , exist = \'Y\'  where ItemNo = ? and ProductName = ?";
		
		if (exist.equals("Y")) {
			sqlStr = sqlStr2;
		}else {
			sqlStr =sqlStr1;
		}
		

		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, number);
		state.setInt(2, itemNo);
		state.setString(3, productNmae);
		int count = state.executeUpdate();
		System.out.println("Lcount:"+count);
		System.out.println("更新清單");
		

	}
	public void deteleByItemno(int itemNO) throws SQLException {
		String sqlStr = "delete List where ItemNo = ? and exist is null";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, itemNO);
		state.executeUpdate();
	}
	public int findAmountByItemno(int itemNo) throws SQLException {
		String sqlStr = "select sum( price * number ) Amount from List where ItemNo = ?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, itemNo);
		ResultSet rs = state.executeQuery();
		int amount =0;
		if(rs.next()){
			amount = rs.getInt("Amount");
			System.out.println(amount);
		}
		return amount;
	}

}
