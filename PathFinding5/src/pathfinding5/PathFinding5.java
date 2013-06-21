package pathfinding5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import pathfinding5.draw.Draw;
import pathfinding5.world.World;

public class PathFinding5 extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public World world;
	public Draw draw;
	private Timer ticktimer;

	public PathFinding5()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024 + 6, 768 + 28);
		setLocationRelativeTo(null);
		setTitle("PathFinding 5");
		setResizable(false);	
		
		world = new World();
		draw = new Draw();
		
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
		new PathFinding5();
	}
}
	