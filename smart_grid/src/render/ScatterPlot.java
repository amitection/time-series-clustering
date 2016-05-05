/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author hp
 */
public class ScatterPlot extends ApplicationFrame {

    private ArrayList disMatrix;
    private List bestClusteringLabels;
    public ScatterPlot(String title, ArrayList disMatrix, List bestClusteringLabels) {
        super(title);
        this.disMatrix = disMatrix;
        this.bestClusteringLabels=bestClusteringLabels;
        JPanel chartPanel = createPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    
    
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot("Cluster Analysis",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");

        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);

        plot.setDomainGridlineStroke(new BasicStroke(0.0f));
        plot.setDomainMinorGridlineStroke(new BasicStroke(0.0f));
        plot.setDomainGridlinePaint(Color.blue);
        plot.setRangeGridlineStroke(new BasicStroke(0.0f));
        plot.setRangeMinorGridlineStroke(new BasicStroke(0.0f));
        plot.setRangeGridlinePaint(Color.blue);

        plot.setDomainMinorGridlinesVisible(true);
        plot.setRangeMinorGridlinesVisible(true);

        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setUseOutlinePaint(true);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);

        domainAxis.setTickMarkInsideLength(2.0f);
        domainAxis.setTickMarkOutsideLength(2.0f);

        domainAxis.setMinorTickCount(2);
        domainAxis.setMinorTickMarksVisible(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickMarkInsideLength(2.0f);
        rangeAxis.setTickMarkOutsideLength(2.0f);
        rangeAxis.setMinorTickCount(2);
        rangeAxis.setMinorTickMarksVisible(true);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public JPanel createPanel() {
        JFreeChart chart = createChart(new XYClusterDataset(disMatrix, bestClusteringLabels));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

 
//    public static void main(String[] args) {
//        ScatterPlot demo = new ScatterPlot(
//                "JFreeChart: ScatterPlotDemo1.java");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//    }

}
