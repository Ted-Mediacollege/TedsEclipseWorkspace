package client.network;

import java.util.ArrayList;

import client.entity.EntityBullet;
import client.entity.EntityPlayerOther;
import client.entity.EntityRocket;
import client.util.Coords;
import client.world.World;

import jexxus.client.ClientConnection;
import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.common.Delivery;
import jexxus.server.ServerConnection;

public class Network implements Runnable
{
	public ClientConnection conection;
	
	public static String ServerIP = "127.0.0.1";
	public static int ServerPort = 1337;
	
	public static ArrayList<String> queue = new ArrayList<String>();
	
	public static boolean conected = false;
	public static int playerid = -2;
	
	public Network()
	{
		
	}
	
	public void sendQueue()
	{
		try 
		{ 
			conection = new ClientConnection(new clientListener(), ServerIP , ServerPort, false);
			conection.connect(200);
			conected = true;
			
			for(int q = 0; q < queue.size(); q++)
			{
				conection.send(queue.get(q).getBytes(), Delivery.RELIABLE);
			}
			
			queue = new ArrayList<String>();
		}
		catch(Exception exception)
		{
			conected = false;
			System.out.println("cannot send data to server!");
		}
	}
	
	public void run()
	{
		sendQueue();
	}
	
	public class clientListener implements ConnectionListener
	{
		public void connectionBroken(Connection broken, boolean forced) { }
		public void clientConnected(ServerConnection conn) { }
		public void receive(byte[] data, Connection from) 
		{
			if(new String(data).equals("end") && conection.isConnected())
			{
				conection.close();
			}
			else
			{
				String[] datasplit = new String(data).split("#");
				if(datasplit[0].equals("register"))
				{
					int pID = Integer.parseInt(datasplit[1]);
					playerid = pID;
				}
				else if(datasplit[0].equals("player"))
				{
					String[] playerdatasplit = new String(datasplit[2]).split("&");
					
					int id = World.getPlayerFromID(Integer.parseInt(datasplit[1]));
					
					if(Integer.parseInt(datasplit[1]) == playerid)
					{
						//do nothing
					}
					else if(id == -1)
					{
						World.players.add(new EntityPlayerOther(Integer.parseInt(datasplit[1]), Double.parseDouble(playerdatasplit[0]), Double.parseDouble(playerdatasplit[1]), Double.parseDouble(playerdatasplit[2]), Double.parseDouble(playerdatasplit[3])));
					}
					else
					{
						World.players.get(id).posX = Double.parseDouble(playerdatasplit[0]);
						World.players.get(id).posY = Double.parseDouble(playerdatasplit[1]);
						World.players.get(id).velX = Double.parseDouble(playerdatasplit[2]);
						World.players.get(id).velY = Double.parseDouble(playerdatasplit[3]);
						World.players.get(id).health = Float.parseFloat(playerdatasplit[4]);
					}
				}
				else if(datasplit[0].equals("projectile"))
				{
					String[] projectiledatasplit = new String(datasplit[2]).split("&");
					int id = World.getPlayerFromID(Integer.parseInt(datasplit[1]));
					
					if(id != playerid)
					{
						if(Integer.parseInt(projectiledatasplit[3]) == 0)
						{
							World.projectiles.add(new EntityBullet(id, Double.parseDouble(projectiledatasplit[0]), Double.parseDouble(projectiledatasplit[1]), Double.parseDouble(projectiledatasplit[2])));
						}
						if(Integer.parseInt(projectiledatasplit[3]) == 1)
						{
							World.projectiles.add(new EntityRocket(id, Double.parseDouble(projectiledatasplit[0]), Double.parseDouble(projectiledatasplit[1]), Double.parseDouble(projectiledatasplit[2])));
						}
					}
				}
			}
		}
	}
}
