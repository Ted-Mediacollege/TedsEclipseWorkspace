package server.playerlist;

import java.util.ArrayList;

public class Player 
{
	public ArrayList<String> queue = new ArrayList<String>();
	public int id;
	public double posX;
	public double posY;
	public double velX;
	public double velY;
	public float health;
	
	public Player(int i)
	{
		id = i;
		posX = 20;
		posY = 10;
		velX = 0;
		velY = 0;
		health = 20F;
	}
}
