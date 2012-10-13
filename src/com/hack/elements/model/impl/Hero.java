package com.hack.elements.model.impl;

import com.hack.elements.model.FreeObject;

public class Hero implements FreeObject {

	private int x;
	private int y;
	private int width;
	private int height;
<<<<<<< HEAD
	
	private Direction facing;
=======
	private Direction direction;
	public boolean usesDirection;
>>>>>>> 855bcd66999ca7f5088f0fb554ac75883a7c2e6d

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
	public boolean usesDirction(){
		return true;
	}
	
	@Override
	public Direction getFacing() {
		return facing;
	}
	
	public void setFacing(Direction facing) {
		this.facing = facing;
=======

	@Override
	public enum getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction d) {
		this.direction = d;
>>>>>>> 855bcd66999ca7f5088f0fb554ac75883a7c2e6d
	}
}
