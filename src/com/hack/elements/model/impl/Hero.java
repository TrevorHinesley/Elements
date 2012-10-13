package com.hack.elements.model.impl;

import com.hack.elements.model.FreeObject;

public class Hero implements FreeObject {

	private int x;
	private int y;
	private int width;
	private int height;
<<<<<<< HEAD
	
	public boolean usesDirection;
=======

	
>>>>>>> 679e67d378f15973407a1b2e3beefd37e889a81b
	private Direction facing;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}
<<<<<<< HEAD
	
	@Override
	public boolean usesDirection(){
		return true;
	}
=======
>>>>>>> 679e67d378f15973407a1b2e3beefd37e889a81b
	
	@Override
	public Direction getFacing() {
		return facing;
	}
	
	@Override
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
}
