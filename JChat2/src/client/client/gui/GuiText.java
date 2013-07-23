package client.gui;

public class GuiText 
{
	public String text;
	public int posX;
	public int posY;
	public String align;
	public int size;
	public int[] color;
	
	public GuiText(String t, int x, int y, String a, int s, int[] c)
	{
		text = t;
		posX = x;
		posY = y;
		align = a;
		size = s;
		color = c;
	}
}
