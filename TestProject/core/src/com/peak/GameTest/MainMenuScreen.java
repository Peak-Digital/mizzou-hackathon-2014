package com.peak.GameTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {

    final GameTest game;
    
    public boolean isDisposed = false;
    
    Texture startTexture = new Texture(Gdx.files.internal("UI Elements/start.png"));
    Texture exitTexture = new Texture(Gdx.files.internal("UI Elements/exit.png"));
    Texture startClickedTexture = new Texture(Gdx.files.internal("UI Elements/start_clicked.png"));
    Texture exitClickedTexture = new Texture(Gdx.files.internal("UI Elements/exit_clicked.png"));
    Sprite start = new Sprite(startTexture, startTexture.getWidth(), startTexture.getHeight());
    Sprite exit = new Sprite(exitTexture, exitTexture.getWidth(), startTexture.getHeight());
    
    

    OrthographicCamera camera;

    public MainMenuScreen(final GameTest gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameTest.window_width, GameTest.window_height);
        
        initUI();

    }
    
    
    
    public void initUI()
    {
    	start.setTexture(startTexture);
    	exit.setTexture(exitTexture);
    }
    

    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0.23f, 0.23f, 0.21f, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        //Set positions of sprites.
        start.setPosition(camera.viewportWidth / 8f, camera.viewportHeight / 8f); //Lower Left
        exit.setPosition(camera.viewportWidth / 2f + camera.viewportWidth /5f , camera.viewportHeight /8f); //Lower Right
        
        
        //Draw sprites.
        game.batch.begin();
        game.mainFont72.draw(game.batch, "TITLE HERE", camera.viewportWidth / 6f + camera.viewportWidth /8f, camera.viewportHeight - camera.viewportHeight / 4f); //Draw in center.
        start.draw(game.batch);
        exit.draw(game.batch);
        game.batch.end();
        
        
        Gdx.input.setInputProcessor(new InputAdapter () {
        	int leftMouse = 0;
        	Vector3 touchPos;
        	
        	   public boolean touchUp (int x, int y, int pointer, int button) {
        		   //Mouse cursor is currently not clicked.
        		   
        		  touchPos = new Vector3(x, y, 0); //When the screen is touched, the location is passed into a vector.
        		  camera.unproject(touchPos); //Calibrate the camera based on the location of the click.
        		  
        		  //Clicked start button.
         	      if (button == leftMouse && start.getBoundingRectangle().contains(touchPos.x, touchPos.y))
         	      {
                   	  System.out.printf("Clicked Start  %d, %d, %d, %d\n", x, y, pointer, button);
                   	  start.setTexture(startTexture);
                   	  isDisposed = true;
                   	  game.setScreen(game.charselect);
         	      }
         	      
         	      else if (button == leftMouse && exit.getBoundingRectangle().contains(touchPos.x, touchPos.y))
         	      {
                   	  System.out.printf("Clicked Exit  %d, %d, %d, %d\n", x, y, pointer, button);
                   	  start.setTexture(exitTexture);
                   	  Gdx.app.exit();

         	      }
         	    	  
        	      return true; //Indicate that the event was actually handled.
        	   }
        	   
        	   public boolean touchDown (int x, int y, int pointer, int button) {
        		   //Mouse cursor is currently clicked.

        		   
        		  touchPos = new Vector3(x, y, 0); //When the screen is touched, the location is passed into a vector.
        		  camera.unproject(touchPos); //Calibrate the camera based on the location of the click.
        		  
        		  //Clicked start button.
         	      if (button == leftMouse && start.getBoundingRectangle().contains(touchPos.x, touchPos.y))
         	      {
                   	  start.setTexture(startClickedTexture);
         	      }
         	      
         	      else if (button == leftMouse && exit.getBoundingRectangle().contains(touchPos.x, touchPos.y))
         	      {
                   	  exit.setTexture(exitClickedTexture);

         	      }
         	    	  
        	      return true; //Indicate that the event was actually handled.
        	   }
        	});

    }
    
    

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



}