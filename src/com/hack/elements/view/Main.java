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
	int mxpos = 0;
	int mypos = 0;
	ObjectController controller;
	private TiledMap map;
	int cxpos;
	int cypos;
	static int screenw = 512;
	static int screenh = 512;

	public Main() {
		super("FUCK!");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main());

		app.setDisplayMode(screenw, screenh, false);
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
		map.render(0, 0, mxpos, mypos, screenw, screenh);
		hero.getImage().draw(hero.getX(), hero.getY());
	}

	public boolean endOfScreen(int xpos, int ypos) {
		if (xpos % screenw == 0)
			return true;

		if (ypos % screenh == 0)
			return true;

		return false;
	}

	public boolean borderTile(int xpos, int ypos) {
<<<<<<< HEAD
		if (cxpos == 0 || cxpos == map.getWidth() * map.getTileWidth())
			return true;
		if (cypos == 0 || cypos == map.getHeight() * map.getTileHeight())
			return true;

		return false;
=======
		return true;
>>>>>>> 7d164ad83d86c90cadcf065c17e6f8207f69e47f
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
<<<<<<< HEAD
		FreeObject hero = controller.getModel().getHero();
		cxpos = (int) hero.getX();
		cypos = (int) hero.getY();

		if (input.isKeyDown(Input.KEY_D)) {
			hero.setImage(new Image("resources/images/player-R.png"));
			if (borderTile(cxpos, cypos))
				return;
			else if (endOfScreen(cxpos, cypos)) {
				mxpos = (mxpos) + (screenw / 32);
				cxpos = (cxpos - screenw) + 1;
				FreeObjectController.moveToPoint(hero, cxpos, cypos);

			}

			else
				FreeObjectController.move(hero, Direction.EAST, .5f);
		}
		if (input.isKeyDown(Input.KEY_A)) {
			hero.setImage(new Image("resources/images/player-L.png"));
			if (borderTile(cxpos, cypos)) {
				cxpos = cxpos++;
				FreeObjectController.moveToPoint(hero, cxpos, cypos);
			} else if (endOfScreen(cxpos - 1, cypos)) {
				System.out.println("end of screen");
				cxpos = (cxpos + screenw) - 1;
				System.out
						.println("cxpos = " + cxpos + ", screenw :" + screenw);
				System.out.println("mxpos : " + mxpos);
				mxpos = (mxpos) - (screenw / 32);
				System.out.println("mxpos : " + mxpos);

				FreeObjectController.moveToPoint(hero, cxpos, cypos);

			}

			else
				FreeObjectController.move(hero, Direction.WEST, .5f);
		}
		if (input.isKeyDown(Input.KEY_W)) {
			hero.setImage(new Image("resources/images/player-U.png"));
			if (borderTile(cxpos, cypos)) {
				cypos = cypos + 8;
				FreeObjectController.moveToPoint(hero, cxpos, cypos);

				return;
			} else if (endOfScreen(cxpos, cypos)) {
				System.out.println(mypos);
				mypos = (mypos) - (screenh / 32);
				System.out.println(mypos);
				System.out.println(cypos);
				cypos = (cypos + screenh) - 1;
				System.out.println(cypos);
				FreeObjectController.moveToPoint(hero, cxpos, cypos);

			}

			else
				FreeObjectController.move(hero, Direction.NORTH, .5f);
		}
		if (input.isKeyDown(Input.KEY_S)) {
			hero.setImage(new Image("resources/images/player-D.png"));
			if (borderTile(cxpos, cypos))
				return;
			else if (endOfScreen(cxpos, cypos)) {
				mypos = (mypos) + (screenh / 32);
				cypos = cypos - screenh + 1;
				FreeObjectController.moveToPoint(hero, cxpos, cypos);

			}

			else
				FreeObjectController.move(hero, Direction.SOUTH, .5f);
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
		}

	}
=======
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
>>>>>>> 7d164ad83d86c90cadcf065c17e6f8207f69e47f
}
