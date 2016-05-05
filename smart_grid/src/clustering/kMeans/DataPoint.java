package clustering.kMeans;

import java.util.Iterator;
import java.util.Vector;

/**
   This class represents a candidate for Cluster analysis. A candidate 
   must have a name and two independent variables on the basis of 
   which it is to be clustered.A Data Point must have two variables 
   and a name. A Vector of  Data Point object is fed into the 
   constructor of the JCA class. JCA and DataPoint are the only
   classes which may be available from other packages.
 */

class DataPoint {
    private double mkW;
    private int mMeterID;
    private Cluster mCluster;
    private double mEuDt;
    
    public DataPoint(int mMeterID, double mkW) {
    	this.mMeterID = mMeterID;
        this.mkW = mkW;
        this.mCluster = null;
    }
    public void setCluster(Cluster cluster) {
        this.mCluster = cluster;
        calcEuclideanDistance();
    }
    public void calcEuclideanDistance() { 
    
    //called when DP is added to a cluster or when a Centroid is recalculated.
        mEuDt = Math.sqrt(Math.pow((mkW - mCluster.getCentroid().getCmkW()),
2));
    }
    public double testEuclideanDistance(Centroid c) {
        return Math.sqrt(Math.pow((mkW - c.getCmkW()), 2));
    }
    public double getmkW() {
        return mkW;
    }
    
    public Cluster getCluster() {
        return mCluster;
    }
    public double getCurrentEuDt() {
        return mEuDt;
    }
    public int getMeterID() {
        return mMeterID;
    }
}
