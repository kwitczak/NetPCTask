package NetPC.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import NetPC.dto.Contact;

public class EditService {
	private List<String> nameList;
	private Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "userlogindb";
	private String driver = "com.mysql.jdbc.Driver";
	private String dbuserName = "root";
	private String dbpassword = "root";

	
	public EditService(){
		nameList = new ArrayList<String>();
	}
	
	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDbuserName() {
		return dbuserName;
	}

	public void setDbuserName(String dbuserName) {
		this.dbuserName = dbuserName;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	public boolean isNotOnList(String email){
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
			String strQuery = "SELECT * FROM clients WHERE email='" + email + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strQuery);
			if(rs.next()){
				return false;
			} else {
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void editDatabase(Map <String, String[]> Parameters, String email){
		System.out.println("Do bazy musze wpisac:");
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
			for(String Param:Parameters.keySet()){
				if (Param.equals("edited")) {break;}
			      String sql = "UPDATE clients SET " + Param + " = '" + Parameters.get(Param)[0] + "' WHERE email = '" + email + "'";
			      System.out.println(sql);
				  Statement st = conn.createStatement();
				  st.executeUpdate(sql);
			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public void insertToDatabase(Map <String, String[]> Parameters, String email){
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
			String sql = "INSERT INTO clients VALUES (";
			for(String Param:Parameters.keySet()){
				if (Param.equals("edited")) {break;}
			      sql = sql + "'" + Parameters.get(Param)[0] + "',";    
			}
			sql = sql.substring(0, sql.length()-1);
			sql = sql + ")";
			System.out.println(sql);
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public void deleteFromDatabase(String email){
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
			String sql = "DELETE FROM clients " +
	                   "WHERE email = '" + email + "'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
