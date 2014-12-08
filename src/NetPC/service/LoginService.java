package NetPC.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginService {
	private Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "userlogindb";
	private String driver = "com.mysql.jdbc.Driver";
	private String dbuserName = "root";
	private String dbpassword = "root";
	private boolean[] niepoprawne = {false,false};
		
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

	public boolean authenticate(String userID, String password){

		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(userID);
		boolean b = m.find();
		if (b){
		   System.out.println("W twoim loginie wykryto niepoprawne znaki...");
		   niepoprawne[0] = true;
		   return false;
		} else {
			niepoprawne[0] = false;
		}
		m = p.matcher(password);
		b = m.find();
		if (b){
		   System.out.println("W twoim hasle wykryto niepoprawne znaki...");
		   niepoprawne[1] = true;
		   return false;
		} else {
			niepoprawne[1] = false;
		}
		
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
			String strQuery = "SELECT * FROM users WHERE username='" + userID + "'and password = '" + password + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strQuery);
			if(rs.next()){
				st.close();
				rs.close();	
				return true;		
			} else {
				st.close();
				rs.close();	
				return false;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean[] getNiepoprawne() {
		return niepoprawne;
	}

	public void setNiepoprawne(boolean[] niepoprawne) {
		this.niepoprawne = niepoprawne;
	}
	
}
