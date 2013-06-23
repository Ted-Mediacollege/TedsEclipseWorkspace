package airtraffic1.logic;

import airtraffic1.util.Coords;

public class AirStripController 
{
	public double[][] stripcoords = new double[4][2];
	public double[][] arivalcoords = new double[4][2];
	public int[][] flightpath = new int[4][2];
	
	public QueueController QUEUE;
	public AirplaneController ARIVINGPLANE = null;
	
	public AirStripController(int x1, int y1, int x2, int y2, int as, int[] q)
	{
		QUEUE = new QueueController(q);
		
		stripcoords[0] = Coords.getNextXY(x1, y1, Coords.getDegreeFromPoint(x1, y1, x2, y2) + 90, 3);
		stripcoords[1] = Coords.getNextXY(x1, y1, Coords.getDegreeFromPoint(x1, y1, x2, y2) - 90, 3);
		stripcoords[2] = Coords.getNextXY(x2, y2, Coords.getDegreeFromPoint(x2, y2, x1, y1) + 90, 3);
		stripcoords[3] = Coords.getNextXY(x2, y2, Coords.getDegreeFromPoint(x2, y2, x1, y1) - 90, 3);
		
		double ad = Coords.getDegreeFromPoint(x2, y2, x1, y1) + 180;
		arivalcoords[0] = new double[]{x2,y2};
		arivalcoords[2] = Coords.getNextXY(x2, y2, ad, as);
		arivalcoords[1] = Coords.getNextXY(arivalcoords[2][0], arivalcoords[2][1], ad + 60, 11);
		arivalcoords[3] = Coords.getNextXY(arivalcoords[2][0], arivalcoords[2][1], ad - 60, 11);
	}
}
