package com.peak.GameTest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameTest extends Game {
	
	public static int window_width = 1024;
	public static int window_height = 600;
	
	public MainMenuScreen title;
	public CharacterSelect charselect;
	public GameWorld gameworld;
	public FreeTypeFontGenerator generator;
	public BitmapFont mainFont32;
	public BitmapFont mainFont64;
	public BitmapFont mainFont72;


	
	
    public SpriteBatch batch;
   
	@SuppressWarnings("deprecation")
	@Override
	public void create () {
		
        batch = new SpriteBatch();
        title = new MainMenuScreen(this);
        charselect = new CharacterSelect(this);
        gameworld = new GameWorld(this);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("UI Elements/Furore.otf"));
        mainFont32 = generator.generateFont(32);
    	mainFont64 = generator.generateFont(64);
    	mainFont72 = generator.generateFont(72);
        
        this.setScreen(gameworld);

	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose () {
		batch.dispose();
		mainFont32.dispose();
		mainFont64.dispose();
		mainFont72.dispose();
		charselect.dispose();
		title.dispose();
	}
}
