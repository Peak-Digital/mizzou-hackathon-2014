package com.peak.GameTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class CharacterSelect implements Screen {

    final GameTest game;
    
    Texture backTexture = new Texture(Gdx.files.internal("UI Elements/back.png"));
    Texture backClickedTexture = new Texture(Gdx.files.internal("UI Elements/back_clicked.png"));
    Sprite back = new Sprite(backTexture, backTexture.getWidth(), backTexture.getHeight());
    
    

    OrthographicCamera camera;

    public CharacterSelect(final GameTest gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameTest.window_width, GameTest.window_height);
        
        initUI();

    }
    
    
    
    public void initUI()
    {
    	back.setTexture(backTexture);
    }
    

    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0.35f, 0.35f, 0.69f, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        //Set positions of sprites.
        back.setPosition(camera.viewportWidth / 8f, camera.viewportHeight / 8f); //Lower Left
        
        
        //Draw sprites.
        game.batch.begin();
        game.mainFont64.draw(game.batch, "CHOOSE A CHARACTER", camera.viewportWidth / 10f + camera.viewportWidth / 32f, camera.viewportHeight - camera.viewportHeight / 4f); //Draw in center.
        back.draw(game.batch);
        game.batch.end();
        
        
        Gdx.input.setInputProcessor(new InputAdapter () {
        	int leftMouse = 0;
        	Vector3 touchPos;
        	
        	   public boolean touchUp (int x, int y, int pointer, int button) 
        	   {
        		   //Mouse cursor is currently not clicked.
        		   
        		  touchPos = new Vector3(x, y, 0); //When the screen is touched, the location is passed into a vector.
        		  camera.unproject(touchPos); //Calibrate the camera based on the location of the click.
        		  
        		  //Clicked start button.
         	      if (button == leftMouse && back.getBoundingRectangle().contains(touchPos.x, touchPos.y))
         	      {
                   	  System.out.printf("Clicked Back  %d, %d, %d, %d\n", x, y, pointer, button);
                   	  back.setTexture(backTexture);
                   	  game.setScreen(game.title);
                   	  CharacterSelect.this.dispose();
         	      }

        	      return true; //Indicate that the event was actually handled.
        	   }
        	   
        	   public boolean touchDown (int x, int y, int pointer, int button) 
        	   {
        		   //Mouse cursor is currently clicked.

        		   
        		  touchPos = new Vector3(x, y, 0); //When the screen is touched, the location is passed into a vector.
        		  camera.unproject(touchPos); //Calibrate the camera based on the location of the click.
        		  
        		  //Clicked start button.
         	      if (button == leftMouse && back.getBoundingRectangle().contains(touchPos.x, touchPos.y))
         	      {
                   	  back.setTexture(backClickedTexture);
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