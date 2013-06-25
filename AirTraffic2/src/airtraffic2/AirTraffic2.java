package airtraffic2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import airtraffic2.draw.Draw;
import airtraffic2.world.World;

public class AirTraffic2 extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Draw draw;
	public World world;
	private Timer ticktimer;

	public AirTraffic2()
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
		ticktimer = new Timer((int) Math.floor(1000 / 60), 
		new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				tick(); 
			} 
		});  
		ticktimer.start();
	}
	
	public void tick()
	{
		Thread drawthread = new Thread(draw); 
		drawthread.start(); 
		world.tick();
	}
	
	public static void main(String[] args)
	{
		new AirTraffic2();
	}
}
