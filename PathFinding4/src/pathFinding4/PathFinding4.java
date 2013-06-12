package pathFinding4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import pathFinding4.paint.Painter;
import pathFinding4.world.World;

public class PathFinding4 extends JFrame
{
	private static final long serialVersionUID = 1L;

	public World world;
	public Painter painter;
	private Timer ticktimer;
	
	public PathFinding4()
	{
		System.out.println("starting...");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((40 * 20) + 6, (40 * 20) + 28);
		setLocationRelativeTo(null);
		setTitle("PathFinding2");
		setResizable(false);	
		
		world = new World();
		painter = new Painter();
		add(painter);
		
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
		Thread paint = new Thread(painter); 
		paint.start(); 
		world.tick();
	}
	
	public static void main(String[] args) 
	{
		new PathFinding4();
	}
}
