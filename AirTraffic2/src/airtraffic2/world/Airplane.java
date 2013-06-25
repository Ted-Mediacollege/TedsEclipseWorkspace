package airtraffic2.world;

import java.util.ArrayList;

import airtraffic2.util.Coords;

public class Airplane 
{
	public int id;
	public String name;
	
	public double posX;
	public double posY;
	public double posZ;
	public double posR;
	public double speed;
	
	public boolean warning;
	public boolean dead;
	
	public int airfield;
	public int airstrip;
	
	public ArrayList<double[]> instructions = new ArrayList<double[]>();

	private int historytimer;
	public ArrayList<int[]> history = new ArrayList<int[]>();
	
	public Airplane(int i, String n, double x, double y, double z, double r, double s)
	{
		id = i;
		name = n;
		
		posX = x;
		posY = y;
		posZ = z;
		posR = r;
		speed = s;
	}
	
	public void giveInstruction(double x, double y, double z, double s)
	{
		instructions.add(new double[]{x,y,z,s});
	}
	
	public void update()
	{
		checkInstructions();
		adjustDirection();
		adjustHeightAndSpeed();
		updateHistory();
		
		posX = Coords.getNextX(posX, posR, speed / 250);
		posY = Coords.getNextY(posY, posR, speed / 250);
	}
	
	public void checkInstructions()
	{
		if(instructions.size() > 0)
		{	
			if(Coords.getDistance(posX, posY, instructions.get(0)[0], instructions.get(0)[1]) < 10)
			{
				instructions.remove(0);
			}
		}
	}
	
	public void adjustDirection()
	{
		if(instructions.size() > 0)
		{				
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
	}
	
	public void adjustHeightAndSpeed()
	{
		if(instructions.size() > 0)
		{	
			if(posZ > instructions.get(0)[2])
			{
				posZ -= 10;
				if(posZ < instructions.get(0)[2] + 20)
				{
					posZ = instructions.get(0)[2];
				}
			}
			if(posZ < instructions.get(0)[2])
			{
				posZ += 10;
				if(posZ > instructions.get(0)[2] - 20)
				{
					posZ = instructions.get(0)[2];
				}
			}
			
			if(speed > instructions.get(0)[3])
			{
				if(posZ > 200) { speed -= 3; } else { speed -= 1; }
				if(speed < instructions.get(0)[3] + 5)
				{
					speed = instructions.get(0)[3];
				}
			}
			if(speed < instructions.get(0)[3])
			{
				speed += 3;
				if(speed > instructions.get(0)[3] - 5)
				{
					speed = instructions.get(0)[3];
				}
			}
		}
	}
	
	public void updateHistory()
	{
		historytimer--;
		if(historytimer < 0)
		{
			history.add(new int[]{(int) Math.floor(posX),(int) Math.floor(posY)});
			if(history.size() > 15)
			{
				history.remove(0);
			}
			historytimer = 3;
		}
	}
}
