package com.hack.elements;
// this code has its collision algorithm based off of Panos Konstantinidis  code at http://thejavablog.wordpress.com/2008/06/08/using-slick-2d-to-write-a-game/
import static com.hack.elements.constants.InitConstants.*;
import static com.hack.elements.constants.SizeConstants.*;
import static com.hack.elements.constants.MoveConstants.*;

import static com.hack.elements.utils.PrintUtililites.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.hack.elements.constants.Direction;
import com.hack.elements.control.FreeObjectController;
import com.hack.elements.exceptions.BuildException;
import com.hack.elements.model.FreeObject;
import com.hack.elements.model.impl.Hero;

import java.util.HashMap;
import java.util.Map;

public class ElementsGame extends BasicGame {
	
	public final static boolean LOGGING_ON = false;
	
	private FreeObject hero;
	
	private TiledMap map;
	private int frameX;
	private int frameY;
	
	private boolean[][] blockedTiles;
	
	public Map<String, Image> imageMap;

	public ElementsGame() {
		super("Elements: THE GAME!!!");
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new ElementsGame());
		app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		logPrintln("START - init");
		
		try {
			container.setVSync(true);

			loadImages();
			buildModel();
			
			
		} catch (SlickException se) {
			throw new SlickException ("Error in initilization: " + se);
		} catch (Exception e) {
			
		}
		
		logPrintln("END - init");
	}
	
	private void buildModel() throws SlickException, BuildException
	{
		logPrintln("START - buildModel");
		
		try {
			//Load hero
			hero = new Hero();
			hero.setX(HERO_START_X);
			hero.setY(HERO_START_Y);
			hero.setWidth(HERO_SIZE);
			hero.setHeight(HERO_SIZE);
			hero.setFacing(Direction.NORTH);
			updateHeroFacing();
			
			//Load map
			map = new TiledMap("resources/base-test.tmx");
			frameX = 0;
			frameY = 0;
			
			//Load blocked tiles
			blockedTiles = new boolean[map.getWidth()][map.getHeight()];
			for (int xAxis = 0; xAxis < blockedTiles.length; xAxis++) {
				for (int yAxis = 0; yAxis < blockedTiles[xAxis].length; yAxis++) {
					
					int tileID = map.getTileId(xAxis, yAxis, 0);
					String value = map.getTileProperty(tileID, "blocked","false");

					if ("true".equals(value)) {
						blockedTiles[xAxis][yAxis] = true;
					}
				}
			}
			
		} catch (SlickException se) {
			throw new SlickException ("Slick exception building model: " + se);
		} catch (Exception e) {
			throw new BuildException ("Exception building model: " + e);
		}
		
		logPrintln("END - buildModel");
	}
	
	private void loadImages() throws SlickException{
		logPrintln("START - loadImages");
		
		imageMap = new HashMap<String, Image>();
		imageMap.put("heroU", new Image("resources/images/player-U.png"));
		imageMap.put("heroD", new Image("resources/images/player-D.png"));
		imageMap.put("heroL", new Image("resources/images/player-L.png"));
		imageMap.put("heroR", new Image("resources/images/player-R.png"));
			
		logPrintln("END - loadImages");
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		map.render(0, 0, frameX * TILES_PER_ROW, frameY * TILES_PER_COLUMN, SCREEN_WIDTH, SCREEN_HEIGHT);
		logPrintln("RENDER - MAP - 0, 0, " + frameX * SCREEN_WIDTH + ", " + frameY + SCREEN_WIDTH + ", " + SCREEN_WIDTH + ", " + SCREEN_HEIGHT);
		
		hero.getImage().draw(hero.getX(), hero.getY());
		logPrintln("RENDER - HERO - " + hero.getX() + ", " + hero.getY());
	}

	public boolean endOfScreen(int xpos, int ypos) {
		if (xpos % SCREEN_WIDTH == 0)
			return true;

		if (ypos % SCREEN_HEIGHT == 0)
			return true;

		return false;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		updateHeroPosition(input);
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
		}
	}
	
	/**
	 * For each key:
	 * 	Check if blocked
	 * 	Check if it's a border tile
	 *  Check if it's the end of the screen
	 *  Natural movement
	 *  
	 */
	private void updateHeroPosition(Input input){
		
		float prevX = hero.getX();
		float prevY = hero.getY();
		
	KEYPRESS: 
	//WASD - it's the same as 'YOURE A FUCKING RETARD'
		if(		input.isKeyDown(Input.KEY_W) && 
				input.isKeyDown(Input.KEY_A) &&
				input.isKeyDown(Input.KEY_S) &&
				input.isKeyDown(Input.KEY_D)) {	
			return;
		} else
	//WSD - it's the same as just 'D'
		if(		input.isKeyDown(Input.KEY_W) && 
				input.isKeyDown(Input.KEY_S) &&
				input.isKeyDown(Input.KEY_D)) 
		{
		
			hero.setFacing(Direction.EAST);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), hero.getFacing()))
				break KEYPRESS;
			
			FreeObjectController.move(hero, Direction.EAST, HERO_NORMAL_SPD);
			
		} else
	//WSA - it's the same as just 'A'
		if(		input.isKeyDown(Input.KEY_W) && 
				input.isKeyDown(Input.KEY_S) &&
				input.isKeyDown(Input.KEY_A)) 
		{
			hero.setFacing(Direction.WEST);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), hero.getFacing()))
				break KEYPRESS;
			
			FreeObjectController.move(hero, Direction.WEST, HERO_NORMAL_SPD);
		} else
	//ASW - it's the same as just 'W'
		if(		input.isKeyDown(Input.KEY_A) && 
				input.isKeyDown(Input.KEY_D) &&
				input.isKeyDown(Input.KEY_W)) 
		{
			hero.setFacing(Direction.NORTH);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), hero.getFacing()))
				break KEYPRESS;
		
			FreeObjectController.move(hero, Direction.NORTH, HERO_NORMAL_SPD);
			
		} else
	//ADS - it's the same as just 'S'
		if(		input.isKeyDown(Input.KEY_A) && 
				input.isKeyDown(Input.KEY_D) &&
				input.isKeyDown(Input.KEY_S)) 
		{
			hero.setFacing(Direction.SOUTH);
		
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), hero.getFacing()))
				break KEYPRESS;
			
			FreeObjectController.move(hero, Direction.SOUTH, HERO_NORMAL_SPD);
			
		} else	
	//WS - it's the same as 'YOURE A FUCKING RETARD'
		if(		input.isKeyDown(Input.KEY_W) && 
				input.isKeyDown(Input.KEY_S)){	
			return;
		} else
	//AD - it's the same as 'YOURE A FUCKING RETARD'
		if(		input.isKeyDown(Input.KEY_A) &&
				input.isKeyDown(Input.KEY_D)){	
			return;
		} else
	//WD
		if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D)) {
			if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.NORTH) &&
					!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.EAST)) {
					FreeObjectController.move(hero, Direction.NORTH, HERO_NORMAL_SPD * .71f);
					FreeObjectController.move(hero, Direction.EAST, HERO_NORMAL_SPD * .71f);
				} else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.NORTH))
					FreeObjectController.move(hero, Direction.NORTH, HERO_NORMAL_SPD);
				else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.EAST))
					FreeObjectController.move(hero, Direction.EAST, HERO_NORMAL_SPD);
			
		} else
	//WA
		if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_A)) {
			if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.NORTH) &&
				!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.WEST)) {
				FreeObjectController.move(hero, Direction.NORTH, HERO_NORMAL_SPD * .71f);
				FreeObjectController.move(hero, Direction.WEST, HERO_NORMAL_SPD * .71f);
			} else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.NORTH))
				FreeObjectController.move(hero, Direction.NORTH, HERO_NORMAL_SPD);
			else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.WEST))
				FreeObjectController.move(hero, Direction.WEST, HERO_NORMAL_SPD);
		} else
	//SD
		if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_D)) {
			if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.SOUTH) &&
					!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.EAST)) {
					FreeObjectController.move(hero, Direction.SOUTH, HERO_NORMAL_SPD * .71f);
					FreeObjectController.move(hero, Direction.EAST, HERO_NORMAL_SPD * .71f);
				} else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.SOUTH))
					FreeObjectController.move(hero, Direction.SOUTH, HERO_NORMAL_SPD);
				else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.EAST))
					FreeObjectController.move(hero, Direction.EAST, HERO_NORMAL_SPD);
		} else
	//SA
		if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)) {
			if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.SOUTH) &&
					!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.WEST)) {
					FreeObjectController.move(hero, Direction.SOUTH, HERO_NORMAL_SPD * .71f);
					FreeObjectController.move(hero, Direction.WEST, HERO_NORMAL_SPD * .71f);
				} else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.SOUTH))
					FreeObjectController.move(hero, Direction.SOUTH, HERO_NORMAL_SPD);
				else if (!isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.WEST))
					FreeObjectController.move(hero, Direction.WEST, HERO_NORMAL_SPD);
		} else
	//W
		if (input.isKeyDown(Input.KEY_W)) {
			hero.setFacing(Direction.NORTH);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.NORTH))
				break KEYPRESS;
			
			FreeObjectController.move(hero, Direction.NORTH, HERO_NORMAL_SPD);
		} else
	//S
		if (input.isKeyDown(Input.KEY_S)) {
			hero.setFacing(Direction.SOUTH);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.SOUTH))
				break KEYPRESS;
			
			FreeObjectController.move(hero, Direction.SOUTH, HERO_NORMAL_SPD);
		} else
	//D
		if (input.isKeyDown(Input.KEY_D)) {
			hero.setFacing(Direction.EAST);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.EAST))
				break KEYPRESS;
			
			FreeObjectController.move(hero, Direction.EAST, HERO_NORMAL_SPD);
		} else
	//A
		if (input.isKeyDown(Input.KEY_A)) {
			hero.setFacing(Direction.WEST);
			
			if (isBlocked(prevX, prevY, hero.getWidth(), hero.getHeight(), Direction.WEST))
				break KEYPRESS;
			
			
			FreeObjectController.move(hero, Direction.WEST, HERO_NORMAL_SPD);
		}
		
		float futureX = hero.getX();
		float futureY = hero.getY();
		
		int prevXFrame = (Math.round(prevX + (frameX * SCREEN_WIDTH)) + HERO_CENTER_OFFSET) / SCREEN_WIDTH;
		int currentXFrame = (Math.round(futureX + (frameX * SCREEN_WIDTH)) + HERO_CENTER_OFFSET) / SCREEN_WIDTH;
		int prevYFrame = (Math.round(prevY + (frameY * SCREEN_HEIGHT)) + HERO_CENTER_OFFSET) / SCREEN_HEIGHT;
		int currentYFrame = (Math.round(futureY + (frameY * SCREEN_HEIGHT)) + HERO_CENTER_OFFSET) / SCREEN_HEIGHT;
		
		if(prevXFrame < currentXFrame)
		{
			if(!isBlocked(futureX + (currentXFrame * SCREEN_WIDTH), futureY, hero.getWidth(), hero.getHeight(), Direction.EAST))
				updateView(Direction.EAST);
			else
				hero.setX(prevX);
			
		}
		else if(prevXFrame > currentXFrame)
		{
			if(!isBlocked(futureX + (currentXFrame * SCREEN_WIDTH), futureY, hero.getWidth(), hero.getHeight(), Direction.WEST))
				updateView(Direction.WEST);
			else
				hero.setX(prevX);
		}
		
		if(prevYFrame > currentYFrame) 
		{
			if(!isBlocked(futureX, futureY + (currentYFrame * SCREEN_HEIGHT), hero.getWidth(), hero.getHeight(), Direction.NORTH))
				updateView(Direction.NORTH);
			else
				hero.setY(prevY);
		}
		else if(prevYFrame < currentYFrame) 
		{
			if(!isBlocked(futureX, futureY + (currentYFrame * SCREEN_HEIGHT), hero.getWidth(), hero.getHeight(), Direction.SOUTH))
				updateView(Direction.SOUTH);
			else
				hero.setY(prevY);
		}
		
		logPrintln("frame x=" + frameX + " frame y=" + frameY);
		updateHeroFacing();
		
	} 
	
	private void updateHeroFacing() {
		if(hero.getFacing() == Direction.NORTH)
			hero.setImage(imageMap.get("heroU"));
		else if(hero.getFacing() == Direction.SOUTH)
			hero.setImage(imageMap.get("heroD"));
		else if(hero.getFacing() == Direction.EAST)
			hero.setImage(imageMap.get("heroR"));
		else if(hero.getFacing() == Direction.WEST)
			hero.setImage(imageMap.get("heroL"));
	}

	private boolean isBlocked(float x, float y, float w, float h, Direction dir) {
		if(dir == Direction.NORTH){
			
			//Check if map border
			if(y + (frameY * SCREEN_HEIGHT) - 2 < 0)
				return true;
			
			int indexX1 = (Math.round(x) / TILE_SIZE)  + (TILES_PER_ROW * frameX);
			int indexX2 = (Math.round(x + w) / TILE_SIZE)  + (TILES_PER_ROW * frameX);
			int indexY = (Math.round(y - 2) / TILE_SIZE) + (TILES_PER_COLUMN * frameY);
			
			logPrint("x1=" + indexX1 + " ");
			logPrint("x2=" + indexX2 + " ");
			logPrint("y=" + indexY + "\n");
			
			return blockedTiles[indexX1][indexY] || blockedTiles[indexX2][indexY];
		}
		else if (dir == Direction.SOUTH) {
			
			//Check if map edge
			if(y + (frameY * SCREEN_HEIGHT) + h + 2 > (map.getHeight() * map.getTileHeight()))
				return true;
			
			int indexX1 = (Math.round(x) / TILE_SIZE) + (TILES_PER_ROW * frameX);
			int indexX2 = (Math.round(x + w) / TILE_SIZE)  + (TILES_PER_ROW * frameX);
			int indexY = (Math.round(y + h + 2) / TILE_SIZE) + (TILES_PER_COLUMN * frameY);
			
			logPrint("x1=" + indexX1 + " ");
			logPrint("x2=" + indexX2 + " ");
			logPrint("y=" + indexY + "\n");
			
			return blockedTiles[indexX1][indexY] || blockedTiles[indexX2][indexY];
		}
		else if (dir == Direction.EAST) {
			
			//Check if map edge
			if(	x + w + (frameX * SCREEN_WIDTH) + 2 >= (map.getWidth() * map.getTileWidth()))
				return true;
			
			int indexX = (Math.round(x + w + 2) / TILE_SIZE)  + (TILES_PER_ROW * frameX);
			int indexY1 = (Math.round(y) / TILE_SIZE) + (TILES_PER_COLUMN * frameY);
			int indexY2 = (Math.round(y + h) / TILE_SIZE) + (TILES_PER_COLUMN * frameY);
			
			return blockedTiles[indexX][indexY1] || blockedTiles[indexX][indexY2];
		}
		else if (dir == Direction.WEST) {
			
			//Check if map edge
			if(x + (frameX * SCREEN_WIDTH) - 2 <=  0)
				return true;
			
			int indexX = (Math.round(x - 2) / TILE_SIZE)  + (TILES_PER_ROW * frameX);
			int indexY1 = (Math.round(y) / TILE_SIZE) + (TILES_PER_COLUMN * frameY);
			int indexY2 = (Math.round(y + h) / TILE_SIZE) + (TILES_PER_COLUMN * frameY);
			
			return blockedTiles[indexX][indexY1] || blockedTiles[indexX][indexY2];
		}
		else 
			return true;
	} 
	
	private void updateView(Direction dir)
	{
		if(dir == Direction.NORTH){
			if(frameY > 0) {
				frameY--;
				hero.setY(hero.getY() + SCREEN_HEIGHT);
			}
		}
		else if (dir == Direction.SOUTH) {
			if(frameY < map.getHeight() -1) {
				frameY++;
				hero.setY(hero.getY() - SCREEN_HEIGHT);
			}
		}
		else if (dir == Direction.EAST) {
			if(frameX < map.getWidth() -1) {
				frameX++;
				hero.setX(hero.getX() - SCREEN_WIDTH);
			}
		}
		else if (dir == Direction.WEST) {
			if(frameX > 0) {
				frameX--;
				hero.setX(hero.getX() + SCREEN_WIDTH);
			}
		}
	}
}
