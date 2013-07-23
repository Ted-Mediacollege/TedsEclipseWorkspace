package com.ted80.tileworld.gui;

import com.ted80.tileworld.TileWorld;

abstract public class Gui 
{
	public TileWorld main;
	
	public Gui(TileWorld m)
	{
		main = m;
	}
	
	abstract public void tick();
}
