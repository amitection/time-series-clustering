/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_grid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import shapelets.ProcessShapelets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.ui.RefineryUtilities;
import render.ScatterPlot;
import render.TimeSeriesAWT;
import render.XYClusterDataset;
import shapelets.ClusteringAnalysis;
import shapelets.Shapelets;

/**
 *
 * @author Innovatus
 */
public class OperationStarter {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/timeseries";

    static final String USER = "root";
    static final String PASS = "";
    public static List shaplets = new ArrayList();

    Scanner io = new Scanner(System.in);
    
    public void startOperation() {
        Connection conn = null;

        Statement stmt;
        ResultSet rs;
        Date date = new Date();

        ArrayList<Meter> D = new ArrayList<Meter>();

        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connected to Database!");

            stmt = conn.createStatement();
            //String sqlStmt="SELECT METER_ID,KW FROM FILE1_DATA WHERE METER_ID < 1200 ORDER BY METER_ID,DATE_TIME";
            String sqlStmt = "SELECT METER_ID,KW FROM datainfo WHERE DATE_TIME < 22501 AND METER_ID < 1200 ORDER BY METER_ID,DATE_TIME";

            System.out.println(date.toString() + ":QUERY CREATED");

            rs = stmt.executeQuery(sqlStmt);

            System.out.println(date.toString() + ":QUERY SUCCESSFUL");

            int newMeterID, oldMeterID;
            double consumption;

            //Adding the queried data to the dataset D
            System.out.println("\nCreating Data Points...");
            rs.next();
            oldMeterID = rs.getInt("METER_ID");
            consumption = rs.getDouble("KW");

            D.add(new Meter(oldMeterID, consumption));

            while (rs.next()) {
                newMeterID = rs.getInt("METER_ID");
                consumption = rs.getDouble("KW");

                if (newMeterID == oldMeterID) {
                    D.get(D.size() - 1).setkW(consumption);		//adding the new consumption to the oldMeterID
                } else {
                    D.add(new Meter(newMeterID, consumption));	//If new meterId found then create a new object
                }												//for meter and add it to the arraylist D

                oldMeterID = newMeterID;
            }

            System.out.println(date.toString() + ":Data Points Inserted... ");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        //######### TITLE #########
        try {
            BufferedReader in = new BufferedReader(new FileReader("title.txt"));
            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(OperationStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        //#########################
        int sLen = 0, k = 4;
        System.out.println("Enter the subsequence range to be searced for(04-15)");
        while(true){
            sLen = io.nextInt();
            if(sLen >=4 && sLen <=15) 
                break;
            System.err.println("Enter a value between 4 to 15: ");
        }

        shapelets.Shapelets sh = new Shapelets();
        List st = sh.extractUShaplets(D, sLen, k);

        shaplets = st;

//        for (int i = 0; i < st.size(); i++) {
//            List s = (List) st.get(i);
//
//            System.out.println(s);
//        }
//        SelectShapletFrame sp = new SelectShapletFrame();
//        sp.setVisible(true);
//        sp.setLocation(420, 150);
//        sp.setSize(600, 300);

        //Drawing Shapelets
        final String frameTitle = "Time Series Analysis";
        TimeSeriesAWT timeSeriesAWT = new TimeSeriesAWT(frameTitle, shaplets);
        timeSeriesAWT.pack();
        RefineryUtilities.positionFrameRandomly(timeSeriesAWT);
        timeSeriesAWT.setVisible(true);

        //#################################
        ProcessShapelets processShapelets = new ProcessShapelets(shaplets, D, k);
        List bestClusteringLabels = new ArrayList();
        ArrayList clusteringResults = new ArrayList();
        ArrayList disMatrix = new ArrayList();
        
        clusteringResults  = processShapelets.clusterData();
        disMatrix = (ArrayList) clusteringResults.get(0);
        bestClusteringLabels = (List) clusteringResults.get(1);
        //bestClusteringLabels = processShapelets.clusterData();

        System.out.println("Best Clustering Labels: " + bestClusteringLabels);

        List cluster1 = new ArrayList();
        List cluster2 = new ArrayList();
        List cluster3 = new ArrayList();
        List cluster4 = new ArrayList();

        for (int i = 0; i < bestClusteringLabels.size(); i++) {
            int ch = (Integer) bestClusteringLabels.get(i);
            switch (ch) {
                case 0:
                    cluster1.add(i + 1000);
                    break;
                case 1:
                    cluster2.add(i + 1000);
                    break;
                case 2:
                    cluster3.add(i + 1000);
                    break;
                case 3:
                    cluster4.add(i + 1000);
            }
        }

        System.out.println("Cluster1: " + cluster1);
        System.out.println("Cluster2: " + cluster2);
        System.out.println("Cluster3: " + cluster3);
        System.out.println("Cluster4: " + cluster4);
        
        ScatterPlot scatterPlot = new ScatterPlot("Cluster Plot", disMatrix, bestClusteringLabels);
        scatterPlot.pack();
        RefineryUtilities.centerFrameOnScreen(scatterPlot);
        scatterPlot.setVisible(true);
        
        ArrayList clusters = new ArrayList();
        clusters.add(cluster1);
        clusters.add(cluster2);
        clusters.add(cluster3);
        clusters.add(cluster4);
        
        ClusteringAnalysis clusteringAnalysis = new ClusteringAnalysis(D,clusters);
    }
}
