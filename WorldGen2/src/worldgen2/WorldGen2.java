package worldgen2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import worldgen2.draw.Draw;
import worldgen2.world.World;

public class WorldGen2 extends JFrame 
{
	private static final long serialVersionUID = 1L;

	public static Draw draw;
	public Timer ticktimer;
	public World world = new World();
	
	public static boolean calcing;
	public static double calctime;
	
	public WorldGen2()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 800);
		setLocationRelativeTo(null);
		setTitle("World Gen 2");
		setResizable(false);		
		
		draw = new Draw();
		add(draw);
		
		setVisible(true);
		
		ticktimer = new Timer((int) Math.floor(1000 / 60), 
		new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				tick(); 
			} 
		});  
		ticktimer.start();
		
		Thread w = new Thread(world); 
		w.start(); 
		
		addKeyListener(new Keys());
	}
	
	public static void main(String[] args) 
	{
		new WorldGen2();
	}
	
	public void tick()
	{
		if(calcing)
		{
			calctime += 1000D / 60D;
		}
	}
    
    private class Keys extends KeyAdapter 
	{
	    public void keyTyped(KeyEvent e) 
	    {
	    }
	
	    public void keyPressed(KeyEvent e) 
	    {
	    	int k = e.getKeyCode();
	    	if(k == 78 && !calcing)
	    	{
	    		Thread w = new Thread(world); 
	    		w.start(); 
				Thread paint = new Thread(draw); 
				paint.start(); 
	    	}
	    }
	
	    public void keyReleased(KeyEvent e) 
	    {
	    }
	}
}
