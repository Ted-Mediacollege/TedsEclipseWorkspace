package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import client.draw.Draw;
import client.network.Network;
import client.network.NetworkPlayer;
import client.world.World;

public class Extreme2Dshooter extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public boolean[] keyinput = new boolean[5];
	
	public Draw draw;
	public World world;
	public Timer ticktimer;
	public Network network;
	public int networktimer = 0;
	
	public Timer fpsSec;
	public int fpscount = 0;
	public static int fps = 0;
	
	public Extreme2Dshooter()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((24*40) + 6, (24*30) + 28);
		setLocationRelativeTo(null);
		setTitle("EXTREME 2D SHOOTER EGT VET COOL MAN");
		setResizable(false);
		
		world = new World();
		draw = new Draw();
		network = new Network();
		NetworkPlayer.registerPlayer();
		add(draw);
		
		setVisible(true);		
		
		tick();
		ticktimer = new Timer((int) Math.floor(1000 / 60), 
		new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				tick(); 
				fpscount++;
			} 
		});  
		ticktimer.start();
		
		fpsSec = new Timer((int) Math.floor(1000), 
		new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				tick(); 
				fps = fpscount;
				fpscount = 0;
			} 
		});  
		fpsSec.start();
		
		addKeyListener(new Keys());
	}
	
	public void tick()
	{
		Thread paint = new Thread(draw); 
		paint.start(); 
		world.tick(keyinput);
		
		networktimer--;
		if(networktimer < 0)
		{
			NetworkPlayer.updatePlayer(World.player.posX, World.player.posY, World.player.velX, World.player.velY);
			Thread networksend = new Thread(network); 
			networksend.start(); 
			networktimer = 5;
		}
	}	
	
	public static void main(String[] args) 
	{
		new Extreme2Dshooter();
	}
	
    private class Keys extends KeyAdapter 
	{
	    public void keyTyped(KeyEvent e) 
	    {
	    }
	
	    public void keyPressed(KeyEvent e) 
	    {
	    	int k = e.getKeyCode();
	    	if(k==38){keyinput[0]=true;}//up
	    	if(k==39){keyinput[1]=true;}//right
	    	if(k==37){keyinput[2]=true;}//left
	    	if(k==40){keyinput[3]=true;}//down
	    	if(k==32){keyinput[4]=true;}//jump
	    }
	
	    public void keyReleased(KeyEvent e) 
	    {
	    	int k = e.getKeyCode();
	    	if(k==38){keyinput[0]=false;}//up
	    	if(k==39){keyinput[1]=false;}//right
	    	if(k==37){keyinput[2]=false;}//left
	    	if(k==40){keyinput[3]=false;}//down
	    	if(k==32){keyinput[4]=false;}//jump
	    }
	}
}
