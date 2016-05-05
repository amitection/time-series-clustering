/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DrawCluster.java
 *
 * Created on 7 Dec, 2014, 6:57:59 PM
 */

package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class DrawCluster extends java.applet.Applet
{
    
    public static ArrayList<Double> mas=new ArrayList<Double>();
  public void paint(Graphics g)
  {
        setBackground(Color.WHITE);
         g.setColor(Color.BLACK);

        g.drawLine(50,10 , 50,410);
        g.drawLine(50,410 , 510,410);

//        g.drawString("|", 100,415);
//        g.drawString("file 10",125 ,430);
//        g.drawString("|",230 ,415);
//        g.drawString("file 20",215 ,430);
//        g.drawString("|", 320,415);
//         g.drawString("file 30",305 ,430);
//        g.drawString("|", 410,415);
//         g.drawString("file 40",395 ,430);
//        g.drawString("|", 500,415);
//         g.drawString("file 50",485 ,430);
//            g.drawString("Time in ms",30,10);

     
        //g.drawString("-",50 ,410 );
        g.drawString("-",50 ,370);
        g.drawString("0.5",10 ,370);
        g.drawString("-", 50,330);
        g.drawString("1.0",10 ,330);
        g.drawString("-", 50,290);
        g.drawString("1.5",10 ,290);
        g.drawString("-", 50,250);
        g.drawString("2.0",10 ,250);

        g.drawString("-",50 ,210 );
        g.drawString("2.5",10 ,210);
        g.drawString("-",50 ,170);
        g.drawString("3.0",10 ,170);
        g.drawString("-", 50,130);
        g.drawString("3.5",10 ,130);
        g.drawString("-", 50,90);
        g.drawString("4.0",10 ,90);
         g.drawString("Time ", 260,480);
           g.drawString("kW ",30,10);
        System.out.println("Mas is "+mas);
        int x=50;
        
        int cx = 0,cy = 0,px,py;
        for(int i=0;i<mas.size();i++)
        {
          x=x+50;
          
          double f=mas.get(i);
         double y1=f*80;
         int t=(int)y1;
         int y=410-t;
         
         if(i==0)
         {
             cx=x;
             cy=y;
             
         }
         
         else
         {
             px=cx;
             py=cy;
             
             cx=x;
             cy=y;
             
             g.drawLine(px, py, cx, cy);
             
             
             
         }
          
 
          System.out.println(x+" , "+y);
        //g.drawString("*", x, y);
           
            g.drawString("|", x+8,415);
            
            
            
        }
       
       

//           ArrayList a=new ArrayList();
//           String nm="file1";
//           String str="select * from clusteralgo where A='"+nm+"'";
//           DataFetcher1 d=new DataFetcher1();
//           a=d.datafetcher1(str);
//           int x1=50,y=10;
//           int st=50,end=410;
//           for(int i=0;i<a.size();i++)
//           {
//           String s=(String) a.get(i);
//           int j=Integer.parseInt(s);
//           j=100-j;
//           y=10+(j*4);
//           x1=x1+90;
//           g.setColor(Color.red);
//           g.drawString("*", x1-3, y+8);
//           g.setColor(Color.blue);
//           g.drawLine(st, end, x1, y);
//           st=x1;
//           end=y;
//           }
//
//           ArrayList b=new ArrayList();
//           String nm1="file2";
//           String str1="select * from clusteralgo where A='"+nm1+"'";
//           DataFetcher1 d1=new DataFetcher1();
//           b=d1.datafetcher1(str1);
//           int x2=50,y1=10;
//           int st1=50,end1=410;
//           for(int i=0;i<b.size();i++)
//           {
//           String s=(String) b.get(i);
//           int j=Integer.parseInt(s);
//           j=100-j;
//           y1=10+(j*4);
//           x2=x2+90;
//           g.setColor(Color.CYAN);
//           g.drawString("*", x2-3, y1+8);
//           g.setColor(Color.YELLOW);
//           g.drawLine(st1, end1, x2, y1);
//           st1=x2;
//           end1=y1;
//           }
//
//           ArrayList c=new ArrayList();
//           String nm2="file3";
//           String str2="select * from clusteralgo where A='"+nm2+"'";
//           DataFetcher1 d2=new DataFetcher1();
//           c=d2.datafetcher1(str2);
//           int x3=50,y2=10;
//           int st2=50,end2=410;
//           for(int i=0;i<c.size();i++)
//           {
//           String s=(String) c.get(i);
//           int j=Integer.parseInt(s);
//           j=100-j;
//           y2=10+(j*4);
//           x3=x3+90;
//           g.setColor(Color.red);
//           g.drawString("*", x3-3, y2+8);
//           g.setColor(Color.GREEN);
//           g.drawLine(st2, end2, x3, y2);
//           st2=x3;
//           end2=y2;
//           }
//
//           ArrayList dd=new ArrayList();
//           String nm3="file4";
//           String str3="select * from clusteralgo where A='"+nm3+"'";
//           DataFetcher1 d3=new DataFetcher1();
//           dd=d3.datafetcher1(str3);
//           int x4=50,y3=10;
//           int st3=50,end3=410;
//           for(int i=0;i<dd.size();i++)
//           {
//           String s=(String) dd.get(i);
//           int j=Integer.parseInt(s);
//           j=100-j;
//           y3=10+(j*4);
//           x4=x4+90;
//           g.setColor(Color.red);
//           g.drawString("*", x4-3, y3+8);
//           g.setColor(Color.orange);
//           g.drawLine(st3, end3, x4, y3);
//           st3=x4;
//           end3=y3;
//           }
  }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
