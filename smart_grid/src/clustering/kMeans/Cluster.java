package clustering.kMeans;

import java.util.Vector;


/**
This class represents a Cluster in a Cluster Analysis Instance. A Cluster is associated with one and only one JCA Instance. A Cluster is related to more than one DataPoints and one centroid. 
*/

class Cluster {
    private int mMeterID;
    private Centroid mCentroid;
    private double mSumSqr;
    private Vector mDataPoints;
    
    public Cluster(int MeterID) {
        this.mMeterID = MeterID;
        this.mCentroid = null; //will be set by calling setCentroid()
        mDataPoints = new Vector();
    }
    public void setCentroid(Centroid c) {
        mCentroid = c;
    }
    public Centroid getCentroid() {
        return mCentroid;
    }
    public void addDataPoint(DataPoint dp) { //called from CAInstance
        dp.setCluster(this); //initiates a inner call to calcEuclideanDistance() in DP.
        this.mDataPoints.addElement(dp);
        calcSumOfSquares();
    }
    public void removeDataPoint(DataPoint dp) {
        this.mDataPoints.removeElement(dp);
        calcSumOfSquares();
    }
    public int getNumDataPoints() {
        return this.mDataPoints.size();
    }
    public DataPoint getDataPoint(int pos) {
        return (DataPoint) this.mDataPoints.elementAt(pos);
    }
    public void calcSumOfSquares() { //called from Centroid
        int size = this.mDataPoints.size();
        double temp = 0;
        for (int i = 0; i < size; i++) {
            temp = temp + ((DataPoint)
this.mDataPoints.elementAt(i)).getCurrentEuDt();
        }
        this.mSumSqr = temp;
    }
    public double getSumSqr() {
        return this.mSumSqr;
    }
    public int getmMeterID() {
        return this.mMeterID;
    }
    public Vector getDataPoints() {
        return this.mDataPoints;
    }
}