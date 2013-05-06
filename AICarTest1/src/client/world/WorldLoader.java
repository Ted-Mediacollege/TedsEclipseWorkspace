package client.world;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import client.MainClass;

public class WorldLoader 
{
	public WorldLoader()
	{
	}
	
	public void load()
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader("world/world.txt"));
			
			String line = null;
			int row = 0;
	        while ((line = bufferedReader.readLine()) != null) 
	        {
	        	if(row >= 0 && row <= 39) //terrain
	        	{
	        		String[] levelpart = line.split(",");
		        	for(int i = 0; i < 40; i++)
		        	{
		        		World.Terrain[i][row] = Integer.parseInt(levelpart[i]);
		        	}
	        	}
	        	
	        	row++;
	        }
	        
	        bufferedReader.close();
		}
		catch(Exception e)
		{
			System.out.println("Cannot load world!");
		}
		

		try
		{
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(MainClass.class.getResourceAsStream("/world/world.txt")));
			
			String line = null;
			int row = 0;
	        while ((line = bufferedReader.readLine()) != null) 
	        {
	        	if(row >= 0 && row <= 39) //terrain
	        	{
	        		String[] levelpart = line.split(",");
		        	for(int i = 0; i < 40; i++)
		        	{
		        		World.Terrain[i][row] = Integer.parseInt(levelpart[i]);
		        	}
	        	}
	        	
	        	row++;
	        }
	        
	        bufferedReader.close();
		}
		catch(Exception e)
		{
			System.out.println("Cannot load world!");
		}
	}
}
