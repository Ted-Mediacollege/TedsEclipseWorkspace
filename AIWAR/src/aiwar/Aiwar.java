package aiwar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import aiwar.draw.Draw;
import aiwar.world.World;

public class Aiwar extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public static World world;
	public Draw draw;
	private Timer ticktimer;
	
	public Aiwar()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((20*30) + 6, (20*20) + 28);
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
		new Aiwar();
	}
}
