/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapelets;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class TimeSeriesMarker {

    private List<Double> shapelet = new ArrayList();
    private int startTime;
    private int endTime;
    public TimeSeriesMarker(int startTime,int endTime,List<Double> shapelet) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shapelet = shapelet;
    }


    
    public int getStartTime(){
        return this.startTime;
    }
    
    public int getEndTime(){
        return this.endTime;
    }
    
    public List<Double> getShaplet(){
       return this.shapelet;
    }
}
