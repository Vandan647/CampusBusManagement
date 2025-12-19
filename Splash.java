package campusComplaints;

import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame implements Runnable {
     ImageIcon i1 = new ImageIcon(Splash.class.getResource("splash.jpeg"));
     Image i2 = i1.getImage().getScaledInstance(900,600,Image.SCALE_DEFAULT);
     ImageIcon i3 = new ImageIcon(i2);
	 JLabel image = new JLabel(i3);
    Splash() {
         
        this.setLayout(null);
        this.setVisible(true);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        add(image);
        image.setSize(900,600);
        Thread t1 = new Thread(this);
        t1.start();
        
    }
    
    public void run()
    {
    	try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			
		}
    	this.setVisible(false);
    	new Login();
    	
    }

    public static void main(String[] args)  {
        Splash s = new Splash(); 
        for(int i =0 ; i<=450 ; i++)
        {
        	s.setSize(2*i,2*i-200);
        	s.setLocation(750-(i-150),400-(i/2));
        	try {
        	Thread.sleep(4);
        	}
        	catch(Exception e)
        	{
        		
        	}
        	
        }
    }
}
