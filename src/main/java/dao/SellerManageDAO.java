package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import model.Seller;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 *
 */
public class SellerManageDAO {
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection connection = null;

//	private String jdbcURL = "jdbc:sqlserver://localhost;databaseName=FoodDB";
//	private String jdbcUsername = "sa";
//	private String jdbcPassword = "password";

	private static final String INSERT_SELLERS_SQL = "insert into SELLER_DETAILS(username, password, full_name, dob, gender, "
			+ "email, phone_number, telephone_number, extension_number, company_name, company_address, "
			+ "business_cert, verify_status) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_SELLER_BY_ID = "select username, password, full_name, dob, gender, email, phone_number, telephone_number, "
			+ "extension_number, company_name, company_address, business_cert, verify_status from SELLER_DETAILS where id =?";
	private static final String SELECT_ALL_SELLERS = "select * from SELLER_DETAILS";
	private static final String DELETE_SELLERS_SQL = "delete from SELLER_DETAILS where id = ?;";
	private static final String UPDATE_SELLERS_SQL = "update SELLER_DETAILS set username =?, password =?, "
			+ "full_name =?, dob =?, gender =?, email =?, phone_number =?, telephone_number =?, extension_number =?, "
			+ "company_name =?, company_address =?, business_cert =?, verify_status =? where id = ?;";

	public SellerManageDAO() {
	}

	// Create Connection
	protected Connection getConnection() {
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

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
	public void insertSeller(Seller seller) throws SQLException {
		System.out.println(INSERT_SELLERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SELLERS_SQL)) {
//			preparedStatement.setString(1, user.getName());
//			preparedStatement.setString(2, user.getEmail());
//			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(1, seller.getUsername());
			preparedStatement.setString(2, seller.getPassword());
			preparedStatement.setString(3, seller.getFullname());
			preparedStatement.setString(4, seller.getDob()); // long型態毫秒時間轉換成SQL Date
			preparedStatement.setString(5, seller.getGender());
			preparedStatement.setString(6, seller.getEmail());
			preparedStatement.setString(7, seller.getPhoneNumber());
			preparedStatement.setString(8, seller.getTelephoneNumber());
			preparedStatement.setString(9, seller.getExtensionNumber());
			preparedStatement.setString(10, seller.getCompanyName());
			preparedStatement.setString(11, seller.getCompanyAddress());
			preparedStatement.setBytes(12, seller.getBusinessCert());
			preparedStatement.setBoolean(13, seller.getVerifyStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// Select seller by id
	public Seller selectSeller(int id) {
		Seller seller = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SELLER_BY_ID);) {
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
				String telephoneNumber = rs.getString("telephone_number");
				String extensionNumber = rs.getString("extension_number");
				String companyName = rs.getString("company_name");
				String companyAddress = rs.getString("company_address");
				byte[] businessCert = rs.getBytes("business_cert");
				Boolean verifyStatus = rs.getBoolean("verify_status");
				seller = new Seller(id, username, password, fullname, dob, gender, email, phoneNumber, telephoneNumber,
						extensionNumber, companyName, companyAddress, businessCert, verifyStatus);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return seller;
	}

	// Select all sellers
	public List<Seller> selectAllSellers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Seller> sellers = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SELLERS);) {
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
				String telephoneNumber = rs.getString("telephone_number");
				String extensionNumber = rs.getString("extension_number");
				String companyName = rs.getString("company_name");
				String companyAddress = rs.getString("company_address");
				byte[] businessCert = rs.getBytes("business_cert");
				Boolean verifyStatus = rs.getBoolean("verify_status");
				sellers.add(new Seller(id, username, password, fullname, dob, gender, email, phoneNumber,
						telephoneNumber, extensionNumber, companyName, companyAddress, businessCert, verifyStatus));
			}
			// try-with-resource寫法實作AutoCloseable介面，rs和connection使用完自動關閉
//			rs.close();
//			connection.close();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return sellers;
	}

	// Delete seller by id
	public boolean deleteSeller(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SELLERS_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// Update seller by id
	public boolean updateSeller(Seller seller) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELLERS_SQL);) {
			preparedStatement.setString(1, seller.getUsername());
			preparedStatement.setString(2, seller.getPassword());
			preparedStatement.setString(3, seller.getFullname());
			preparedStatement.setString(4, seller.getDob()); // long型態毫秒時間轉換成SQL Date
			preparedStatement.setString(5, seller.getGender());
			preparedStatement.setString(6, seller.getEmail());
			preparedStatement.setString(7, seller.getPhoneNumber());
			preparedStatement.setString(8, seller.getTelephoneNumber());
			preparedStatement.setString(9, seller.getExtensionNumber());
			preparedStatement.setString(10, seller.getCompanyName());
			preparedStatement.setString(11, seller.getCompanyAddress());
			preparedStatement.setBytes(12, seller.getBusinessCert());
			preparedStatement.setBoolean(13, seller.getVerifyStatus());
			preparedStatement.setInt(14, seller.getId());

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
