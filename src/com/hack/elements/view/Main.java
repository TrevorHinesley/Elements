package com.hack.elements.view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;
import com.hack.elements.control.FreeObjectController;
import com.hack.elements.control.ObjectController;
import com.hack.elements.model.FreeObject;
import com.hack.elements.model.impl.Direction;

public class Main extends BasicGame {
	int mxpos;
	int mypos;
	ObjectController controller;
	private TiledMap map;

	public Main() {
		super("FUCK!");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main());

		app.setDisplayMode(512, 512, false);
		app.start();

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		controller = new ObjectController();
		controller.getModel().getHero()
				.setImage(new Image("resources/images/player-U.png"));
		container.setVSync(true);
		map = new TiledMap("resources/test2.tmx");

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		FreeObject hero = controller.getModel().getHero();
		map.render(mxpos, mypos);
		hero.getImage().draw(hero.getX(), hero.getY());
	}

	public boolean endOfScreen(int xpos, int ypos) {
		if (xpos % 512 == 0 )
			
		if (xpos == map.getWidth())
			return true;
		if (ypos % 512 == 0 || ypos == map.getHeight())
			return true;

		return false;
	}

	public boolean borderTile(int xpos, int ypos) {
		return true;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
		FreeObject hero = controller.getModel().getHero();
		
        if(input.isKeyDown(Input.KEY_A))
        {
        	FreeObjectController.move(hero, Direction.WEST, 1.4f);
        	hero.setImage(new Image("resources/images/player-L.png"));
        }
        if(input.isKeyDown(Input.KEY_D))
        {
        	FreeObjectController.move(hero, Direction.EAST, 1.4f);
        	hero.setImage(new Image("resources/images/player-R.png"));
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
        	FreeObjectController.move(hero, Direction.NORTH, 1.4f);
        	hero.setImage(new Image("resources/images/player-U.png"));
        }
            
        if(input.isKeyDown(Input.KEY_S))
        {
        	FreeObjectController.move(hero, Direction.SOUTH, 1.4f);
        	hero.setImage(new Image("resources/images/player-D.png"));
        }
        if(input.isKeyDown(Input.KEY_SPACE))
        { }
        if(input.isKeyDown(Input.KEY_ESCAPE))
        { }
	}
}
