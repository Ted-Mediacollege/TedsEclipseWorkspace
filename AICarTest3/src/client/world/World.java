package client.world;

import java.util.ArrayList;

import client.entity.Entity;
import client.field.FieldRemover;
import client.field.FieldTrafficLight;
import client.path.Path;
import client.util.Coords;

public class World 
{
	public static ArrayList<FieldTrafficLight> trafficlights = new ArrayList<FieldTrafficLight>();
	public static ArrayList<FieldRemover> removers = new ArrayList<FieldRemover>();
	public static ArrayList<Path> paths = new ArrayList<Path>();
	public static ArrayList<Entity> vehicles = new ArrayList<Entity>();
	
	public int spawntimer = -1;
	public int trafficstate = 0;
	public int traffictimer = -1;
	
	public World()
	{
		addTestPaths();
	}
	
	public void tick()
	{
		for(int i = 0; i < vehicles.size(); i++)
		{
			vehicles.get(i).tick();
			
			for(int r = 0; r < removers.size(); r++)
			{
				if( World.vehicles.get(i).targetX > removers.get(r).x1 &&
					World.vehicles.get(i).targetX < removers.get(r).x2 &&
					World.vehicles.get(i).targetY > removers.get(r).y1 &&
					World.vehicles.get(i).targetY < removers.get(r).y2)
				{
					vehicles.remove(i);
					break;
				}
			}
		}
		
		if(spawntimer < 0)
		{
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(0, 506, 0, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(0, 532, 0, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(506, 960, -90, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(532, 960, -90, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(960, 428, 180, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(960, 454, 180, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(430, 0, 90, 100)); }
			if(Math.floor(Math.random() * 2) == 0) { vehicles.add(new Entity(456, 0, 90, 100)); }
			spawntimer = 100;
		}
		spawntimer--;
		
		if(traffictimer < 0)
		{
			if(trafficstate == 0)
			{
				trafficlights.get(0).closed = false;
				traffictimer = 240;
				trafficstate++;
			}
			else if(trafficstate == 1)
			{
				trafficlights.get(0).closed = true;
				traffictimer = 100;
				trafficstate++;
			}
			else if(trafficstate == 2)
			{
				trafficlights.get(1).closed = false;
				traffictimer = 240;
				trafficstate++;
			}
			else if(trafficstate == 3)
			{
				trafficlights.get(1).closed = true;
				traffictimer = 100;
				trafficstate++;
			}
			else if(trafficstate == 4)
			{
				trafficlights.get(2).closed = false;
				traffictimer = 240;
				trafficstate++;
			}
			else if(trafficstate == 5)
			{
				trafficlights.get(2).closed = true;
				traffictimer = 100;
				trafficstate++;
			}
			else if(trafficstate == 6)
			{
				trafficlights.get(3).closed = false;
				traffictimer = 240;
				trafficstate++;
			}
			else if(trafficstate == 7)
			{
				trafficlights.get(3).closed = true;
				traffictimer = 100;
				trafficstate = 0;
			}
		}
		traffictimer--;
	}
	
	public void addTestPaths()
	{
		removers.add(new FieldRemover(429 - 16, 960 - 20, 429 + 41, 960));
		removers.add(new FieldRemover(960 - 20, 532 - 40, 960, 532 + 15));
		removers.add(new FieldRemover(506 - 15, 0, 506 + 42, 0 + 20));
		removers.add(new FieldRemover(0, 454 - 40, 0 + 20, 454 + 15));
		
		trafficlights.add(new FieldTrafficLight(390, 532 - 65, 390 + 25, 532 + 35));
		trafficlights.add(new FieldTrafficLight(550, 532 - 65 - 74, 550 + 25, 532 + 35 - 74));
		trafficlights.add(new FieldTrafficLight(390, 390, 390 + 100, 390 + 20));
		trafficlights.add(new FieldTrafficLight(470, 550, 470 + 100, 550 + 20));
		
		//right
		paths.add(new Path("path", new int[]{0, 180}, new int[]{506, 506}) );
		paths.add(new Path("path", new int[]{0, 180}, new int[]{532, 532}) );
		paths.add(new Path("path", new int[]{180, 220, 480, 506, 506}, new int[]{506, 480, 480, 454, 0}) );
		paths.add(new Path("path", new int[]{180, 220, 390, 960}, new int[]{506, 506, 506, 506}) );
		paths.add(new Path("path", new int[]{180, 220, 390, 960}, new int[]{532, 532, 532, 532}) );
		paths.add(new Path("path", new int[]{180, 220, 390, 403, 429, 429}, new int[]{532, 558, 558, 570, 660, 960}) );
		
		//up
		paths.add(new Path("path", new int[]{506, 506}, new int[]{960, 780}) );
		paths.add(new Path("path", new int[]{532, 532}, new int[]{960, 780}) );
		paths.add(new Path("path", new int[]{506, 480, 480, 480, 460, 0}, new int[]{780, 744, 570, 480, 454, 454}) );
		paths.add(new Path("path", new int[]{506, 506, 506, 506}, new int[]{780, 744, 570, 0}) );
		paths.add(new Path("path", new int[]{532, 532, 532, 532}, new int[]{780, 744, 570, 0}) );
		paths.add(new Path("path", new int[]{532, 556, 556, 569, 660, 960}, new int[]{780, 744, 570, 558, 532, 532}) );
		
		//left
		paths.add(new Path("path", new int[]{960, 780}, new int[]{428, 428}) );
		paths.add(new Path("path", new int[]{960, 780}, new int[]{454, 454}) );
		paths.add(new Path("path", new int[]{780, 740, 570, 558, 532, 532}, new int[]{428, 402, 402, 388, 305, 0}) );
		paths.add(new Path("path", new int[]{780, 740, 570, 0}, new int[]{428, 428, 428, 428}) );
		paths.add(new Path("path", new int[]{780, 740, 570, 0}, new int[]{454, 454, 454, 454}) );
		paths.add(new Path("path", new int[]{780, 740, 570, 480, 455, 455}, new int[]{454, 480, 480, 480, 506, 960}) );
		
		//down
		paths.add(new Path("path", new int[]{430, 430}, new int[]{0, 180}) );
		paths.add(new Path("path", new int[]{456, 456}, new int[]{0, 180}) );
		paths.add(new Path("path", new int[]{430, 404, 404, 390, 310, 0}, new int[]{180, 220, 390, 402, 428, 428}) );
		paths.add(new Path("path", new int[]{430, 430, 430, 430}, new int[]{180, 220, 390, 960}) );
		paths.add(new Path("path", new int[]{456, 456, 456, 456}, new int[]{180, 220, 390, 960}) );
		paths.add(new Path("path", new int[]{456, 482, 482, 482, 506, 960}, new int[]{180, 220, 390, 480, 506, 506}) );
	}
}
