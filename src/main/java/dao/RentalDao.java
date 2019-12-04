package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Rental;

public class RentalDao {
	
	public List<Rental> getOrderHisroty(String customerID) {
		
		List<Rental> rentals = new ArrayList<Rental>();
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT R.* " + 
					"FROM  Rental R, Account A " + 
					"WHERE A.Id=R.AccountId AND A.Customer="+Integer.valueOf(customerID));
			while(rs.next()) {
				Rental rental = new Rental();
				rental.setOrderID(rs.getInt("OrderId"));
				rental.setMovieID(rs.getInt("MovieId"));
				rental.setCustomerRepID(rs.getInt("CustRepId"));
				rentals.add(rental);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
						
		return rentals;
		
	}

}
