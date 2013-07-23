package client.gui;

public class GuiButton 
{
	public int id = -1;
	public String text = "";
	public int posX = 0;
	public int posY = 0;
	public int size = 0;
	public boolean enabled = true;
	
	public GuiButton(int i, String t, int x, int y, int s)
	{
		id = i;
		text = t;
		posX = x;
		posY = y;
		size = s;
	}

	public void click()
	{
	}
}
