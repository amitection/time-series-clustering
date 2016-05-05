package shapelets;

import smart_grid.Meter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gap {

	static Distance distance = new Distance();
	
	
	//Takes as input a candidate u-shapelet and the DataSet D
	//Returns the maximum gap score and dt
	//
	public static Double[] comuputeGap(List<Double> subSequence, ArrayList<Meter> D,int k)
	{
		ArrayList<Double> Da = new ArrayList<Double>();
		ArrayList<Double> Db = new ArrayList<Double>();
		double meanDa,meanDb, stdDeviationDa,stdDeviationDb;
		
		ArrayList<Double> dis = distance.computeDistance(subSequence,D);
		Collections.sort(dis);
		
		double maxGap = 0;
		double dt = 0,d = 0;
		
		//System.out.println(D.get(0).getMeterID());
		//System.out.println(D.get(0).kW);
		
		for(int l = 0; l < dis.size()-2;l++)		//all possible locations of dt
		{
			double m = dis.get(l);
			double n = dis.get(l+1);
			d = (dis.get(l)+dis.get(l+1))/2;
		
			int i = 0;
			
			Da.clear(); //Doubt about this
			Db.clear(); //Doubt about this
			
			while(dis.get(i)<d)
			{
				Da.add(dis.get(i));
				i++;

				if(i>=dis.size())
					break;
			}
			
			while(dis.get(i)>d)
			{
				Db.add(dis.get(i));
				i++;
				if(i>=dis.size())
					break;
			}
			
			int daSize = Da.size();
			int dbSize = Db.size();
			Double ratio;
			try{
				ratio = ((double)Da.size())/Db.size();
		
			}catch(ArithmeticException e)
			{
				ratio = 0.0;
			}
			
			
			if((1.0/k)<ratio && ratio < (1.0 - (1.0/k)))
			{
			
				meanDa = mean(Da);
				meanDb = mean(Db);
				stdDeviationDa = stdDeviation(Da);
				stdDeviationDb = stdDeviation(Db);
				
				Double gap = meanDb -stdDeviationDb - (meanDa + stdDeviationDa);
				
				if( gap > maxGap)
				{
					maxGap = gap;
					dt = d;
				}
			}
			
		}
		
		Double[] returnArray= new Double[2];
		returnArray[0] = maxGap;
		returnArray[1] = dt;
		 return returnArray;
	}
	
	
	public static double mean(ArrayList<Double> data)
	{
		Double summation = 0.0;
		for(int i=0;i<data.size();i++)
			summation += data.get(i);
		
		return summation/data.size();
	}
	
	public static double stdDeviation(ArrayList<Double> data)
	{
		Double summationSquaredData = 0.0, stdDeviationData = 0.0; 
		//double mean = mean(data);
		
		for(int i=0;i<data.size();i++)
		{
			//summationSquaredData += Math.pow(data.get(i) - mean,2);
			summationSquaredData += Math.pow(data.get(i), 2);
		}
		//stdDeviationData = Math.sqrt((summationSquaredData/data.size()));
		stdDeviationData = (summationSquaredData/data.size()) - Math.pow(mean(data), 2);
		
		return stdDeviationData;
	}
}
