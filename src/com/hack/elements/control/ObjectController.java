package com.hack.elements.control;

import com.hack.elements.model.FreeObject;
import com.hack.elements.model.Tile;
import com.hack.elements.model.impl.Floor;
import com.hack.elements.model.impl.Hero;

import static com.hack.elements.model.SizeConstants.*;

public class ObjectController {
	
	Tile[][] layer1;
	FreeObject hero;
	
	public ObjectController()
	{
		layer1 = new Floor[MAP_SIZE][MAP_SIZE];
		for(int i = 0; i < MAP_SIZE; i++)
			for(int j = 0; j < MAP_SIZE; j++)
				layer1[i][j] = new Floor();
		
		hero = new Hero();
		int centerOffset = ((MAP_SIZE * TILE_SIZE) - HERO_SIZE) / 2;
		hero.setX(centerOffset);
		hero.setY(centerOffset);
		hero.setWidth(HERO_SIZE);
		hero.setHeight(HERO_SIZE);
	}
}
