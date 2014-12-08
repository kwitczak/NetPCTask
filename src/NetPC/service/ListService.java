package NetPC.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import NetPC.dto.Contact;

public class ListService {
		private List<String> nameList;
		private Connection conn = null;
		private String url = "jdbc:mysql://localhost:3306/";
		private String dbName = "userlogindb";
		private String driver = "com.mysql.jdbc.Driver";
		private String dbuserName = "root";
		private String dbpassword = "root";
	
		
		public ListService(){
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

		public List<String> getNames(){
		
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
				String strQuery = "SELECT email, name, surname FROM clients";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(strQuery);
				while(rs.next()){
					String listItem = String.format("%s - %s %s",rs.getString("email"), rs.getString("name"),  rs.getString("surname"));
					nameList.add(listItem);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			return nameList;
		}
		
		public Contact getCurrentClient(String listItem){
			String[] tokens = listItem.split("\\s");
			Contact currentContact = new Contact();
			
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(url+dbName, dbuserName, dbpassword);
				String strQuery = "SELECT * FROM clients WHERE email='" + tokens[0] +"'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(strQuery);
				if(rs.next()){
					System.out.println(String.format("%s - %s %s, phone number %s and birthdate %s", 
							rs.getString("email"), 
							rs.getString("name"),  
							rs.getString("surname"),
							rs.getString("phonenumber"),
							rs.getString("birthdate")));
					currentContact.setEmail(rs.getString("email"));
					currentContact.setName(rs.getString("name"));
					currentContact.setSurname(rs.getString("surname"));
					currentContact.setPhonenumber(rs.getString("phonenumber"));
					currentContact.setDateofbirth(rs.getString("birthdate"));
					
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			
			
			return currentContact;
		}
	}