package client.world;

import java.io.BufferedReader;
import java.io.FileReader;

public class WorldLoader 
{
	public int[][] load()
	{
		int[][] level = new int[40][30];
		BufferedReader bufferedReader;
		
		try
		{
			bufferedReader = new BufferedReader(new FileReader("levels/level.txt"));
		
			String line = null;
			int row = 0;
	        while ((line = bufferedReader.readLine()) != null) 
	        {
	        	if(row > 0 && row < 31)
	        	{
	        		String[] levelpart = line.split(",");
		        	for(int i = 0; i < 40; i++)
		        	{
		        		level[i][row - 1] = Integer.parseInt(levelpart[i]);
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
		
		return level;
	}
}
