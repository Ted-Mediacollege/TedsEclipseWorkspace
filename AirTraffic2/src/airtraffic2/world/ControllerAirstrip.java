package airtraffic2.world;

import java.util.ArrayList;

import airtraffic2.util.Coords;

public class ControllerAirstrip 
{
	public ArrayList<Integer> AIRPLANES_QUEUE = new ArrayList<Integer>();
	public ArrayList<Integer> AIRPLANES_ARIVAL = new ArrayList<Integer>();
	
	public double[][] stripcoords = new double[4][2];
	public double[][] arivalcoords = new double[4][2];
	public double[][] flightpath = new double[6][4];
	public int[] queuepoints = new int[2];
	
	public int queuetimer = 0;
	
	public ControllerAirstrip(int x1, int y1, int x2, int y2, int as, int q[])
	{
		stripcoords[0] = Coords.getNextXY(x1, y1, Coords.getDegreeFromPoint(x1, y1, x2, y2) + 90, 3);
		stripcoords[1] = Coords.getNextXY(x1, y1, Coords.getDegreeFromPoint(x1, y1, x2, y2) - 90, 3);
		stripcoords[2] = Coords.getNextXY(x2, y2, Coords.getDegreeFromPoint(x2, y2, x1, y1) + 90, 3);
		stripcoords[3] = Coords.getNextXY(x2, y2, Coords.getDegreeFromPoint(x2, y2, x1, y1) - 90, 3);
		
		double ad = Coords.getDegreeFromPoint(x2, y2, x1, y1) + 180;
		arivalcoords[0] = new double[]{x2,y2};
		arivalcoords[2] = Coords.getNextXY(x2, y2, ad, as);
		arivalcoords[1] = Coords.getNextXY(arivalcoords[2][0], arivalcoords[2][1], ad + 60, 11);
		arivalcoords[3] = Coords.getNextXY(arivalcoords[2][0], arivalcoords[2][1], ad - 60, 11);
		
		flightpath[5] = new double[]{x1, y1, 0, 0};
		flightpath[4] = new double[]{Coords.getNextX(x2, ad - 180, 40), Coords.getNextY(y2, ad - 180, 40), 100, 100};
		flightpath[3] = new double[]{x2, y2, 200, 180};
		flightpath[2] = new double[]{Coords.getNextX(x2, ad, 40), Coords.getNextY(y2, ad, 40), 400, 190};
		flightpath[1] = new double[]{Coords.getNextX(x2, ad, 120), Coords.getNextY(y2, ad, 120), 800, 220};
		flightpath[0] = new double[]{Coords.getNextX(x2, ad, 250), Coords.getNextY(y2, ad, 250), 1200, 300};
		
		queuepoints[0] = q[0];
		queuepoints[1] = q[1];
	}
	
	public void tick()
	{
		queuetimer--;
		if(queuetimer < 0)
		{
			if(AIRPLANES_QUEUE.size() > 0)
			{
				int id = AIRPLANES_QUEUE.get(0);
				AIRPLANES_ARIVAL.add(AIRPLANES_QUEUE.get(0));
				AIRPLANES_QUEUE.remove(0);
				queuetimer = 80;

				for(int b = 0; b < World.AIRPLANES.size(); b++)
				{
					if(id == World.AIRPLANES.get(b).id)
					{
						World.AIRPLANES.get(b).instructions.clear();
						ControllerAirstrip strip = World.AIRFIELDS.get(World.AIRPLANES.get(b).airfield).AIRSTRIPS.get(World.AIRPLANES.get(b).airstrip);
						World.AIRPLANES.get(b).giveInstruction(strip.flightpath[0][0],strip.flightpath[0][1],strip.flightpath[0][2],strip.flightpath[0][3]);
						World.AIRPLANES.get(b).giveInstruction(strip.flightpath[1][0],strip.flightpath[1][1],strip.flightpath[1][2],strip.flightpath[1][3]);
						World.AIRPLANES.get(b).giveInstruction(strip.flightpath[2][0],strip.flightpath[2][1],strip.flightpath[2][2],strip.flightpath[2][3]);
						World.AIRPLANES.get(b).giveInstruction(strip.flightpath[3][0],strip.flightpath[3][1],strip.flightpath[3][2],strip.flightpath[3][3]);
						World.AIRPLANES.get(b).giveInstruction(strip.flightpath[4][0],strip.flightpath[4][1],strip.flightpath[4][2],strip.flightpath[4][3]);
						World.AIRPLANES.get(b).giveInstruction(strip.flightpath[5][0],strip.flightpath[5][1],strip.flightpath[5][2],strip.flightpath[5][3]); 
					}
				}
			}
		}

		for(int i = 0; i < AIRPLANES_QUEUE.size(); i++)
		{
			for(int j = 0; j < World.AIRPLANES.size(); j++)
			{
				if(AIRPLANES_QUEUE.get(i) == World.AIRPLANES.get(j).id)
				{
					if(World.AIRPLANES.get(j).instructions.size() < 1)
					{
						World.AIRPLANES.get(j).giveInstruction(World.POINTS.get(queuepoints[0]).posX, World.POINTS.get(queuepoints[0]).posY, 1200 + (i * 200), 300);
						World.AIRPLANES.get(j).giveInstruction(World.POINTS.get(queuepoints[1]).posX, World.POINTS.get(queuepoints[1]).posY, 1200 + (i * 200), 300);
					}
				}
			}
		}
		
		for(int i = 0; i < AIRPLANES_ARIVAL.size(); i++)
		{
			for(int j = 0; j < World.AIRPLANES.size(); j++)
			{
				if(AIRPLANES_ARIVAL.get(i) == World.AIRPLANES.get(j).id)
				{
					if(World.AIRPLANES.get(j).posZ < 150)
					{
						World.AIRPLANES.get(j).dead = true;
					}
					
					if(World.AIRPLANES.get(j).posZ < 50)
					{
						AIRPLANES_ARIVAL.remove(i);
						World.AIRPLANES.remove(j);
						break;
					}
				}
			}
		}
	}
	
	public void register(int id)
	{
		AIRPLANES_QUEUE.add(id);
	}
	
}

