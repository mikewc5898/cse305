package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Movie;
import model.Account;

public class AccountDao {
	
	public int getSalesReport(Account account) {
			
			/*
			 * The students code to fetch data from the database will be written here
			 * Query to get sales report for a particular month must be implemented
			 * account, which has details about the month and year for which the sales report is to be generated, is given as method parameter
			 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the dateOpened attribute of account object
			 * The month and year can be accessed by getter method, i.e., account.getAcctCreateDate()
			 */
	
			int income = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
				Statement st = con.createStatement();
				st.executeUpdate(
						"CREATE OR REPLACE VIEW feetable AS " +
						"SELECT EXTRACT(YEAR_MONTH FROM O.RDate) AS Date, CASE "+
						"WHEN A.AcctType = 'limited' THEN 10 " +
						"WHEN A.AcctType = 'unlimited-1' THEN 15 " +
						"WHEN A.AcctType = 'unlimited-2' THEN 20 " +
						"ELSE 25 " +
						"END AS fee " +
						"FROM Orders O, Rental R, Account A " +
						"WHERE O.Id = R.OrderId AND R.AccountId = A.Id " +
						"group by EXTRACT(YEAR_MONTH FROM O.RDate), A.Id;");
				String date = account.getAcctCreateDate();
				String month = date.substring(0,date.indexOf("-"));
				String year = date.substring(date.indexOf("-") + 1,date.length());
				String yearMonth = year.concat(month);
				ResultSet rs = st.executeQuery(
						"SELECT F.Date, SUM(F.fee) AS Sum "+
						"FROM feetable F "+
						"WHERE F.Date =" + yearMonth +
						" group by F.Date;");
				while(rs.next()) {
					income += rs.getInt("Sum");
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}		
			
			
	
			return income;
			
		}
	
	public String setAccount(String customerID, String accountType) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/mwcoulter?useSSL=false","mwcoulter","111030721");
			PreparedStatement st = con.prepareStatement(
					"UPDATE mwcoulter.Account SET AcctType = ? WHERE Customer = ?");
			st.setString(1, accountType);
			st.setInt(2, Integer.valueOf(customerID));
			st.executeUpdate();
			
			
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";

	}
	

}
