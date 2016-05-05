/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smart_grid;

import graph.*;
import javax.swing.JApplet;

class cluster extends JApplet implements Runnable
{
public void init() { }

public void start()
{

this.getContentPane().add(new DrawCluster());
this.setVisible(true);
}


public void stop()
{

   }

    public void run()
    {

    }


}
