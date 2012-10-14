package com.hack.elements.control;

import com.hack.elements.constants.Direction;
import com.hack.elements.model.FreeObject;

import static java.lang.Math.*;

public class FreeObjectController {
	
	public static void move(FreeObject fo, Direction direction, float speed)
	{
		int dirId = direction.id;
		int directionPositivity = (dirId/abs(dirId));
		int xDir = (directionPositivity*(abs(dirId + 1)%2));
		int yDir = (directionPositivity*(abs(dirId)%2));
		fo.setX(fo.getX() + xDir * speed);
		fo.setY(fo.getY() + yDir * speed);
		
	}
	public static void moveToPoint(FreeObject fo, int xpos, int ypos){
		fo.setX(xpos);
		fo.setY(ypos);
	}
	
	

}
