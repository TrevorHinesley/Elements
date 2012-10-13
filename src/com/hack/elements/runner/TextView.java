package com.hack.elements.runner;

import com.hack.elements.control.FreeObjectController;
import com.hack.elements.control.ObjectController;
import com.hack.elements.model.FreeObject;
import com.hack.elements.model.impl.Direction;

import java.util.Random;

public class TextView {
	
	ObjectController objCon;
	
	public TextView()
	{
		
	}
	
	public void init() {
		objCon = new ObjectController();
	}
	
	public void run() {
		
		Random rand = new Random();
		for(int i = 0; i < 500; i++)
		{
			moveRandom(rand.nextInt(4));
			FreeObject hero = objCon.getModel().getHero();
			System.out.print(hero.getX() +", " + hero.getY() + " : ");
			if(hero.getFacing().id == -1)
				System.out.println("North");
			else if(hero.getFacing().id == 1)
				System.out.println("South");
			else if(hero.getFacing().id == -2)
				System.out.println("West");
			else if(hero.getFacing().id == 2)
				System.out.println("East");
			else
				System.out.println("Error");
		}
		
	}
	
	public void moveRandom(int id)
	{
		FreeObject hero = objCon.getModel().getHero();
		if (id == 0)
			FreeObjectController.move(hero, Direction.NORTH, 1);
		else if (id == 1)
			FreeObjectController.move(hero, Direction.SOUTH, 1);
		else if (id == 2)
			FreeObjectController.move(hero, Direction.EAST, 1);
		else if (id == 3)
			FreeObjectController.move(hero, Direction.WEST, 1);
			
	}

}
