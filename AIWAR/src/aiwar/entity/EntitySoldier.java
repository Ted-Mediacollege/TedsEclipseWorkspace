package aiwar.entity;

import java.util.ArrayList;

import aiwar.Aiwar;
import aiwar.util.Coords;

public class EntitySoldier extends Entity
{
	public int team;
	public int health;
	public ArrayList<int[]> walkinst = new ArrayList<int[]>();
	public double[] walktarget;
	
	public EntitySoldier(int t)
	{
		team = t;
	}
	
	public void tick()
	{
	}
	
	public void walk()
	{
		if(walktarget != null)
		{
			if(walktarget[0] < posX) { posX -= 0.01; }
			if(walktarget[0] > posX) { posX += 0.01; }
			if(walktarget[1] < posY) { posY -= 0.01; }
			if(walktarget[1] > posY) { posY += 0.01; }
			
			if(Coords.getDistance(posX, posY, walktarget[0], walktarget[1]) < 0.02) { walktarget = null; }
		}
		else if(walkinst.size() > 0)
		{
			System.out.println("GETTING NEXT TARGET");
			walktarget = new double[2];
			walktarget[0] = walkinst.get(0)[0] + 0.5D;
			walktarget[1] = walkinst.get(0)[1] + 0.5D;
			walkinst.remove(0);
		}
		else
		{
			System.out.println("CREATING NEW PATH INST");
			
			int x = (int) Math.floor(posX);
			int y = (int) Math.floor(posY);
			int d = -1;
			
			for(int t = 0; t < 3; t++)
			{
				System.out.println(t + "/3");
				
				int whilesafe = 0;
				while(true)
				{
					if(isEmptyTile(x, y - 1) && d != 0 && Math.floor(Math.random() * 4) == 0)
					{
						y -= 1;
						walkinst.add(new int[]{x,y});
						if(t == 0) { d = 0; }
						break;
					}
					else if(isEmptyTile(x + 1, y) && d != 1 && Math.floor(Math.random() * 4) == 0)
					{
						x += 1;
						if(t == 0) { d = 1; }
						walkinst.add(new int[]{x,y});
						break;
					}
					else if(isEmptyTile(x, y + 1) && d != 2 && Math.floor(Math.random() * 4) == 0)
					{
						y += 1;
						if(t == 0) { d = 2; }
						walkinst.add(new int[]{x,y});
						break;
					}
					else if(isEmptyTile(x - 1, y) && d != 3 && Math.floor(Math.random() * 4) == 0)
					{
						x -= 1;
						if(t == 0) { d = 3; }
						walkinst.add(new int[]{x,y});
						break;
					}
					
					whilesafe++;
					if(whilesafe > 50) { break; }
				}
			}
		}
	}
	
	public boolean isEmptyTile(int x, int y)
	{
		if(Aiwar.world.terrain[x][y] == 0)
		{
			return true;
		}
		return false;
	}
}
