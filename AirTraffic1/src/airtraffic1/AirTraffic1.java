package airtraffic1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import airtraffic1.draw.Draw;
import airtraffic1.world.World;

public class AirTraffic1 extends JFrame
{
	private static final long serialVersionUID = 1L;
	public int[] mousepos = new int[2];
	
	public Draw draw;
	public World world;
	private Timer ticktimer;
	
	public AirTraffic1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200 + 6, 800 + 28);
		setLocationRelativeTo(null);
		setTitle("AirTraffic1");
		setResizable(false);	
		
		draw = new Draw();
		world = new World();
		
		add(draw);
		
		setVisible(true);
		
		tick();
		ticktimer = new Timer((int) Math.floor(1000 / 2), 
		new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				tick(); 
			} 
		});  
		ticktimer.start();

		addMouseListener(new Mouse());
	}
	
	public void tick()
	{
		Thread drawthread = new Thread(draw); 
		drawthread.start(); 
		world.tick();
	}
	
	public static void main(String[] args)
	{
		new AirTraffic1();
	}
	
    private class Mouse extends MouseAdapter 
	{
    	public void mouseMoved(MouseEvent e)
    	{
    	}
    	
    	public void mousePressed(MouseEvent e)
    	{
    	}
	}
}
