package com.hack.elements.control;

import static com.hack.elements.model.SizeConstants.HERO_SIZE;
import static com.hack.elements.model.SizeConstants.MAP_SIZE;
import static com.hack.elements.model.SizeConstants.TILE_SIZE;

import com.hack.elements.model.GameModel;
import com.hack.elements.model.Layer;
import com.hack.elements.model.Tile;
import com.hack.elements.model.impl.Floor;
import com.hack.elements.model.impl.Hero;

public class ObjectController {
	
	GameModel gm;
	
	public ObjectController()
	{
		gm = new GameModel();
		
		Tile[][] layer1 = new Floor[MAP_SIZE][MAP_SIZE];
		for(int i = 0; i < MAP_SIZE; i++)
			for(int j = 0; j < MAP_SIZE; j++)
				layer1[i][j] = new Floor();
		
		gm.setLayer(1, new Layer());
		gm.getLayer(1).setMap(layer1);
		
		gm.setHero(new Hero());
		
		int centerOffset = ((MAP_SIZE * TILE_SIZE) - HERO_SIZE) / 2;
		gm.getHero().setX(centerOffset);
		gm.getHero().setY(centerOffset);
		gm.getHero().setWidth(HERO_SIZE);
		gm.getHero().setHeight(HERO_SIZE);
	}
	
	public GameModel getModel()
	{
		return gm;
	}
}
