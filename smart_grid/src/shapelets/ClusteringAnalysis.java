/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapelets;

import java.util.ArrayList;
import smart_grid.Meter;

/**
 *
 * @author hp
 */
public class ClusteringAnalysis {

    private ArrayList D = new ArrayList();
    private ArrayList clusters = new ArrayList();
    public ClusteringAnalysis(ArrayList D, ArrayList clusters) {
        this.D = D;
        this.clusters = clusters;
        startAnalysis();
    }
    
    void startAnalysis() {
        getAverageAnalysis();
        get21to7Analysis();
        get7to9Analysis();
        get9to13Analysis();
        get13to17Analysis();
        get17to21Analysis();
    }

    private void getAverageAnalysis() {
        for(int iterator=0;iterator < clusters.size(); iterator++){
            ArrayList cluster = (ArrayList)clusters.get(iterator);
            float avgHourlyOfCluster = 0;
            float avgDailyOfCluster = 0;
            
            for(int index=0;index<cluster.size();index++) {
                int meterID = (Integer)cluster.get(index);
                
                Meter meter = (Meter) D.get(meterID - 1000);
                double avgHourlyDaily = 0;
                double avgDaily = 0;
                for(int i=0;i<(meter.getKwSize() / 48);i=i+48) {
                    double avgHourly = 0;
                    for(int j=i;j<48;j++) {
                        avgHourly += meter.kW.get(j);
                    }
                    avgDaily +=  avgHourly;
                    avgHourly = avgHourly / 24;
                    avgHourlyDaily += avgHourly;
                }
              
                avgHourlyDaily = avgHourlyDaily / 30;
                avgHourlyOfCluster += avgHourlyDaily; 
                
                avgDaily = avgDaily / 30;
                avgDailyOfCluster += avgDaily;
            }
            System.out.println("Cluster "+iterator+" average Hourly KW : "+avgHourlyOfCluster/ cluster.size()); 
            System.out.println("Cluster "+iterator+" average Daily KW : "+avgDailyOfCluster/ cluster.size());
        }
    }

    private void get21to7Analysis() {
        for(int iterator=0;iterator < clusters.size(); iterator++){
            ArrayList cluster = (ArrayList)clusters.get(iterator);
            float avgHourlyOfCluster = 0;
            
            for(int index=0;index<cluster.size();index++) {
                int meterID = (Integer)cluster.get(index);
                
                Meter meter = (Meter) D.get(meterID - 1000);
                double avgHourlyDaily = 0;
                for(int i=0;i<(meter.getKwSize() / 48);i=i+48) {
                    double avgHourly = 0;
                    for(int j=i;j<48;j++) {
                        if((j%48) < 14 || (j%48) >= 42 ){
                            avgHourly += meter.kW.get(j);
                        }
                    }
                    avgHourly = avgHourly / 10;
                    avgHourlyDaily += avgHourly;
                }
              
                avgHourlyDaily = avgHourlyDaily / 30;
                avgHourlyOfCluster += avgHourlyDaily; 
            }
            System.err.println("Cluster "+iterator+" average 21-7 KW : "+avgHourlyOfCluster/ cluster.size()); 
        }
        System.out.println("\n");
    }

    private void get7to9Analysis() {
        for(int iterator=0;iterator < clusters.size(); iterator++){
            ArrayList cluster = (ArrayList)clusters.get(iterator);
            float avgHourlyOfCluster = 0;
            
            for(int index=0;index<cluster.size();index++) {
                int meterID = (Integer)cluster.get(index);
                
                Meter meter = (Meter) D.get(meterID - 1000);
                double avgHourlyDaily = 0;
                for(int i=0;i<(meter.getKwSize() / 48);i=i+48) {
                    double avgHourly = 0;
                    for(int j=i;j<48;j++) {
                        if((j%48) >= 14 && (j%48) < 18 ){
                            avgHourly += meter.kW.get(j);
                        }
                    }
                    avgHourly = avgHourly / 2;
                    avgHourlyDaily += avgHourly;
                }
              
                avgHourlyDaily = avgHourlyDaily / 30;
                avgHourlyOfCluster += avgHourlyDaily; 
            }
            System.err.println("Cluster "+iterator+" average 7-9 KW : "+avgHourlyOfCluster/ cluster.size()); 
        }
        System.out.println("\n");
    }

    private void get9to13Analysis() {
        for(int iterator=0;iterator < clusters.size(); iterator++){
            ArrayList cluster = (ArrayList)clusters.get(iterator);
            float avgHourlyOfCluster = 0;
            
            for(int index=0;index<cluster.size();index++) {
                int meterID = (Integer)cluster.get(index);
                
                Meter meter = (Meter) D.get(meterID - 1000);
                double avgHourlyDaily = 0;
                for(int i=0;i<(meter.getKwSize() / 48);i=i+48) {
                    double avgHourly = 0;
                    for(int j=i;j<48;j++) {
                        if((j%48) >= 18 && (j%48) < 26 ){
                            avgHourly += meter.kW.get(j);
                        }
                    }
                    avgHourly = avgHourly / 4;
                    avgHourlyDaily += avgHourly;
                }
              
                avgHourlyDaily = avgHourlyDaily / 30;
                avgHourlyOfCluster += avgHourlyDaily; 
            }
            System.err.println("Cluster "+iterator+" average 9-13 KW : "+avgHourlyOfCluster/ cluster.size()); 
        }
        System.out.println("\n");
    }

    private void get13to17Analysis() {
        for(int iterator=0;iterator < clusters.size(); iterator++){
            ArrayList cluster = (ArrayList)clusters.get(iterator);
            float avgHourlyOfCluster = 0;
            
            for(int index=0;index<cluster.size();index++) {
                int meterID = (Integer)cluster.get(index);
                
                Meter meter = (Meter) D.get(meterID - 1000);
                double avgHourlyDaily = 0;
                for(int i=0;i<(meter.getKwSize() / 48);i=i+48) {
                    double avgHourly = 0;
                    for(int j=i;j<48;j++) {
                        if((j%48) >= 26 || (j%48) < 34 ){
                            avgHourly += meter.kW.get(j);
                        }
                    }
                    avgHourly = avgHourly / 4;
                    avgHourlyDaily += avgHourly;
                }
              
                avgHourlyDaily = avgHourlyDaily / 30;
                avgHourlyOfCluster += avgHourlyDaily; 
            }
            System.err.println("Cluster "+iterator+" average 13-17 KW : "+avgHourlyOfCluster/ cluster.size()); 
        }
        System.out.println("\n");
    }
    
    
    private void get17to21Analysis() {
        for(int iterator=0;iterator < clusters.size(); iterator++){
            ArrayList cluster = (ArrayList)clusters.get(iterator);
            float avgHourlyOfCluster = 0;
            
            for(int index=0;index<cluster.size();index++) {
                int meterID = (Integer)cluster.get(index);
                
                Meter meter = (Meter) D.get(meterID - 1000);
                double avgHourlyDaily = 0;
                for(int i=0;i<(meter.getKwSize() / 48);i=i+48) {
                    double avgHourly = 0;
                    for(int j=i;j<48;j++) {
                        if((j%48) >= 34 || (j%48) < 42 ){
                            avgHourly += meter.kW.get(j);
                        }
                    }
                    avgHourly = avgHourly / 4;
                    avgHourlyDaily += avgHourly;
                }
              
                avgHourlyDaily = avgHourlyDaily / 30;
                avgHourlyOfCluster += avgHourlyDaily; 
            }
            System.err.println("Cluster "+iterator+" average 17-21 KW : "+avgHourlyOfCluster/ cluster.size()); 
        }
        System.out.println("\n");
    }
    
}
