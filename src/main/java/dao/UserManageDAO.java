package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 *
 */
public class UserManageDAO {
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection connection = null;
	
//	private String jdbcURL = "jdbc:sqlserver://localhost;databaseName=FoodDB";
//	private String jdbcUsername = "sa";
//	private String jdbcPassword = "password";

	private static final String INSERT_USERS_SQL = "insert into USER_DETAILS(username, password, full_name, dob, gender, email, phone_number, home_number, bonus_point) values (?,?,?,?,?,?,?,?,?);";

	private static final String SELECT_USER_BY_ID = "select username, password, full_name, dob, gender, email, phone_number, home_number, bonus_point from USER_DETAILS where id =?";
	private static final String SELECT_ALL_USERS = "select * from USER_DETAILS";
	private static final String DELETE_USERS_SQL = "delete from USER_DETAILS where id = ?;";
	private static final String UPDATE_USERS_SQL = "update USER_DETAILS set username =?, password =?, full_name =?, dob =?, gender =?, email =?, phone_number =?, home_number =?, bonus_point =? where id = ?;";

	public UserManageDAO() {
	}

	// Create Connection
	protected Connection getConnection() {
		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB");

			// 向DataSource要Connection
			connection = ds.getConnection();
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return connection;
			
		}
	}
	
	// Create or insert user
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//			preparedStatement.setString(1, user.getName());
//			preparedStatement.setString(2, user.getEmail());
//			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setString(4, user.getDob()); //long型態毫秒時間轉換成SQL Date
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setString(8, user.getHomeNumber());
            preparedStatement.setFloat(9, user.getBonusPoint());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	// Select user by id
	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("full_name");
				String dob = rs.getString("dob");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String homeNumber = rs.getString("home_number");
				float bonusPoint = rs.getFloat("bonus_point");
				user = new User(id, username, password, fullname, dob, gender, email, phoneNumber, homeNumber, bonusPoint);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	// Select all users
	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println(rs);
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("full_name");
				String dob = rs.getString("dob");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String homeNumber = rs.getString("home_number");
				float bonusPoint = rs.getFloat("bonus_point");
				users.add(new User(id, username, password, fullname, dob, gender, email, phoneNumber, homeNumber, bonusPoint));
			}
			// try-with-resource寫法實作AutoCloseable介面，rs和connection使用完自動關閉
//			rs.close();
//			connection.close();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	// Delete user by id
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// Update user by id
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFullname());
			preparedStatement.setString(4, user.getDob()); //long型態毫秒時間轉換成SQL Date
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setString(7, user.getPhoneNumber());
			preparedStatement.setString(8, user.getHomeNumber());
			preparedStatement.setFloat(9, user.getBonusPoint());
			preparedStatement.setInt(10, user.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
