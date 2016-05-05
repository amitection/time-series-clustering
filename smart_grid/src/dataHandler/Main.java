/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

/**
 *
 * @author amit
 */

public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/ENERGY1";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sqlStmt;
            
            //Selecting TSV File to be Read
            
            BufferedReader file = (new ReadFile("/home/amit/BE_PROJECT/ElecData/Files/File1.txt")).getFile();
            String line,meter_id;
            String []arr; 
            ResultSet rs;
            int flag=0;
            
            while((line = file.readLine()) != null)
            {
            	arr = line.split(" ");
            	meter_id = arr[0];
            	
            	
            	sqlStmt = "SELECT * FROM METERS WHERE METER_ID ="+meter_id;
            	rs =stmt.executeQuery(sqlStmt);
            	            	   	
            	System.out.println("SELECT Stament Executed");
            	
            	if(!rs.next())   //if meter_id not present then add it to the METERS table
             	{
            		/*
            		//for updating the previous with 288 (from observations)
            		rs = null;
            		sqlStmt = "UPDATE METERS SET NO_OF_READINGS = NO_OF_READINGS + 1 WHERE METER_ID = "+meter_id;
                	flag =stmt.executeUpdate(sqlStmt);
                	if(flag>0)
                		System.out.println("Updated Successfull");
                	else
                		System.out.println("Updated Unsucessfull");
                	
                	flag = 0;
                	*/
                	//------------for inserting newly found
            		rs = null;
            		sqlStmt = "INSERT INTO METERS VALUES("+meter_id+",1);";
                	flag =stmt.executeUpdate(sqlStmt);
                	
                	if(flag>0)
                		System.out.println("Insert Successfull");
                	else
                		System.out.println("Insert Unsucessfull");
                	
                	flag = 0;
            	}
            	else
            	{
            		//increment count in the readings
            		rs = null;
            		sqlStmt = "UPDATE METERS SET NO_OF_READINGS = NO_OF_READINGS + 1 WHERE METER_ID = "+meter_id;
                	flag =stmt.executeUpdate(sqlStmt);
                	if(flag>0)
                		System.out.println("Update Successfull");
                	else
                		System.out.println("Update Unsucessfull");
                	
                	flag = 0;
            	}
         		
            	//rs.close();
            	
            }
            
            

            //STEP 6: Clean-up environment
            
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
