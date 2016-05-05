/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapelets;

import clustering.kMeans.ClusteringDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ProcessShapelets {

    private List shapelets;
    private ArrayList D;
    private int k;

    public ProcessShapelets(List shapelets, ArrayList D, int k) {
        this.shapelets = shapelets;
        this.D = D;
        this.k = k;
    }

    public ArrayList clusterData() {
        ArrayList<Double> DIS = new ArrayList<Double>();                                      //distance map that contains all the distance vectors
       

        List<Double> CRI = new ArrayList<Double>();
        ArrayList<List> cls = new ArrayList<List>();
        ArrayList< ArrayList<Double>> disArray = new ArrayList<ArrayList<Double>>();
        // cls(0) ← c assign same cluster label to all the time series
        List temp = new ArrayList();
        for(int iterator = 0; iterator < D.size(); iterator++) 
            temp.add(1);
        cls.add(0,temp);
        CRI.add(Double.MAX_VALUE);
        CRI.add(Double.MAX_VALUE);
        ClusteringDriver clusteringDriver = new ClusteringDriver();

        Distance distance = new Distance();

        for (int i = 1; i <= shapelets.size(); i++) {
            List s = new ArrayList();
            s = (List) shapelets.get(i-1);
            System.err.println("Selecting Shapelet "+i+" for Clustering: "+s);
            ArrayList<Double> dis;
            dis = distance.computeDistance(s, D);
            //DIS.addAll(dis);
            int sumDis = Integer.MAX_VALUE;

            //clusteringDriver.addData(DIS);
            Vector dataPoints = new Vector();
            dataPoints = clusteringDriver.addData(dis);
            cls.add(i,clusteringDriver.startClusteringProcedure(k,dataPoints));
            disArray.add(dis);
            //[IDX, SUMD] ← k-means(DIS, k)    //kmeans ka function ko call karke kuch numbers paas karke dekh na
            //cls(cnt) ← IDX
            if(i>1)
                CRI.add(i, RandomIndex.calculateRandomIndex(cls.get(i-1), cls.get(i)));           //Null Pointer Exception cauz cls.get(0) = null;
        }
        
        double minCri = Double.MAX_VALUE;
        int index= 0;
        for(int iterator = 0; iterator < CRI.size(); iterator++) {
            if(CRI.get(iterator) < minCri) {
                minCri = CRI.get(iterator);
                index = iterator;
            }
        }
        ArrayList returnArray = new ArrayList();
        returnArray.add(disArray.get(index-1));         //1st position contains the distance matrix
        returnArray.add(cls.get(index));                //2nd position contains the best clustering labels
        
        return returnArray;
        //return cls.get(index);
    }

}
