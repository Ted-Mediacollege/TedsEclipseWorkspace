package client.gui;

import java.util.ArrayList;

import client.JCHAT2client;

public class Gui
{
	public JCHAT2client main;
	
	public int[] mousePos = new int[2];
	public ArrayList<GuiTexture> guiTextures = new ArrayList<GuiTexture>();
	public ArrayList<GuiButton> guiButtons = new ArrayList<GuiButton>();
	public ArrayList<GuiInputField> guiInputfields = new ArrayList<GuiInputField>();
	public ArrayList<GuiText> guiText = new ArrayList<GuiText>();
	
	public void create() { }
	public void destroy() { }
	public void tick() { }
	public void onAction(int id) { }
	public void onScroll(int speed) { }
	public void onKeyInput(int[] input) { }
}
