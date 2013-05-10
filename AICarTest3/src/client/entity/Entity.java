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
	public double targetX;
	public double targetY;
	
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
		checkForNewPaths();
		checkForNextPoint();
		rotate();
		updateHitboxes();
		boolean[] checks = checkHitboxes();
		updateSpeed(checks[0], checks[1]);
		
		posX = Coords.getNextX(posX, posR, (double) cSpeed / 100D);
		posY = Coords.getNextY(posY, posR, (double) cSpeed / 100D);
	}
	
	public void checkForNewPaths()
	{
		if(path == -1) 
		{
			path = -1;
			
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
	
	public void checkForNextPoint()
	{
		if(path > -1) 
		{ 
			double nextPointX = World.paths.get(path).path[point][0];
			double nextPointY = World.paths.get(path).path[point][1];
			
			if(Coords.getDistance(posX, posY, nextPointX, nextPointY) < 10.0D)
			{
				point++;
				
				if(point >= World.paths.get(path).path.length)
				{
					path = -1;
					point = 0;
				}
			}
		}
	}
	
	public void rotate()
	{
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
	}
	
	public void updateSpeed(boolean hit, boolean trafficlight)
	{
		if(hit || trafficlight)
		{
			if(cSpeed > 4)
			{
				cSpeed -= 5;
			}
		}
		else if(cSpeed < mSpeed)
		{
			cSpeed += 5;
		}
	}
	
	public void updateHitboxes()
	{
		targetX = Coords.getNextX(posX, posR, 40);
		targetY = Coords.getNextY(posY, posR, 40);
		hitbox[0][0] = Coords.getNextX(Coords.getNextX(posX, posR, 10), posR + 90, 15);
		hitbox[0][1] = Coords.getNextY(Coords.getNextY(posY, posR, 10), posR + 90, 15);
		hitbox[1][0] = Coords.getNextX(Coords.getNextX(posX, posR, 10), posR - 90, 15);
		hitbox[1][1] = Coords.getNextY(Coords.getNextY(posY, posR, 10), posR - 90, 15);
		hitbox[2][0] = Coords.getNextX(Coords.getNextX(posX, posR, 40), posR + 90, 15);
		hitbox[2][1] = Coords.getNextY(Coords.getNextY(posY, posR, 40), posR + 90, 15);
		hitbox[3][0] = Coords.getNextX(Coords.getNextX(posX, posR, 40), posR - 90, 15);
		hitbox[3][1] = Coords.getNextY(Coords.getNextY(posY, posR, 40), posR - 90, 15);
	}
	
	public boolean[] checkHitboxes()
	{
		boolean[] checks = new boolean[2];
		
		checks[0] = false;
		for(int v = 0; v < World.vehicles.size(); v++)
		{
			int count = 0;
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[0][0], hitbox[0][1]) < 30D)
			{
				count++;
			}
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[1][0], hitbox[1][1]) < 30D)
			{
				count++;
			}
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[2][0], hitbox[2][1]) < 30D)
			{
				count++;
			}
			if(Coords.getDistance(World.vehicles.get(v).posX, World.vehicles.get(v).posY, hitbox[3][0], hitbox[3][1]) < 30D)
			{
				count++;
			}
				
			if(count > 3)
			{
				checks[0] = true;
			}
		}
		
		checks[1] = false;
		for(int t = 0; t < World.trafficlights.size(); t++)
		{
			if( targetX > World.trafficlights.get(t).x1 &&
				targetX < World.trafficlights.get(t).x2 &&
				targetY > World.trafficlights.get(t).y1 &&
				targetY < World.trafficlights.get(t).y2 &&
				World.trafficlights.get(t).closed == true)
			{
				checks[1] = true;
			}
		}
		
		return checks;
	}
}
