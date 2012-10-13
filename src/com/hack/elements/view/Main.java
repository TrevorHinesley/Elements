package com.hack.elements.view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Main extends BasicGame {
	
	Image player = null;

    public Main() {
		super("FUCK!");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new Main());
 
         app.setDisplayMode(512, 512, false);
         app.start();
    }

	/**
	 * @param args
	 */


	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		float x = 247;
		float y = 247;
		player.draw(x, y);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		player = new Image("resources/images/player-U.png");
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
		 
        if(input.isKeyDown(Input.KEY_A))
        {
        	player = new Image("resources/images/player-L.png");
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
        	player = new Image("resources/images/player-R.png");
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
        	player = new Image("resources/images/player-U.png");
        }
            
        if(input.isKeyDown(Input.KEY_S))
        {
        	player = new Image("resources/images/player-D.png");
        }
            
 
            
            
        
 
        if(input.isKeyDown(Input.KEY_SPACE))
        {
        	player = new Image("resources/images/player-U.png");
        }
        if(input.isKeyDown(Input.KEY_ESCAPE))
        {
            
            
        }
        
		
	}
	
	
}
