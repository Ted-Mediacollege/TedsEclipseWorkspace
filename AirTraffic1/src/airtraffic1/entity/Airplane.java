package airtraffic1.entity;

import java.util.ArrayList;

import airtraffic1.util.Coords;
import airtraffic1.world.World;

public class Airplane 
{
	public String name;
	public double posX;
	public double posY;
	public int posR;
	public double feet;
	public double speed;
	public boolean warning;
	public boolean dead;
	
	private int routetimer;
	public ArrayList<int[]> route = new ArrayList<int[]>();
	public ArrayList<Integer> plannedPoints = new ArrayList<Integer>();
	
	public Airplane(String n, double x, double y, int r, double f, double s)
	{
		name = n;
		posX = x;
		posY = y;
		posR = r;
		feet = f;
		speed = s;
		dead = false;
		warning = false;
	}
	
	public void tick()
	{
		routetimer--;
		if(routetimer < 0)
		{
			route.add(new int[]{(int) Math.floor(posX),(int) Math.floor(posY)});
			if(route.size() > 10)
			{
				route.remove(0);
			}
			routetimer = 2;
		}
		
		if(plannedPoints.size() > 0)
		{	
			if(Coords.getDistance(posX, posY, World.points.get(plannedPoints.get(0)).posX, World.points.get(plannedPoints.get(0)).posY) < 10)
			{
				plannedPoints.remove(0);
			}
		}
			
		if(plannedPoints.size() > 0)
		{	
			if(Coords.getDegreeFromPoint(posX, posY, World.points.get(plannedPoints.get(0)).posX, World.points.get(plannedPoints.get(0)).posY) < posR - 180)
			{
				posR -= 360;
			}
			if(Coords.getDegreeFromPoint(posX, posY, World.points.get(plannedPoints.get(0)).posX, World.points.get(plannedPoints.get(0)).posY) > posR + 180)
			{
				posR += 360;
			}
	
			if(Coords.getDegreeFromPoint(posX, posY, World.points.get(plannedPoints.get(0)).posX, World.points.get(plannedPoints.get(0)).posY) < posR)
			{
				posR -= 1D * speed / 200;
			}
			if(Coords.getDegreeFromPoint(posX, posY, World.points.get(plannedPoints.get(0)).posX, World.points.get(plannedPoints.get(0)).posY) > posR)
			{
				posR += 1D * speed / 200;
			}
		}
		
		posX = Coords.getNextX(posX, posR, speed / 250);
		posY = Coords.getNextY(posY, posR, speed / 250);
	}
}
