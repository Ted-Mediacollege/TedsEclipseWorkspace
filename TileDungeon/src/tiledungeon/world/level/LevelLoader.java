package tiledungeon.world.level;

import java.io.BufferedReader;
import java.io.FileReader;

public class LevelLoader 
{
	public static Level load(String name)
	{
		int[][] lvl = new int[20][20];
		
		try
		{
			FileReader fileReader = new FileReader("res/levels/" + name + ".lvl");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			int row = 0;
	        while ((line = bufferedReader.readLine()) != null) 
	        {
	        	if(row >= 0 && row <= 19)
	        	{
	        		String[] levelpart = line.split(",");
		        	for(int i = 0; i < 20; i++)
		        	{
		        		lvl[i][row] = Integer.parseInt(levelpart[i]);
		        	}
	        	}
	        	
	        	row++;
	        }
	        
	        bufferedReader.close();
		}
		catch(Exception e)
		{
			System.out.println("Cannot load: " + name );
		}
		
		return new Level(lvl, 20, 20);
	}
}