package com.hack.elements.constants;

public enum Direction {

	NORTH(-1), SOUTH(1), WEST(-2), EAST(2);
	
	public final int id;
	
	Direction(int id) {
		this.id = id;
	}
}