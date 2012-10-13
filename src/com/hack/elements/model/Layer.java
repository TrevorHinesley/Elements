package com.hack.elements.model;

public class Layer {
	
	public Tile[][] map;
	
	public Tile[][] getMap(){
		return map;
	}

	public void setMap(Tile[][] map){
		this.map = map;
	}
	
	public Tile getTile(int x, int y){
		return map[x][y];
	}
	
	public void setTile(int x, int y, Tile tile)
	{
		map[x][y] = tile;
	}
}
