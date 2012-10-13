package com.hack.elements.model;

public class GameModel {
	
	Layer layer1;
	FreeObject hero;
	
	public Layer getLayer(int id)
	{
		if(id == 1)
			return layer1;
		else
			return null;
	}
	
	public void setLayer(int id, Layer layer){
		if(id == 1)
			layer1 = layer;
	}
	
	public FreeObject getHero()
	{
		return hero;
	}
	
	public void setHero(FreeObject fo)
	{
		hero = fo;
	}

}
