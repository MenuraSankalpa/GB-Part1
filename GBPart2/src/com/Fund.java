package com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Fund {
	
	/*
	 *A common method to connect to the DB
	 * */
		private Connection connect(){
			
			Connection con = null;
			
			try{
				Class.forName("com.mysql.jdbc.Driver"); 
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fundingservice", "root", "admin");
			}catch (Exception e){
				e.printStackTrace();
			}
				return con;
		}
		
		
		
		
		
		public String insertFund(String title, String desc, String price, String name,  String date) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into fund(`fId`,`fTitle`,`fDesc`,`fPrice`,`fuName`, `date` )"
		 		+ " values (?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, title); 
		 preparedStmt.setString(3, desc); 
		 preparedStmt.setDouble(4, Double.parseDouble(price)); 
		 preparedStmt.setString(5, name); 
		 preparedStmt.setString(6, date);
		// execute the statement
		 preparedStmt.execute(); 
		 con.close();
		 
		 String newfunds = readFunds(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
				 newfunds + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the fund.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 


		public String readFunds() 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Fund ID</th><th>Fund Title</th><th>Fund Description</th>" +
		 "<th>Fund Price</th>" + 
		 "<th>Funder Name</th>" +
		 "<th>Date</th>";

		 
		 String query = "select * from fund"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String fId = Integer.toString(rs.getInt("fId")); 
		 String fTitle = rs.getString("fTitle"); 
		 String fDesc = rs.getString("fDesc"); 
		 String fPrice = Double.toString(rs.getDouble("fPrice")); 
		 String fuName = rs.getString("fuName"); 
		 String date = rs.getString("date"); 
		 // Add into the html table
		 
		 output += "<tr><td>" + fId + "</td>";
		 output += "<td>" + fTitle + "</td>"; 
		 output += "<td>" + fDesc + "</td>"; 
		 output += "<td>" + fPrice + "</td>"; 
		 output += "<td>" + fuName + "</td>";
		 output += "<td>" + date + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='index.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='fId' type='hidden' value='" +fId 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the funds."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 


		public String updateFund(String fId, String fTitle, String fDesc, String fPrice, String fuName, String date)
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE fund SET fTitle=?,fDesc=?,fPrice=?,fuName=?, date=?  WHERE fId=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, fTitle); 
			 preparedStmt.setString(2, fDesc); 
			 preparedStmt.setDouble(3, Double.parseDouble(fPrice)); 
			 preparedStmt.setString(4, fuName); 
			 preparedStmt.setString(5, date);
			 preparedStmt.setInt(6, Integer.parseInt(fId)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newFunds = readFunds(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newFunds + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 

			public String deleteFund(String fId) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from fund where fId=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(fId)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newFunds = readFunds(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newFunds + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\":	 \"Error while deleting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
		}