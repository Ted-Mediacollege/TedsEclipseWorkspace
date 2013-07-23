package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import client.draw.Draw;
import client.gui.Gui;

public class JCHAT2client extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Gui gui;
	public Draw draw;

	private Timer ticktimer;
	public boolean debugmode = true;
	
	public int focusedinputfield = -1;

	public JCHAT2client()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200 + 6, 800 + 28);
		setLocationRelativeTo(null);
		setTitle("JChat 2");
		setResizable(false);	
		
		draw = new Draw(1200, 800);
		
		add(draw);
		
		setVisible(true);

		tick();
		ticktimer = new Timer((int) Math.floor(1000 / 20), 
		new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				tick(); 
			} 
		});  
		ticktimer.start();
	}

	public void switchGui(Gui newgui)
	{
		if(gui != null)
		{
			gui.destroy();
		}
		
		gui = newgui;
		gui.create();
	}
	
	public void tick()
	{
		Thread drawthread = new Thread(draw); 
		drawthread.start(); 
	}
	
	public static void main(String[] args)
	{
		new JCHAT2client();
	}
}