package server.network;

import server.playerlist.Player;
import server.playerlist.PlayerList;
import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.common.Delivery;
import jexxus.server.Server;
import jexxus.server.ServerConnection;

public class Network 
{
	private Server server;
	public int ServerPort = 1337;
	
	public Network()
	{
		try 
		{
			server = new Server(new serverListen(), ServerPort, false);
			server.startServer();
			//System.out.println("Ready for incoming data!");
		}
		catch(Exception exception)
		{
			System.out.println("cannot start listening");
		}
	}
	
	public class serverListen implements ConnectionListener 
	{		
		public void connectionBroken(Connection broken, boolean forced) { }
		public void clientConnected(ServerConnection conn) { }
		public void receive(byte[] data, Connection from) 
		{
			//System.out.println(new String(data));
			
			String[] datasplit = new String(data).split("#");
			if(datasplit[0].equals("register"))
			{
				int newid = PlayerList.getNextAvailableID();
				String registerstring = "register#" + newid;
				PlayerList.players.add(new Player(newid)); 
				from.send(registerstring.getBytes(), Delivery.RELIABLE);
			}
			else
			{
				int id = PlayerList.getPlayerFromID(Integer.parseInt(datasplit[1]));
				
				if(datasplit[0].equals("player"))
				{	
					if(id > -1)
					{
						String[] playerdatasplit = new String(datasplit[2]).split("&");
						
						PlayerList.players.get(id).posX = Double.parseDouble(playerdatasplit[0]);
						PlayerList.players.get(id).posY = Double.parseDouble(playerdatasplit[1]);
						PlayerList.players.get(id).velX = Double.parseDouble(playerdatasplit[2]);
						PlayerList.players.get(id).velY = Double.parseDouble(playerdatasplit[3]);
						PlayerList.players.get(id).health = Float.parseFloat(playerdatasplit[4]);
					}
					
					for(int p = 0; p < PlayerList.players.size(); p++)
					{
						String newstring = "player#" + PlayerList.players.get(p).id + "#" + PlayerList.players.get(p).posX + "&" + PlayerList.players.get(p).posY + "&" + PlayerList.players.get(p).velX + "&" + PlayerList.players.get(p).velY + "&" + PlayerList.players.get(p).health;
						from.send(newstring.getBytes(), Delivery.RELIABLE);
					}
					
					for(int d = 0; d < PlayerList.players.get(id).queue.size(); d++)
					{
						from.send((PlayerList.players.get(id).queue.get(d)).getBytes(), Delivery.RELIABLE);
					}
					PlayerList.players.get(id).queue.clear();
					
					from.send("end".getBytes(), Delivery.RELIABLE);
				}
				
				if(datasplit[0].equals("projectile"))
				{
					for(int pp = 0; pp < PlayerList.players.size(); pp++)
					{
						if(PlayerList.players.get(pp).id != id)
						{
							PlayerList.players.get(pp).queue.add(new String(data));
						}
					}
				}
			}
		}
	}
}
