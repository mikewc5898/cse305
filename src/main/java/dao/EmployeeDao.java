package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Login;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		try {
			String SSN = employee.getEmployeeID();
			SSN = SSN.replace("-","");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			PreparedStatement st = con.prepareStatement(
					"INSERT IGNORE INTO mwcoulter.Location (ZipCode, City, State) VALUES (?,?,?);");
			st.setInt(1,Integer.valueOf(employee.getZipCode()));
			st.setString(2, employee.getCity());
			st.setString(3, employee.getState());
			st.executeUpdate();
			
			st = con.prepareStatement(
					"INSERT INTO mwcoulter.Person (SSN, LastName, FirstName, Address, ZipCode, Telephone) " +
					"VALUES (?,?,?,?,?,?);");
			st.setInt(1,Integer.valueOf(SSN));
			st.setString(2, employee.getLastName());
			st.setString(3, employee.getFirstName());
			st.setString(4, employee.getAddress());
			st.setInt(5, employee.getZipCode());
			st.setLong(6, Long.valueOf(employee.getTelephone()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"INSERT INTO mwcoulter.LivesAt (ZipCode, SSN) " +
					"VALUES (?,?);");
			st.setInt(1,Integer.valueOf(employee.getZipCode()));
			st.setInt(2, Integer.valueOf(SSN));
			st.executeUpdate();
			
			st = con.prepareStatement("INSERT INTO mwcoulter.Employee (ID, SSN, StartDate, HourlyRate, Email) " +
			"VALUES (?,?,?,?,?);");
			st.setInt(1,Integer.valueOf(SSN));
			st.setInt(2,Integer.valueOf(SSN));
			st.setDate(3,Date.valueOf(employee.getStartDate()));
			st.setInt(4, (int)employee.getHourlyRate());
			st.setString(5, employee.getEmail());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		}
		
	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			PreparedStatement st = con.prepareStatement(
					"UPDATE mwcoulter.Employee SET StartDate = ?, HourlyRate = ?, Email = ? WHERE SSN = ?");
			st.setDate(1, Date.valueOf(employee.getStartDate()));
			st.setInt(2, (int)employee.getHourlyRate());
			st.setString(3, employee.getEmail());
			st.setInt(4, Integer.valueOf(employee.getEmployeeID()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"UPDATE mwcoulter.Person SET LastName = ?, FirstName = ?, Address = ?, ZipCode = ? WHERE SSN = ?");
			st.setString(1, employee.getLastName());
			st.setString(2, employee.getFirstName());
			st.setString(3, employee.getAddress());
			st.setInt(4, employee.getZipCode());
			st.setInt(5, Integer.valueOf(employee.getEmployeeID()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"UPDATE mwcoulter.LivesAt SET ZipCode = ? WHERE SSN = ?");
			st.setInt(1, employee.getZipCode());
			st.setInt(2, Integer.valueOf(employee.getEmployeeID()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"UPDATE mwcoulter.Location SET City = ?, State = ? WHERE ZipCode = ?");
			st.setString(1, employee.getCity());
			st.setString(2, employee.getState());
			st.setInt(3, employee.getZipCode());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			st.executeUpdate(
					"DELETE Employee, Login FROM mwcoulter.Employee INNER JOIN mwcoulter.Login WHERE "
					+ "Email = Username AND ID = " + Integer.valueOf(employeeID));
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		
		/*Sample data begins
		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setEmail("shiyong@cs.sunysb.edu");
			employee.setFirstName("Shiyong");
			employee.setLastName("Lu");
			employee.setAddress("123 Success Street");
			employee.setCity("Stony Brook");
			employee.setStartDate("2006-10-17");
			employee.setState("NY");
			employee.setZipCode(11790);
			employee.setTelephone("5166328959");
			employee.setEmployeeID("631-413-5555");
			employee.setHourlyRate(100);
			employees.add(employee);
		}
		Sample data ends*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.*, E.StartDate, E.HourlyRate, E.Email, E.ID, L.* "
					+ "FROM Employee E, Person P, Location L"
					+ " WHERE P.SSN = E.SSN AND L.ZipCode = P.ZipCode");
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmail(rs.getString("Email"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Address"));
				employee.setCity(rs.getString("City"));
				employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setTelephone(String.valueOf(rs.getLong("Telephone")));
				employee.setEmployeeID(String.valueOf(rs.getInt("SSN")));
				employee.setHourlyRate(rs.getInt("HourlyRate"));
				employees.add(employee);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.*, E.StartDate, E.HourlyRate, E.Email, E.ID, L.* "
					+ "FROM Employee E, Person P, Location L"
					+ " WHERE E.ID = \'" + employeeID + "\' AND P.SSN = E.SSN AND L.ZipCode = P.ZipCode");
			rs.next();
			employee.setEmail(rs.getString("Email"));
			employee.setFirstName(rs.getString("FirstName"));
			employee.setLastName(rs.getString("LastName"));
			employee.setAddress(rs.getString("Address"));
			employee.setCity(rs.getString("City"));
			employee.setStartDate(String.valueOf(rs.getDate("StartDate")));
			employee.setState(rs.getString("State"));
			employee.setZipCode(rs.getInt("ZipCode"));
			employee.setTelephone(String.valueOf(rs.getLong("Telephone")));
			employee.setEmployeeID(String.valueOf(rs.getInt("SSN")));
			employee.setHourlyRate(rs.getInt("HourlyRate"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		Employee employee = new Employee();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT R.CustRepId, P.FirstName, P.LastName, E.Email, Count(*) " +
					"FROM Person P, Rental R, Employee E " +
					"WHERE  P.SSN = R.CustRepId AND E.SSN = P.SSN "+
					"group by P.FirstName, P.LastName " +
					"ORDER BY COUNT(*) DESC; "
					);
			rs.next();
			employee.setEmployeeID(String.valueOf(rs.getInt("CustRepId")));
			employee.setFirstName(rs.getString("FirstName"));
			employee.setLastName(rs.getString("LastName"));
			employee.setEmail(rs.getString("Email"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		return employee;
		
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */
		String ID = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT E.* "
					+ "FROM Employee E"
					+ " WHERE E.Email = \'" + username + "\'");
			rs.next();
			ID = String.valueOf(rs.getInt("ID"));
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return ID;
	}

}
