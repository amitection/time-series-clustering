/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering.kMeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ClusteringDriver {

    Vector dataPoints = new Vector();
    int k;

    public Vector addData(ArrayList<Double> DIS) {
        Vector dataPoints = new Vector();
        
        for(int iterator = 0; iterator < DIS.size(); iterator++) {
            double val = DIS.get(iterator);
            dataPoints.add(new DataPoint(iterator, val));
        }
            

        System.out.println("DATA POINTS INSERTED INTO KMEANS");
        return dataPoints;
    }

    public List startClusteringProcedure(int k,Vector dataPoints) {
        JCA jca = new JCA(k, 20, dataPoints);
        jca.startAnalysis();

        Vector[] v = jca.getClusterOutput();

        /*for (int i = 0; i < v.length; i++) {
            Vector tempV = v[i];
            System.out.println("-----------Cluster" + i + "---------");
            Iterator iter = tempV.iterator();
            while (iter.hasNext()) {
                DataPoint dpTemp = (DataPoint) iter.next();
                System.out.println("10"+dpTemp.getMeterID() + "[" + dpTemp.getmkW() + "]");
            }
        } */

        //List cls = new ArrayList();
        //Initializing cls with 0
        int iterator;
        
        int[] cls = new int[dataPoints.size()];
        
        for(iterator = 0; iterator < dataPoints.size();  iterator++ )
            cls[iterator] = 0;
        
        int cnt=0;
        for (int i = 0; i < v.length; i++) {
            Vector tempV = v[i];

            Iterator iter = tempV.iterator();
            while (iter.hasNext()) {
                DataPoint dpTemp = (DataPoint) iter.next();
                int loc = dpTemp.getMeterID();
                //System.err.println(loc+"-->"+i);
                
                //cls.add(loc,i);
                cls[dpTemp.getMeterID()] = i;
                cnt++;
            }

        }
        
        
        System.out.println("cnt-->>"+cnt);
        
        List clusterLabels = new ArrayList();
        for(int i=0; i<cls.length;i++)
            clusterLabels.add(cls[i]);
        
        return clusterLabels;
    }
}
