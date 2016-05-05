/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author hp
 */
public class TimeSeriesAWT extends ApplicationFrame {

    public TimeSeriesAWT(final String title, List list) {
        super(title);
        List shapelets = new ArrayList(list);
        final XYDataset dataset = createDataset(shapelets);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        chartPanel.setPreferredSize(new java.awt.Dimension(fullScreen.width, fullScreen.height));
        //chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset(List list) {
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        for (int i = 0; i < list.size(); i++) {
            List s = (List) list.get(i);
            Minute min = new Minute(0, 0, 1, 1, 2012);
            TimeSeries series = new TimeSeries("Shapelet" + (i + 1));
            
            System.err.println("Rendering: "+s);
            for (int j = 0; j < s.size(); j++) {
                //System.out.println("min->>" + min);
                //System.out.println("s:" + Double.parseDouble("" + s.get(j)));
                series.add(min, Double.parseDouble("" + s.get(j)));
                for (int iterator = 0; iterator < 30; iterator++) {
                    min = (Minute) min.next();
                }
            }
             
            dataset.addSeries(series);
        }
        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        return ChartFactory.createTimeSeriesChart(
                "Shapelets",
                "Time",
                "kW",
                dataset,
                true,
                true,
                false);
    }

//    public static void main(final String[] args) {
//
//        final String frameTitle = "Time Series Analysis";
//        List list = new ArrayList();
//        List s1 = new ArrayList();
//        List s2 = new ArrayList();
//        s1.add(0.3);
//        s1.add(0.7);
//        s1.add(0.6);
//        s1.add(0.2);
//        s1.add(0.9);
//
//        s2.add(0.5);
//        s2.add(0.4);
//        s2.add(0.1);
//        s2.add(0.8);
//        s2.add(0.8);
//        
//        list.add(s1);
//        list.add(s2);
//
//        System.out.println(list);
//
//        TimeSeriesAWT timeSeriesAWT = new TimeSeriesAWT(frameTitle, list);
//        timeSeriesAWT.pack();
//        RefineryUtilities.positionFrameRandomly(timeSeriesAWT);
//        timeSeriesAWT.setVisible(true);
//    }
}
