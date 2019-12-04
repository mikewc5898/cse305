package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Customer;
import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		Login login = new Login();	
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT L.* FROM Login L " +
				"WHERE L.Username = \'" +username + "\' AND L.Password = \'" + password + "\'" );
				rs.next();
				login.setUsername(rs.getString("Username"));
				login.setPassword(rs.getString("Password"));
				login.setRole(rs.getString("Role"));
	}
	catch(Exception e) {
		System.out.println(e);
	}
		return login;	
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			PreparedStatement st = con.prepareStatement(
					"INSERT IGNORE INTO mwcoulter.Login (Username, Password, Role) VALUES (?,?,?);");
			st.setString(1,login.getUsername());
			st.setString(2, login.getPassword());
			st.setString(3, login.getRole());
			st.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		}
	}
