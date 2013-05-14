package server;

import javax.swing.JFrame;

import server.network.Network;

public class Extreme2DshooterServer extends JFrame
{
	private static final long serialVersionUID = 1L;
	public Network network;
	
	public Extreme2DshooterServer()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((24*40) + 6, (24*30) + 28);
		setLocationRelativeTo(null);
		setTitle("EXTREME 2D SHOOTER EGT VET COOL MAN - SERVER BITCHASSSSSSS!");
		setResizable(false);
		
		network = new Network();
		
		setVisible(true);	
	}

	public static void main(String[] args) 
	{
		new Extreme2DshooterServer();
	}
}
