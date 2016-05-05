package clustering.kMeans;

/* This class represents the Centroid for a Cluster. The initial   
centroid is calculated using a equation which divides the sample    
space for each dimension into equal parts depending on the value of k.
 */
class Centroid {
	private double mkW;
	private Cluster mCluster;

	public Centroid(double mkW) {
		this.mkW = mkW;
	}

	public void calcCentroid() { 
		//only called by CAInstance
		int numDP = mCluster.getNumDataPoints();
		double tempmkW = 0;
		int i;

		//------------caluclating the new Centroid-----

		//totaling
		for (i = 0; i < numDP; i++) 
			tempmkW = tempmkW + mCluster.getDataPoint(i).getmkW();

		this.mkW = tempmkW / numDP;


		//calculating the new Euclidean Distance for each Data Point

		tempmkW = 0;

		for (i = 0; i < numDP; i++)
			mCluster.getDataPoint(i).calcEuclideanDistance();

		//calculate the new Sum of Squares for the Cluster
		mCluster.calcSumOfSquares();
	}

	public void setCluster(Cluster c) { this.mCluster = c; }

	public double getCmkW() {return mkW;}

	public Cluster getCluster() { return mCluster; }
	
}
