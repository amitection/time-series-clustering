/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author hp
 */
public class XYClusterDataset extends AbstractXYDataset implements XYDataset, DomainInfo, RangeInfo{

    ArrayList disMatrix = new ArrayList();
    List bestClusteringLabels = new ArrayList();
    
    private int seriesCount;
    private int itemCount;
    private Double[][] xValues;
    private Double[][] yValues;
    
    /** The minimum domain value. */
    private Number domainMin;

    /** The maximum domain value. */
    private Number domainMax;

    /** The minimum range value. */
    private Number rangeMin;

    /** The maximum range value. */
    private Number rangeMax;

    /** The range of the domain. */
    private Range domainRange;

    /** The range. */
    private Range range;
    
    public XYClusterDataset(ArrayList disMatrix, List bestClusteringLabels) {
        this.disMatrix = disMatrix;
        this.bestClusteringLabels = bestClusteringLabels;
        this.seriesCount = 4;
        this.itemCount = disMatrix.size();
        
        this.xValues = new Double[this.seriesCount][this.itemCount];
        this.yValues = new Double[this.seriesCount][this.itemCount];
        
        double minX = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        
            int inc0,inc1,inc2,inc3;
            inc0=inc1=inc2=inc3=0;
            for (int item = 0; item < itemCount; item++) {
                
                if ((Double) disMatrix.get(item) < minX) {
                    minX = (Double) disMatrix.get(item);
                    minY = (Double) disMatrix.get(item);
                }
                if ((Double) disMatrix.get(item) > maxX) {
                    maxX = (Double) disMatrix.get(item);
                    maxY = (Double) disMatrix.get(item);
                }
                        
                int ch = (Integer)bestClusteringLabels.get(item);
                switch(ch) {
                    case 0:
                        this.xValues[0][inc0] = (Double) disMatrix.get(item);
                        this.yValues[0][inc0] = (Double) disMatrix.get(item);
                        inc0++;
                        break;
                    case 1:
                        this.xValues[1][inc1] = (Double) disMatrix.get(item);
                        this.yValues[1][inc1] = (Double) disMatrix.get(item);
                        inc1++;
                        break;
                    case 2:
                        this.xValues[2][inc2] = (Double) disMatrix.get(item);
                        this.yValues[2][inc2] = (Double) disMatrix.get(item);
                        inc2++;
                        break;
                    case 3:
                        this.xValues[3][inc3] = (Double) disMatrix.get(item);
                        this.yValues[3][inc3] = (Double) disMatrix.get(item);
                        inc3++;
                }
            }
            
        this.domainMin = minX;
        this.domainMax = maxX;
        this.domainRange = new Range(minX, maxX);

        this.rangeMin = minY;
        this.rangeMax = maxY;
        this.range = new Range(minY, maxY);
    }
    
    @Override
    public int getSeriesCount() {
        return this.seriesCount;
    }

    @Override
    public Comparable getSeriesKey(int series) {
        return "Sample " + series;
    }

    public int getItemCount(int i) {
        return this.itemCount;
    }

    public Number getX(int series, int item) {
        return this.xValues[series][item];
    }

    public Number getY(int series, int item) {
        return this.yValues[series][item];
    }

    public double getDomainLowerBound(boolean bln) {
        return this.domainMin.doubleValue();
    }

    public double getDomainUpperBound(boolean bln) {
         return this.domainMax.doubleValue();
    }

    public Range getDomainBounds(boolean bln) {
        return this.domainRange;
    }

    public double getRangeLowerBound(boolean bln) {
        return this.rangeMin.doubleValue();
    }

    public double getRangeUpperBound(boolean bln) {
        return this.rangeMax.doubleValue();
    }

    public Range getRangeBounds(boolean bln) {
        return this.range;
    }
    
}
