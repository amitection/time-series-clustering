package clustering.kMeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

class Main {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/timeseries";

	static final String USER = "root";
	static final String PASS = "";

	
	
	public static void main (String args[]){
		Vector dataPoints = new Vector();

		//dataPoints.add(new DataPoint(meterID,mkW));    TEMPLATE

		Connection conn = null;
		Statement stmt;
		ResultSet rs;

		try {
			//STEP 1: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sqlStmt = "SELECT * FROM datainfo WHERE DATE_TIME = 19917 ORDER BY METER_ID LIMIT 200";
			
			rs = stmt.executeQuery(sqlStmt);
			
			System.out.println("QUERY SUCCESSFUL");
			int meterID;
			double kW;
			while(rs.next())
			{
				
				meterID = rs.getInt("METER_ID");
				kW = rs.getDouble("KW");
				
				dataPoints.add(new DataPoint(meterID, kW));  //add retrieved results from SQL Query into dataPoints
				
			}
				
			System.out.println("DATA POINTS INSERTED");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		JCA jca = new JCA(20,10000,dataPoints);
		jca.startAnalysis();
		
		Vector[] v = jca.getClusterOutput();
		
		for (int i=0; i<v.length; i++){
			Vector tempV = v[i];
			System.out.println("-----------Cluster"+i+"---------");
			Iterator iter = tempV.iterator();
			while(iter.hasNext()){
				DataPoint dpTemp = (DataPoint)iter.next();
				System.out.println(dpTemp.getMeterID()+"["+dpTemp.getmkW()+"]");
			}
		}
	}
}
