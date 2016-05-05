package shapelets;

import smart_grid.Meter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shapelets {

    private ArrayList<Meter> D = new ArrayList<Meter>();

    public ArrayList<List<Double>> extractUShaplets(ArrayList<Meter> DATASET, int sLen, int k) {
        ArrayList<List<Double>> S = new ArrayList<List<Double>>();
        this.D.addAll(DATASET);
        //ArrayList<List<Double>> subSequences = new ArrayList<List<Double>>();
        Date date = new Date();
        ArrayList<TimeSeriesMarker> shapeletTimeSeriesMarker = new ArrayList<TimeSeriesMarker>();

        ArrayList<Double> ts = D.get(0).kW;
              //  System.out.println("first ts : "+ts);

        Distance distance = new Distance();
        int h = 0;
        System.err.println("Extracting Unsupervised Shapelets...");
        while (true) {
            int cnt = 0;    //to count the candidate U-Shapelets from ts

            ArrayList subSequences = new ArrayList();

            ArrayList<Double> gap = new ArrayList<Double>();
            ArrayList<Double> dt = new ArrayList<Double>();
            ArrayList<TimeSeriesMarker> tsm = new ArrayList<TimeSeriesMarker>();
            
            for (int sl = 2; sl <= sLen; sl++) //each U-Shapelet Length
            {
                System.out.println(date.toString() + ":Inside For: sl = " + sl);
                int tsSize = ts.size();
                for (int i = 0; i < (ts.size() - sl + 1); i++) //Each subSequence from ts
                {
                    int end = (i + sl) - 1;

                    List<Double> subList = new ArrayList<Double>(ts.subList(i, end));

                    subSequences.add(cnt, subList);	//a subSequence of length sl     //unable to insert element in subSequence

                    Double[] gapResults = Gap.comuputeGap((List<Double>) subSequences.get(cnt), D, k);

                    gap.add(cnt, gapResults[0]); //[gap(cnt+1), dt(cnt+1)] = <-computeGap(s(cnt),D)
                    dt.add(cnt, gapResults[1]); //[gap(cnt+1), dt(cnt+1)] = <-computeGap(s(cnt),D)
                    tsm.add(cnt,new TimeSeriesMarker(i, end, subList)); ////Adding start and end time of sublist in a arraylist along with sublist
                    cnt++;
                }
            }

            //Index for the maximum gap
            //int index1=-1;
            int index1 = 0;
            double maxGap = 0.0;
            for (int i = 0; i < gap.size(); i++) {
                if (gap.get(i) >= maxGap) {
                    maxGap = gap.get(i);
                    index1 = i;
                }
            }

            //@SuppressWarnings("unused")
            int gapSize = gap.size();
            //@SuppressWarnings("unused")
            int subSeqSize = subSequences.size();

            S.add((List<Double>) subSequences.get(index1)); //adding the U-Shapelet with maximum gap score
            
            //Adding start and end time of shapelet in a arraylist along with shapelet
            shapeletTimeSeriesMarker.add(new TimeSeriesMarker(tsm.get(index1).getStartTime(), tsm.get(index1).getEndTime(), tsm.get(index1).getShaplet()));
            
            ArrayList<Double> dis = distance.computeDistance((List<Double>) subSequences.get(index1), D);

            ArrayList<Double> Da = new ArrayList<Double>(); //Finding the points to the left of dt
            int iterator = 0;
            //double dtval = dt.get(index1);

            for (int i = 0; i < dis.size(); i++) {
                if (dis.get(i) < dt.get(index1)) {
                    Da.add(dis.get(i));
                }
            }

//			/*while(dis.get(iterator) < dt.get(index1) && iterator <= dt.size())
//			{
//				Da.add(dis.get(iterator));
//				iterator++;
//			}*/
//
            if (Da.size() == 1) //Stopping Criteria.. when there will be only one differentiable shapelet
            {
                break;
            } else {

                //System.out.println("Size of Da: "+Da.size());
                int index2 = 0;
                double maxDis = 0;

                for (int i = 0; i < dis.size(); i++) //To find the index2 for max(dis)
                {
                    if (dis.get(i) > maxDis) {
                        maxDis = dis.get(i);
                        index2 = i;
                    }
                }

                ts = new ArrayList();
                ts = D.get(index2).kW; //Extracting the next U-Shapelet
                System.out.println(date.toString() + ":Time Series under consideration Index: " + index2);
                //Calculating Threshold to Remove time series that have sdists < threshold
                double threshold = Gap.mean(Da) + Gap.stdDeviation(Da);

                if (Double.isNaN(threshold)) {
                    threshold = 0.0;
                }

				//required so that Da = 1 sometime - stopping condition
//
                for (int i = 0; i < dis.size(); i++) {
                    if (dis.get(i) < threshold) {
                        System.out.println("------------------------- " + i);

                        System.out.println("Removing: " + D.get(i).getMeterID());
                        D.remove(i);
                        dis.remove(i);

                        i = i - 1;
                    }
                }

            }
        }

        System.out.println("\nDisplaying the set of U-Shapelets");
        int iterator = 0;
        for (List<Double> i : S) {
            System.err.println("Shapelet " + (++iterator) + ": ");
            for (int j = 0; j < i.size(); j++) {
                System.out.print((i.get(j).toString()) + "-");
            }
            System.out.println();
        }
        
        for(int i =0; i<shapeletTimeSeriesMarker.size();i++)
            System.err.println(i+" :\nStart Time: "+(shapeletTimeSeriesMarker.get(i).getStartTime())+"\n Shapelet: "+(shapeletTimeSeriesMarker.get(i).getShaplet())+"\n EndTime: "+(shapeletTimeSeriesMarker.get(i).getEndTime()));
        
        return S;
    }

}
