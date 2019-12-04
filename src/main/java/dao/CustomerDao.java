package dao;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Customer;
import model.Movie;
import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers() {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.*,C.*,L.* From Person P, Customer C, Location L " +
					"WHERE P.SSN = C.Id AND P.ZipCode=L.ZipCode");
					while (rs.next()) {
						Customer customer = new Customer();
						customer.setCustomerID(rs.getString("Id"));
						customer.setAddress(rs.getString("Address"));
						customer.setLastName(rs.getString("LastName"));
						customer.setFirstName(rs.getString("FirstName"));
						customer.setCity(rs.getString("City"));
						customer.setState(rs.getString("State"));
						customer.setEmail(rs.getString("Email"));
						customer.setZipCode(rs.getInt("ZipCode"));
						customer.setTelephone(rs.getString("Telephone"));
						customer.setCreditCard(rs.getString("CreditCardNumber"));
						customer.setRating(rs.getInt("Rating"));
						customers.add(customer);			
					}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */


		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT C.Id, P.FirstName, P.LastName, C.Email " +
					"FROM Person P, Rental R, Account A, Customer C " +
					"WHERE  R.AccountId = A.Id AND A.Customer = P.SSN  AND A.Customer = C.Id " +
					"group by P.FirstName, P.LastName " +
					"ORDER BY COUNT(*) DESC"
					);
			rs.next();
			customer.setCustomerID(String.valueOf(rs.getInt("Id")));
			customer.setFirstName(rs.getString("FirstName"));
			customer.setLastName(rs.getString("LastName"));
			customer.setEmail(rs.getString("Email"));
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		return customer;
		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.*,C.*,L.* From Person P, Customer C, Location L " +
				"WHERE P.SSN = C.Id AND P.ZipCode=L.ZipCode");
				
				while (rs.next()) {
					Customer customer = new Customer();
					customer.setCustomerID(rs.getString("Id"));
					customer.setAddress(rs.getString("Address"));
					customer.setLastName(rs.getString("LastName"));
					customer.setFirstName(rs.getString("FirstName"));
					customer.setCity(rs.getString("City"));
					customer.setState(rs.getString("State"));
					customer.setEmail(rs.getString("Email"));
					customer.setZipCode(rs.getInt("ZipCode"));
					customers.add(customer);			
				}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.*,C.*,L.* From Person P, Customer C, Location L " +
				"WHERE C.Id = " + Integer.valueOf(customerID) + " AND P.SSN = C.Id AND P.ZipCode=L.ZipCode");
					rs.next();
					customer.setTelephone(String.valueOf(rs.getLong("Telephone")));
					customer.setCreditCard(String.valueOf(rs.getLong("CreditCardNumber")));
					customer.setRating(rs.getInt("Rating"));
					customer.setCustomerID(rs.getString("Id"));
					customer.setAddress(rs.getString("Address"));
					customer.setLastName(rs.getString("LastName"));
					customer.setFirstName(rs.getString("FirstName"));
					customer.setCity(rs.getString("City"));
					customer.setState(rs.getString("State"));
					customer.setEmail(rs.getString("Email"));
					customer.setZipCode(rs.getInt("ZipCode"));		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			st.executeUpdate(
					"DELETE FROM mwcoulter.Customer " +
					"WHERE `Id` = " + Integer.valueOf(customerID));
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */

		String ID = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT C.* "
					+ "FROM Customer C"
					+ " WHERE C.Email = \'" + username + "\'");
			rs.next();
			ID = String.valueOf(rs.getInt("Id"));
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return ID;
	}


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setCustomerID("111-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Shiyong");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);			
		}
		/*Sample data ends*/
		
		return customers;

	}


	public String addCustomer(Customer customer) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			PreparedStatement st = con.prepareStatement(
					"INSERT IGNORE INTO mwcoulter.Location (ZipCode, City, State) VALUES (?,?,?);");
			st.setInt(1,Integer.valueOf(customer.getZipCode()));
			st.setString(2, customer.getCity());
			st.setString(3, customer.getState());
			st.executeUpdate();
			
			st = con.prepareStatement(
					"INSERT INTO mwcoulter.Person (SSN, LastName, FirstName, Address, ZipCode, Telephone) " +
					"VALUES (?,?,?,?,?,?);");
			st.setInt(1,Integer.valueOf(customer.getCustomerID()));
			st.setString(2, customer.getLastName());
			st.setString(3, customer.getFirstName());
			st.setString(4, customer.getAddress());
			st.setInt(5, customer.getZipCode());
			st.setLong(6, Long.valueOf(customer.getTelephone()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"INSERT INTO mwcoulter.LivesAt (ZipCode, SSN) " +
					"VALUES (?,?);");
			st.setInt(1,Integer.valueOf(customer.getZipCode()));
			st.setInt(2, Integer.valueOf(customer.getCustomerID()));
			st.executeUpdate();
			
			st = con.prepareStatement("INSERT INTO mwcoulter.Customer (ID, Rating, CreditCardNumber, Email) " +
			"VALUES (?,?,?,?);");
			st.setInt(1,Integer.valueOf(customer.getCustomerID()));
			st.setInt(2,Integer.valueOf(customer.getRating()));
			st.setLong(3,Long.valueOf(customer.getCreditCard()));
			st.setString(4, customer.getEmail());
			st.executeUpdate();
			
			st = con.prepareStatement("INSERT INTO mwcoulter.Account (Id, DateOpened, AcctType, Customer) " +
					"VALUES (?,?,?,?);");
			Random r = new Random();
			int rand = r.nextInt((1000000-4) + 1) + 4;
			st.setInt(1,rand);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			st.setDate(2,Date.valueOf(dtf.format(now)));
			st.setString(3,"limited");
			st.setInt(4, Integer.valueOf(customer.getCustomerID()));
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		try {
			String SSN = customer.getCustomerID();
			System.out.println(SSN);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			PreparedStatement st = con.prepareStatement(
					"UPDATE mwcoulter.Customer SET Email = ?, Rating = ?, CreditCardNumber = ?  WHERE Id = ?");
			st.setString(1, customer.getEmail());
			st.setInt(2, (int)customer.getRating());
			st.setFloat(3, Float.valueOf(customer.getCreditCard()));
			st.setInt(4, Integer.valueOf(customer.getCustomerID()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"UPDATE mwcoulter.Person SET LastName = ?, FirstName = ?, Address = ?, ZipCode = ? WHERE SSN = ?");
			st.setString(1, customer.getLastName());
			st.setString(2, customer.getFirstName());
			st.setString(3, customer.getAddress());
			st.setInt(4, customer.getZipCode());
			st.setInt(5, Integer.valueOf(customer.getCustomerID()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"UPDATE mwcoulter.LivesAt SET ZipCode = ? WHERE SSN = ?");
			st.setInt(1, customer.getZipCode());
			st.setInt(2, Integer.valueOf(customer.getCustomerID()));
			st.executeUpdate();
			
			st = con.prepareStatement(
					"UPDATE mwcoulter.Location SET City = ?, State = ? WHERE ZipCode = ?");
			st.setString(1, customer.getCity());
			st.setString(2, customer.getState());
			st.setInt(3, customer.getZipCode());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";

	}

}
