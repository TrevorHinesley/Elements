package com.hack.elements.model.impl;

import com.hack.elements.model.Tile;

public class Floor implements Tile {
	
	@Override
	public boolean isCollidable(){
		return false;
	}
}
