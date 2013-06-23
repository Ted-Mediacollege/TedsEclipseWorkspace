package airtraffic1.logic;

import java.util.ArrayList;

public class QueueController 
{
	public ArrayList<AirplaneController> AIRPLANES_QUEUE = new ArrayList<AirplaneController>();
	public int[] queuepoints = new int[2];
	
	public QueueController(int[] p)
	{
		queuepoints = p;
	}
}
