package laser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Laser extends JFrame
{
	private static final long serialVersionUID = 1L;
	public Painter paint;
	private Timer ticktimer;
	private Timer restarttimer;

	public float[] lpX = new float[4];
	public float[] lpY = new float[4];
	public float[] ldX = new float[4];
	public float[] ldY = new float[4];
	
	public boolean lasering = true;

	public Laser()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1900 + 6, 980 + 28);
		setLocationRelativeTo(null);
		setTitle("LASER TEST");
		setResizable(false);
		
		paint = new Painter();
		add(paint);
		
		setVisible(true);
		
		restart();
		
		tick();
		ticktimer = new Timer((int) Math.floor(1000 / 60), new ActionListener() {
			public void actionPerformed(ActionEvent e) { tick(); } });  
		ticktimer.start();
		
		restarttimer = new Timer(1000 * 8, new ActionListener() {
			public void actionPerformed(ActionEvent e) { restart(); } });  
		restarttimer.start();
	}
	
	public void restart()
	{
		ObjectsData.objectinfo = new int[64][6];
		ObjectsData.objectlenght = 0;
		
		LaserData.Laserinfo = new float[4][51200][4];
		LaserData.laserlenght = new int[4];
		
		createObjects();
		
		for(int i = 0; i < 4; i++)
		{
			LaserData.Laserinfo[i][0][0] = 10 + (i * 200F);
			LaserData.Laserinfo[i][0][1] = 1;
			LaserData.Laserinfo[i][0][2] = 1;
			LaserData.Laserinfo[i][0][3] = 1;
			lpX[i] = 1F + (i * 200F);
			lpY[i] = 1F;
			ldX[i] = 0.05F;
			ldY[i] = 0.05F;
			LaserData.laserlenght[i] = 1;
		}
	}
	
	public void tick()
	{
		if(lasering == true)
		{
			for(int l = 0; l < 4; l++)
			{
				for(int i = 0; i < 3000; i++)
				{
					if(LaserData.laserlenght[l] < LaserData.Laserinfo[0].length - 1)
					{
						if(lpX[l] < 0 || lpX[l] > 1900) { ldX[l] = -ldX[l]; createLaserPart(l, lpX[l], lpY[l]); }
						if(lpY[l] < 0 || lpY[l] > 980) { ldY[l] = -ldY[l]; createLaserPart(l, lpX[l], lpY[l]); }
						
						for(int obj = 0; obj < ObjectsData.objectlenght; obj++)
						{
							if(
									lpX[l]+1 > ObjectsData.objectinfo[obj][0] && 
									lpY[l] > ObjectsData.objectinfo[obj][1] && 
									lpX[l]+1 < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l] < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldX[l] = -ldX[l]; createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							else if(
									lpX[l] > ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 > ObjectsData.objectinfo[obj][1] && 
									lpX[l] < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldY[l] = -ldY[l]; createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							else if(
									lpX[l]-1 > ObjectsData.objectinfo[obj][0] && 
									lpY[l] > ObjectsData.objectinfo[obj][1] && 
									lpX[l]-1 < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l] < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldX[l] = -ldX[l]; createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							else if(
									lpX[l] > ObjectsData.objectinfo[obj][0] && 
									lpY[l]-1 > ObjectsData.objectinfo[obj][1] && 
									lpX[l] < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l]-1 < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldY[l] = -ldY[l]; createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							

							else if(
									lpX[l]+1 > ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 > ObjectsData.objectinfo[obj][1] && 
									lpX[l]+1 < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldY[l] = -ldY[l]; 
								ldX[l] = -ldX[l];
								createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							else if(
									lpX[l]-1 > ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 > ObjectsData.objectinfo[obj][1] && 
									lpX[l]-1 < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldY[l] = -ldY[l]; 
								ldX[l] = -ldX[l];
								createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							else if(
									lpX[l]-1 > ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 > ObjectsData.objectinfo[obj][1] && 
									lpX[l]-1 < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l]+1 < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldY[l] = -ldY[l]; 
								ldX[l] = -ldX[l];
								createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
							
							else if(
									lpX[l]-1 > ObjectsData.objectinfo[obj][0] && 
									lpY[l]-1 > ObjectsData.objectinfo[obj][1] && 
									lpX[l]-1 < ObjectsData.objectinfo[obj][2] + ObjectsData.objectinfo[obj][0] && 
									lpY[l]-1 < ObjectsData.objectinfo[obj][3] + ObjectsData.objectinfo[obj][1]
								) 
							{ 
								ldY[l] = -ldY[l]; 
								ldX[l] = -ldX[l];
								createLaserPart(l, lpX[l], lpY[l]); 
								ObjectsData.objectinfo[obj][4] = 0;
								ObjectsData.objectinfo[obj][5] = 50;
							}
						}
					}
					
					lpX[l] += ldX[l];
					lpY[l] += ldY[l];
					LaserData.Laserinfo[l][LaserData.laserlenght[l] - 1][2] = lpX[l];
					LaserData.Laserinfo[l][LaserData.laserlenght[l] - 1][3] = lpY[l];
				}
			}
		}
		
		paint.repaint();
	}
	
	public static void main(String[] args) 
	{
		new Laser();
	}
	
	public void createLaserPart(int l, float x, float y)
	{
		if(LaserData.laserlenght[l] < 51200-5)
		{
			LaserData.laserlenght[l]++;
			LaserData.Laserinfo[l][LaserData.laserlenght[l] - 1][0] = x;
			LaserData.Laserinfo[l][LaserData.laserlenght[l] - 1][1] = y;
			LaserData.Laserinfo[l][LaserData.laserlenght[l] - 1][2] = x;
			LaserData.Laserinfo[l][LaserData.laserlenght[l] - 1][3] = y;
		}
	}
	
	public void createObjects()
	{
		for(int i = 0; i < 64; i++)
		{
			int size = (int) Math.floor(Math.random() * 80) + 50;
			
			ObjectsData.createObject(
				(int) Math.floor(Math.random() * 1500) + 100, 
				(int) Math.floor(Math.random() * 600) + 100, 
				size, 
				size
			);
		}
	}
}
