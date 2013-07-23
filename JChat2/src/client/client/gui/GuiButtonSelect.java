package client.gui;

public class GuiButtonSelect extends GuiButton
{
	public String[] textarray;
	public int arrayPos = 0;
	
	public GuiButtonSelect(int i, String[] t, int x, int y, int s) 
	{
		super(i, t[0], x, y, s);
		textarray = t;
	}
	
	@Override
	public void click()
	{
		arrayPos++;
		if(arrayPos > textarray.length - 1)
		{
			arrayPos = 0;
		}
		text = textarray[arrayPos];
	}
}
