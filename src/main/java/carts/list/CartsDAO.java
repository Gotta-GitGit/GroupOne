package carts.list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class CartsDAO {
	
	
	private Connection conn;
	
	public CartsDAO(Connection conn) {
		this.conn = conn;
	}
	public Vector<String> findTwoCart(String username) throws SQLException {
		String sqlStr = "select * from Cart where Username = ? and Payday is null and Wrong is null";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, username);
		ResultSet rs = state.executeQuery();
		
		CartsBean cart = new CartsBean();
		Vector<String> nowCart = new Vector<String>();
		while(rs.next()) {
			cart.setItemNo(rs.getInt("ItemNo"));
//			cart.setUsername(rs.getString("Username"));
			cart.setType(rs.getString("ShoppingType"));
			
			String itemNo = Integer.toString(cart.getItemNo());
			String type = cart.getType();
			System.out.println(type);
			if(type.equals("food")) {
				type = "訂餐";
			}else {
				type = "團購券";
			}
			nowCart.add(itemNo);
			nowCart.add(type);
		}
		return nowCart;
	}
	
	public Vector<String> findAllCartByUsername(String username) throws SQLException {
		String sqlStr = "select * from Cart where Username = ? and Payday is not null";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, username);
		ResultSet rs = state.executeQuery();
		
		CartsBean cart = new CartsBean();
		Vector<String> usedCart = new Vector<String>();
		while(rs.next()) {
			cart.setItemNo(rs.getInt("ItemNo"));
//			cart.setUsername(rs.getString("Username"));
			cart.setType(rs.getString("ShoppingType"));
			cart.setAmonunt(rs.getInt("Amount"));
			cart.setPoints(rs.getFloat("Points"));
			cart.setPayday(rs.getDate("Payday"));
			cart.setWrong(rs.getString("Wrong"));
			
			
			String itemNo = Integer.toString(cart.getItemNo());
			String type = cart.getType();
			System.out.println(type);
			if(type.equals("food")) {
				type = "訂餐";
			}else {
				type = "團購券";
			}
			String amount = Integer.toString(cart.getAmonunt());
			String point = Float.toString(cart.getPoints());
			Date date = cart.getPayday();
			System.out.println("sqlDate: "+date);
			String payday = date.toString();
//			DateFormat dateF = new SimpleDateFormat("yyyy-mm-dd");
//			String payday = dateF.format(date);
			String wrong = cart.getWrong();
			if(wrong == null) {
				wrong = "無";
			}
			
			usedCart.add(itemNo);
			usedCart.add(type);
			usedCart.add(amount);
			usedCart.add(point);
			usedCart.add(payday);
			usedCart.add(wrong);
			
			System.out.println("清單編號 :"+itemNo+" 商品類別 :"+type+" 總金額 :"+amount+" 點數增減 :"+point+" 付款日期 :"+payday+" 備註 :"+wrong);
		}
		return usedCart;
	}
	
	public int findByUsername(String username, String type) throws SQLException {
		String sqlStr = "select * from Cart where Username = ? and ShoppingType = ? and Payday is null and Wrong is null";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, username);
		state.setString(2, type);
		ResultSet rs = state.executeQuery();
		
		CartsBean cart = new CartsBean();		
		if (rs.next()) {
			cart.setItemNo(rs.getInt("ItemNo"));
			cart.setUsername(rs.getString("Username"));
			cart.setType(rs.getString("ShoppingType"));
			cart.setAmonunt(rs.getInt("Amount"));
			cart.setPoints(rs.getFloat("Points"));
			cart.setPayday(rs.getDate("Payday"));
			cart.setWrong(rs.getString("Wrong"));
			
			System.out.println(username +" No. "+ cart.getItemNo() + "." +cart.getType());
		}else {
			insertCart(username,type);
		}
		System.out.println("DAO - "+cart.getItemNo());
		return cart.getItemNo();
	}
	public void insertCart(String username , String type ) throws SQLException {
		String sqlStr = "insert into Cart (Username, ShoppingType) values (?,?)";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, username);
		state.setString(2, type);
		state.executeUpdate();
		
		System.out.println("New Cart");
//		findByUsername(username, type);
		
	}
	public void updateByUsername(int amount , float points ,String username , int itemNo ) throws SQLException {
		
		Date utilD = new Date();
		java.sql.Date sqlD = new java.sql.Date(utilD.getTime());
		System.out.println("sql Time:"+sqlD);
		
		String sqlStr = "update Cart set Amount = ? , Points = ? , Payday = ? where Username = ? and ItemNo =? ";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, amount);
		state.setFloat(2, points);
		state.setDate(3, sqlD);
		state.setString(4, username);
		state.setInt(5, itemNo);
		int count = state.executeUpdate();
		System.out.println("Ccount:"+count);
	/*
		float AllPoint=0;
		String sqlStr2 = "select username, bonus_point from USER_DETAILS where username = ?";
		PreparedStatement state2 = conn.prepareStatement(sqlStr2);
		state2.setString(1, username);
		ResultSet rs2 = state2.executeQuery();
		if(rs2.next()) {
			AllPoint = rs2.getFloat("bonus_point");
			System.out.println("point:"+AllPoint);
		}
		AllPoint = AllPoint + points;
		System.out.println("Allpoint:"+AllPoint);
		
		String sqlStr3 = "update USER_DETAILS set bonus_point =? where username =?";
		PreparedStatement state3 = conn.prepareStatement(sqlStr3);
		state3.setFloat(1, AllPoint);
		state3.setString(2, username);
		state3.executeUpdate();
		*/
	}
	public void deleteByitemNo(int itemNo) throws SQLException {
		String sqlStr = "delete Cart where ItemNo = ?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, itemNo);
		state.executeUpdate();
	}
}
