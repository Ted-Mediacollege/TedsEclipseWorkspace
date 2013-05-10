package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import client.paint.Painter;
import client.world.World;

public class MainClass extends JFrame
{
	private static final long serialVersionUID = 1L;
	public Painter painter;
	public World world;
	private Timer ticktimer;
	
	public MainClass()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((26*37) + 6, (26*37) + 28);
		setLocationRelativeTo(null);
		setTitle("AI TEST 3");
		setResizable(false);		
		
		painter = new Painter();
		world = new World();
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
		
		addKeyListener(new Keys());
	}
	
	public void tick()
	{
		Thread paint = new Thread(painter); 
		paint.start(); 
		world.tick();
	}
	
	public static void main(String[] args) 
	{
		new MainClass();
	}
	
    private class Keys extends KeyAdapter 
	{
	    public void keyTyped(KeyEvent e) 
	    {
	    }
	
	    public void keyPressed(KeyEvent e) 
	    {
	        int key = e.getKeyCode();
	        System.out.println(key);
	        
	    	if (key == 114)
	        {
	    		if(painter.debug == true)
	    		{
	    			painter.debug = false;
	    		}
	    		else
	    		{
	    			painter.debug = true;
	    		}
	        }
	    }
	
	    public void keyReleased(KeyEvent e) 
	    {
	    }
	}
}
