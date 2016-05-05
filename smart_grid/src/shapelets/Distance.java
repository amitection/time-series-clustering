package shapelets;

import smart_grid.Meter;
import java.util.ArrayList;
import java.util.List;


public class Distance {

	//Takes as input a single subSequence and the dataset D
	//Returns distances from all the time series in the dataset
	
	public ArrayList<Double> computeDistance(List<Double> subSequence,ArrayList<Meter> D)
	{
		ArrayList<Double> dis = new ArrayList<Double>();
		ArrayList<Double> normalizedSubSequence = null;
		normalizedSubSequence = zNormalize(subSequence); 
		
		
		for(int i=0; i<D.size(); i++)
		{
			ArrayList<Double> ts = new ArrayList<Double>(D.get(i).kW);
			//System.out.println(D.get(i).getMeterID());
			//System.out.println(D.get(i).kW);
			dis.add(i,Double.MAX_VALUE);
		
			for(int j = 0; j< (ts.size() -normalizedSubSequence.size()+1);j++)
			{
				if( j >= normalizedSubSequence.size())
					break;
				
				List<Double> subList = new ArrayList<Double>(ts.subList(j, normalizedSubSequence.size()));
				ArrayList<Double> z = zNormalize(subList);
				
				Double d = euclideanDistance(z,normalizedSubSequence); //Scope to reduce the time by calculating 
															//the time as normalizedSubsequence is not changing
				dis.set(i, min(d,dis.get(i)));
			}
		}
		
		
		//Computing the distance from all the time series in the data set
		for(int i = 0;i<dis.size();i++)
			dis.set(i, dis.get(i)/Math.sqrt(normalizedSubSequence.size()));
		
		return dis;
	}

	private ArrayList<Double> zNormalize(List<Double> subList) {
		// TODO Auto-generated method stub
		//a z-score is the number of standard deviations from the mean a data point is.
		
		ArrayList<Double> normalizedList = new ArrayList<Double>();
		Double summationSubList = 0.0,summationSquaredSubList=0.0;
		Double meanSubList = 0.0, stdDeviationSubList = 0.0;
		//The basic z score formula for a sample is:z = (x – μ) / σ
		
		for(int i=0;i<subList.size();i++)
		{
			summationSubList += subList.get(i);
			summationSquaredSubList += Math.pow(subList.get(i), 2);
		}
		
		meanSubList = summationSubList/subList.size();
		stdDeviationSubList = (summationSquaredSubList/subList.size()) - Math.pow(meanSubList, 2);
		
		for(int i = 0; i<subList.size();i++)
		{
			double intermediate = (subList.get(i) - meanSubList)/stdDeviationSubList;
			
			if(Double.isNaN(intermediate))
				intermediate = 0.0;
			
			normalizedList.add(intermediate);
		}
		
		/*
		for(int i=0;i<subList.size();i++)
		{
			summationSubList += subList.get(i);
		}
		
		meanSubList = summationSubList/subList.size();
		
		for(int i = 0; i<subList.size();i++)
		{
			summationSquaredSubList += Math.pow((subList.get(i) - meanSubList),2);
		}
		
		stdDeviationSubList = Math.sqrt(summationSquaredSubList/(subList.size()-1));
		
		for(int i = 0; i<subList.size();i++)
		{
			normalizedList.add((subList.get(i) - meanSubList) / stdDeviationSubList); 
		}
		*/
		
		return normalizedList;
	}

	private Double euclideanDistance(ArrayList<Double> TX, ArrayList<Double> TY) {
		// TODO Auto-generated method stub
		
		Double summationTX=0.0,summationTY=0.0;		
		Double summationSquaredTX=0.0,summationSquaredTY=0.0;
		Double summationProduct=0.0;
		Double meanTX=0.0, meanTY =0.0;
		Double stdDeviationTX=0.0,stdDeviationTY=0.0;
		
		int n = TX.size();
		
		//Caching Results to reduce time
		for(int i=0;i<TX.size();i++)
		{
			summationTX += TX.get(i);
			summationTY += TY.get(i);
			summationSquaredTX += Math.pow(TX.get(i), 2);
			summationSquaredTY += Math.pow(TY.get(i), 2);
			summationProduct +=(TX.get(i)*TY.get(i));
		}
		
		meanTX = summationTX/n;
		meanTY = summationTY/n;
		
		stdDeviationTX = (summationSquaredTX/n) - Math.pow(meanTX, 2);
		stdDeviationTY = (summationSquaredTY/n) - Math.pow(meanTY, 2);
		
		//Calculating the correlation
		Double correlation = (summationProduct - (n*meanTX*meanTY)) / (n*stdDeviationTX*stdDeviationTY);
		
		if(Double.isNaN(correlation))
			correlation = 0.0;
		//Calculating Euclidean Distance on basis of Correlation
		
		Double euclidean = Math.sqrt((2*(1-correlation)));
		
		return euclidean;
	}

	private Double min(Double d1, Double d2) {
		// TODO Auto-generated method stub
		return d1<d2 ? d1:d2;
	}
	

}
