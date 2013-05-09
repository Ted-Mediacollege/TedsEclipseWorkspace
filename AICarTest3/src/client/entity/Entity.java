package client.entity;

import client.util.Coords;
import client.world.World;

public class Entity 
{
	public double posX;
	public double posY;
	public double posR;
	public int cSpeed;
	public int mSpeed;
	
	public int path;
	public int point;
	
	public double[][] hitbox = new double[4][2];
	
	public Entity(int x, int y, int r, int s)
	{
		posX = x;
		posY = y;
		posR = r;
		cSpeed = s;
		mSpeed = s;
		
		path = -1;
	}
	
	public void tick()
	{
		//check for new paths
		if(path == -1)
		{
			path = -1;
			lookForPaths();
		}

		//check for next point
		if(path > -1)
		{
			double nextPointX = World.paths.get(path).path[point][0];
			double nextPointY = World.paths.get(path).path[point][1];
			
			if(Coords.getDistance(posX, posY, nextPointX, nextPointY) < 20.0D)
			{
				point++;
				
				if(point >= World.paths.get(path).path.length)
				{
					path = -1;
					point = 0;
				}
			}
		}
		
		//rotating
		if(path > -1)
		{
			double nextPointX = World.paths.get(path).path[point][0];
			double nextPointY = World.paths.get(path).path[point][1];
			
			if(Coords.getDegreeFromPoint(posX, posY, nextPointX, nextPointY) < posR - 180)
			{
				posR -= 360;
			}
			if(Coords.getDegreeFromPoint(posX, posY, nextPointX, nextPointY) > posR + 180)
			{
				posR += 360;
			}
			
			if(point != 0)
			{
				double prevPointX = World.paths.get(path).path[point - 1][0];
				double prevPointY = World.paths.get(path).path[point - 1][1];
				double distance = Coords.getDistance(posX, posY, nextPointX, nextPointY) - 10;
				double direction = Coords.getDegreeFromPoint(nextPointX, nextPointY, prevPointX, prevPointY); 
				double targetPointX = Coords.getNextX(nextPointX, direction, distance); 
				double targetPointY = Coords.getNextY(nextPointY, direction, distance);
				
				double targetDirection = Coords.getDegreeFromPoint(posX, posY, targetPointX, targetPointY);
				if(targetDirection < posR)
				{
					posR -= 2D;
				}
				if(targetDirection > posR)
				{
					posR += 2D;
				}
			}
			else
			{
				if(Coords.getDegreeFromPoint(posX, posY, nextPointX, nextPointY) < posR)
				{
					posR -= 2D;
				}
				if(Coords.getDegreeFromPoint(posX, posY, nextPointX, nextPointY) > posR)
				{
					posR += 2D;
				}
			}
		}
		
		//update hitbox
		hitbox[0][0] = Coords.getNextX(Coords.getNextX(posX, posR, 10), posR + 90, 10);
		hitbox[0][1] = Coords.getNextY(Coords.getNextY(posY, posR, 10), posR + 90, 10);
		hitbox[1][0] = Coords.getNextX(Coords.getNextX(posX, posR, 10), posR - 90, 10);
		hitbox[1][1] = Coords.getNextY(Coords.getNextY(posY, posR, 10), posR - 90, 10);
		hitbox[2][0] = Coords.getNextX(Coords.getNextX(posX, posR, 30), posR + 90, 10);
		hitbox[2][1] = Coords.getNextY(Coords.getNextY(posY, posR, 30), posR + 90, 10);
		hitbox[3][0] = Coords.getNextX(Coords.getNextX(posX, posR, 30), posR - 90, 10);
		hitbox[3][1] = Coords.getNextY(Coords.getNextY(posY, posR, 30), posR - 90, 10);
		
		//check hitbox
		boolean hit = false;
		for(int v = 0; v < World.vehicles.size(); v++)
		{
			int count = 0;
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[0][0], hitbox[0][1]) < 20D)
			{
				count++;
			}
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[1][0], hitbox[1][1]) < 20D)
			{
				count++;
			}
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[2][0], hitbox[2][1]) < 20D)
			{
				count++;
			}
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[3][0], hitbox[3][1]) < 20D)
			{
				count++;
			}
			
			if(count > 3)
			{
				hit = true;
			}
		}
		
		//set speed
		if(hit)
		{
			if(cSpeed > 0)
			{
				cSpeed -= 4;
			}
		}
		else if(cSpeed < mSpeed)
		{
			cSpeed ++;
		}
		
		posX = Coords.getNextX(posX, posR, (double) cSpeed / 100D);
		posY = Coords.getNextY(posY, posR, (double) cSpeed / 100D);
	}
	
	public void lookForPaths()
	{
		int[] paths = new int[20];
		int count = 0;
		
		for(int p = 0; p < World.paths.size(); p++)
		{
			if(World.paths.get(p).path[0][0] > posX - 10 && World.paths.get(p).path[0][0] < posX + 10 &&
			   World.paths.get(p).path[0][1] > posY - 10 && World.paths.get(p).path[0][1] < posY + 10)
			{
				paths[count] = p;
				count++;
			}
		}
		
		if(count > 0)
		{
			point = 0;
			path = paths[(int) Math.floor(Math.random() * count)];
		}
	}
}
