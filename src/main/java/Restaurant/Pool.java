//package Restaurant;
//
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//	public class Pool {
//		
//		private HikariDataSource ds;
//		
//		public HikariDataSource openDataSource() {
//			HikariConfig config =new HikariConfig();
//			config.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=EEIT133GroupOne");
//			config.setUsername("sa");
//			config.setPassword("123456");
//			config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			config.setMaximumPoolSize(10);
//			
//			ds = new HikariDataSource(config);
//			
//			return ds;
//		
//		
//		}
//		
//		public void closeDataSource() {
//			if(ds!=null) {
//				ds.close();
//			}
//		}
//		
//
//
//}
