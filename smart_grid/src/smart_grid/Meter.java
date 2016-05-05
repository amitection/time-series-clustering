package smart_grid;

import java.util.ArrayList;

/**
  This class represents each individual meter 
  along with the consumption (kw) for that meter
 */

public class Meter {
	
    public ArrayList<Double> kW = new ArrayList<Double>();
    private int meterID;
    
    public Meter(int meterID, double consumption) {
    	this.meterID = meterID;
    	this.kW.add(consumption);
    }
    
    public void setkW(double consumption)
    {
    	kW.add(consumption);
    }
    
    public int getMeterID() {
        return meterID;
    }
    
    public int getKwSize() {
        return kW.size();
    }
}
