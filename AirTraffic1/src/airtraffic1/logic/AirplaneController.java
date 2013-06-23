package airtraffic1.logic;

import java.util.ArrayList;

import airtraffic1.util.Coords;
import airtraffic1.world.World;

public class AirplaneController 
{
	public int id;
	public String name;
	public double posX;
	public double posY;
	public int posR;
	public boolean warning;
	public boolean dead;
	public boolean contact;

	public double feet;
	public double speed;
	
	private int routetimer;
	public ArrayList<int[]> route = new ArrayList<int[]>();
	public ArrayList<int[]> instructions = new ArrayList<int[]>();
	public int airfield;
	
	public AirplaneController(int i, String n, double x, double y, int r, double f, double s, int a)
	{
		id = i;
		name = n;
		posX = x;
		posY = y;
		posR = r;
		feet = f;
		speed = s;
		dead = false;
		warning = false;
		contact = false;
		airfield = a;
	}
	
	public void addInstruction(int x, int y, int f, int s)
	{
		instructions.add(new int[]{x,y,f,s});
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
		
		if(instructions.size() > 0)
		{	
			if(Coords.getDistance(posX, posY, instructions.get(0)[0], instructions.get(0)[1]) < 10)
			{
				instructions.remove(0);
			}
		}
			
		if(instructions.size() > 0)
		{	
			if(feet > instructions.get(0)[2])
			{
				feet -= 20;
				speed += 4;
				if(feet < instructions.get(0)[2] + 20)
				{
					feet = instructions.get(0)[2];
				}
			}
			if(feet < instructions.get(0)[2] && speed > 250)
			{
				feet += 20;
				speed -= 3;
				if(feet > instructions.get(0)[2] - 20)
				{
					feet = instructions.get(0)[2];
				}
			}
			
			if(speed > instructions.get(0)[3])
			{
				speed -= 5;
				if(speed < instructions.get(0)[3] + 5)
				{
					speed = instructions.get(0)[3];
				}
			}
			if(speed < instructions.get(0)[3])
			{
				speed += 5;
				if(speed > instructions.get(0)[3] - 5)
				{
					speed = instructions.get(0)[3];
				}
			}
			
			if(Coords.getDegreeFromPoint(posX, posY, instructions.get(0)[0], instructions.get(0)[1]) < posR - 180)
			{
				posR -= 360;
			}
			if(Coords.getDegreeFromPoint(posX, posY, instructions.get(0)[0], instructions.get(0)[1]) > posR + 180)
			{
				posR += 360;
			}
	
			if(Coords.getDegreeFromPoint(posX, posY, instructions.get(0)[0], instructions.get(0)[1]) < posR)
			{
				posR -= 1D * speed / 200;
			}
			if(Coords.getDegreeFromPoint(posX, posY, instructions.get(0)[0], instructions.get(0)[1]) > posR)
			{
				posR += 1D * speed / 200;
			}
		}
		else if(contact == false)
		{
			World.AIRFIELDS.get(airfield).requestLanding(this);
		}
		
		posX = Coords.getNextX(posX, posR, speed / 250);
		posY = Coords.getNextY(posY, posR, speed / 250);
	}
}
