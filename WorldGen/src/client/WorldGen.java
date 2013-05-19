package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import client.draw.Draw;
import client.world.World;

public class WorldGen extends JFrame 
{
	private static final long serialVersionUID = 1L;

	public Draw draw;
	public World world;
	public Timer ticktimer;
	
	public WorldGen()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500 + 6, 800 + 28);
		setLocationRelativeTo(null);
		setTitle("WORLD GEN TEST 1");
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
	
	public static void main(String[] args) 
	{
		new WorldGen();
	}
	
	public void tick()
	{
		Thread paint = new Thread(draw); 
		paint.start(); 
		world.tick();
	}
}
