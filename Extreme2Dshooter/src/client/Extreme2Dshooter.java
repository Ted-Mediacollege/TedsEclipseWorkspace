package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import client.draw.Draw;
import client.entity.EntityBullet;
import client.entity.EntityRocket;
import client.network.Network;
import client.network.NetworkPlayer;
import client.util.Coords;
import client.world.World;

public class Extreme2Dshooter extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public boolean[] keyinput = new boolean[5];
	public int[] mousepos = new int[2];
	
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
		addMouseListener(new Mouse());
		addMouseMotionListener(new Mouse());
	}
	
	public void tick()
	{
		Thread paint = new Thread(draw); 
		paint.start(); 
		world.tick(keyinput, mousepos);
		
		networktimer--;
		if(networktimer < 0)
		{
			NetworkPlayer.updatePlayer(World.player.posX, World.player.posY, World.player.velX, World.player.velY, World.player.health);
			Thread networksend = new Thread(network); 
			networksend.start(); 
			networktimer = 10;
		}
	}	
	
	public static void main(String[] args) 
	{
		new Extreme2Dshooter();
	}

    private class Mouse extends MouseAdapter 
	{
    	public void mouseMoved(MouseEvent e)
    	{
    		mousepos[0] = e.getX() - 6;
    		mousepos[1] = e.getY() - 28;
    	}
    	
    	public void mousePressed(MouseEvent e)
    	{
    		int b = e.getButton();
    		if(b==1) { World.projectiles.add(new EntityBullet(Network.playerid, World.player.posX, World.player.posY, Coords.getDegreeFromPoint(World.player.posX, World.player.posY, (double) mousepos[0], (double) mousepos[1]))); NetworkPlayer.projectile(World.player.posX, World.player.posY, Coords.getDegreeFromPoint(World.player.posX, World.player.posY, (double) mousepos[0], (double) mousepos[1]), 0); }
    		if(b==3) { World.projectiles.add(new EntityRocket(Network.playerid, World.player.posX, World.player.posY, Coords.getDegreeFromPoint(World.player.posX, World.player.posY, (double) mousepos[0], (double) mousepos[1]))); NetworkPlayer.projectile(World.player.posX, World.player.posY, Coords.getDegreeFromPoint(World.player.posX, World.player.posY, (double) mousepos[0], (double) mousepos[1]), 1); }
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
	    	if(k==87){keyinput[0]=true;}//up
	    	if(k==68){keyinput[1]=true;}//right
	    	if(k==65){keyinput[2]=true;}//left
	    	if(k==83){keyinput[3]=true;}//down
	    	if(k==32){keyinput[4]=true;}//jump
	    }
	
	    public void keyReleased(KeyEvent e) 
	    {
	    	int k = e.getKeyCode();
	    	if(k==87){keyinput[0]=false;}//up
	    	if(k==68){keyinput[1]=false;}//right
	    	if(k==65){keyinput[2]=false;}//left
	    	if(k==83){keyinput[3]=false;}//down
	    	if(k==32){keyinput[4]=false;}//jump
	    }
	}
}
