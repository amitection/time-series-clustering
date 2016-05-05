package clustering.kMeans;

import java.util.Vector;
import java.util.Iterator;

/**
This class is the entry point for constructing Cluster Analysis objects. Each instance of JCA object is associated with one or more clusters, and a Vector of DataPoint objects. The JCA and DataPoint classes are the only classes available from other packages. 
**/
class JCA {
    private Cluster[] clusters;
    private int miter;
    private Vector mDataPoints = new Vector();
    private double mSWCSS;
    
    public JCA(int k, int iter, Vector dataPoints) {
        clusters = new Cluster[k];
        for (int i = 0; i < k; i++) {
            clusters[i] = new Cluster(i);
        }
        this.miter = iter;
        this.mDataPoints = dataPoints;
    }
    
    private void calcSWCSS() {
        double temp = 0;
        for (int i = 0; i < clusters.length; i++) {
            temp = temp + clusters[i].getSumSqr();
        }
        mSWCSS = temp;
    }
    
    public void startAnalysis() {
        //set Starting centroid positions - Start of Step 1
        setInitialCentroids();
        int n = 0;
        //assign DataPoint to clusters
        loop1: while (true) {
            for (int l = 0; l < clusters.length; l++) 
            {
                clusters[l].addDataPoint((DataPoint)mDataPoints.elementAt(n));
                n++;
                if (n >= mDataPoints.size())
                    break loop1;
            }
        }
        //calculate E for all the clusters
        calcSWCSS();
        //recalculate Cluster centroids - Start of Step 2
        for (int i = 0; i < clusters.length; i++) {
            clusters[i].getCentroid().calcCentroid();
        }
        //recalculate E for all the clusters
        calcSWCSS();
        for (int i = 0; i < miter; i++) {
            //enter the loop for cluster 1
            for (int j = 0; j < clusters.length; j++) {
            for (int k = 0; k < clusters[j].getNumDataPoints(); k++) {
            //pick the first element of the first cluster
            //get the current Euclidean distance
             double tempEuDt = clusters[j].getDataPoint(k).
getCurrentEuDt();
             Cluster tempCluster = null;
             boolean matchFoundFlag = false;
             //call testEuclidean distance for all clusters
             for (int l = 0; l < clusters.length; l++) {
             //if testEuclidean < currentEuclidean then
             if (tempEuDt > clusters[j].getDataPoint(k).
             testEuclideanDistance(clusters[l].getCentroid())) {
              tempEuDt = clusters[j].getDataPoint(k).
              testEuclideanDistance(clusters[l].getCentroid());
              tempCluster = clusters[l];
              matchFoundFlag = true;
             }
        //if statement - Check whether the Last EuDt is > Present EuDt 
                        }
//for variable 'l' - Looping between different Clusters for matching a Data Point.
//add DataPoint to the cluster and calcSWCSS
       if (matchFoundFlag) {
		tempCluster.addDataPoint(clusters[j].getDataPoint(k));
		clusters[j].removeDataPoint(clusters[j].getDataPoint(k));
                        for (int m = 0; m < clusters.length; m++) {
                            clusters[m].getCentroid().calcCentroid();
                        }
//for variable 'm' - Recalculating centroids for all Clusters
                        calcSWCSS();
                    }
//if statement - A Data Point is eligible for transfer between Clusters.
                }
                //for variable 'k' - Looping through all Data Points of the current Cluster.
            }//for variable 'j' - Looping through all the Clusters.
        }//for variable 'i' - Number of iterations.
    }
    public Vector[] getClusterOutput() {
        Vector v[] = new Vector[clusters.length];
        for (int i = 0; i < clusters.length; i++) {
            v[i] = clusters[i].getDataPoints();
        }
        return v;
    }
    private void setInitialCentroids() {
        //kn = (round((max-min)/k)*n)+min where n is from 0 to (k-1).
        
    	double ckW = 0;
        
        for (int n = 1; n <= clusters.length; n++) {
        		ckW = (((getMaxkWValue() - getMinkWValue()) / (clusters.length + 1)) * n) + getMinkWValue();
        		Centroid c1 = new Centroid(ckW);
                clusters[n - 1].setCentroid(c1);
                c1.setCluster(clusters[n - 1]);
        }
    }
    
    private double getMaxkWValue() {
        double temp;
        temp = ((DataPoint) mDataPoints.elementAt(0)).getmkW();
        for (int i = 0; i < mDataPoints.size(); i++) {
            DataPoint dp = (DataPoint) mDataPoints.elementAt(i);
            temp = (dp.getmkW() > temp) ? dp.getmkW() : temp;
        }
        return temp;
    }
    
    private double getMinkWValue() {
        double temp = 0;
        temp = ((DataPoint) mDataPoints.elementAt(0)).getmkW();
        for (int i = 0; i < mDataPoints.size(); i++) {
            DataPoint dp = (DataPoint) mDataPoints.elementAt(i);
            temp = (dp.getmkW() < temp) ? dp.getmkW() : temp;
        }
        return temp;
    }
    
    
    public int getKValue() {
        return clusters.length;
    }
    public int getIterations() {
        return miter;
    }
    public int getTotalDataPoints() {
        return mDataPoints.size();
    }
    public double getSWCSS() {
        return mSWCSS;
    }
    public Cluster getCluster(int pos) {
        return clusters[pos];
    }
}
