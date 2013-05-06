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
	
	public World world;
	public Painter painter;
	private Timer ticktimer;
	
	public boolean[] keys = new boolean[4];
	
	public MainClass()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024 + 6, 768 + 28);
		setLocationRelativeTo(null);
		setTitle("AI TEST 3");
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
		
		addKeyListener(new Keys());
	}
	
	public void tick()
	{
		Thread paint = new Thread(painter); 
		paint.start(); 
		world.tick(keys);
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
	    	if(e.getKeyCode() == 38)//up
	    	{
	    		keys[0] = true;
	    	}
	    	if(e.getKeyCode() == 39)//right
	    	{
	    		keys[1] = true;
	    	}
	    	if(e.getKeyCode() == 40)//down
	    	{
	    		keys[2] = true;
	    	}
	    	if(e.getKeyCode() == 37)//left
	    	{
	    		keys[3] = true;
	    	}
        }
	    
	    public void keyReleased(KeyEvent e) 
	    {
	    	if(e.getKeyCode() == 38)//up
	    	{
	    		keys[0] = false;
	    	}
	    	if(e.getKeyCode() == 39)//right
	    	{
	    		keys[1] = false;
	    	}
	    	if(e.getKeyCode() == 40)//down
	    	{
	    		keys[2] = false;
	    	}
	    	if(e.getKeyCode() == 37)//left
	    	{
	    		keys[3] = false;
	    	}
	    }
    }
}
