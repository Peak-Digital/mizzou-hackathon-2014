package com.peak.GameTest;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import static com.peak.GameTest.B2DVars.PPM;

public class GameWorld implements Screen {

	final GameTest game;
	
	World world = new World(new Vector2(0, -10), true);
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	float[] goodVertices = { 100 / PPM, 100 / PPM, 100 / PPM, 200 / PPM, 
							 200 / PPM, 100 / PPM, 200 / PPM, 200 / PPM };
	
	float[] evilVertices = { 800 / PPM, 100 / PPM, 800 / PPM, 200 / PPM,
							 900 / PPM, 100 / PPM, 900 / PPM, 200 / PPM };
	
	Body squareBody;
	Body evilSquareBody;

	OrthographicCamera B2DCam;

	Texture background;
	//Sprite spriteGround;
	
	public GameWorld(final GameTest gam) {
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameTest.window_width, GameTest.window_height);

		B2DCam = new OrthographicCamera();
		B2DCam.setToOrtho(false, GameTest.window_width / PPM,
				GameTest.window_height / PPM);

		//Make the background image//
		background = new Texture(Gdx.files.internal("city.jpg"));
		game.batch.begin();
		game.batch.draw(background, camera.viewportWidth, camera.viewportHeight);
		game.batch.end();
		//spriteGround = new Sprite(background);
		//spriteGround.setSize(camera.viewportWidth, camera.viewportHeight);
		//SpriteBatch.spriteGround.
		//spriteGround.draw(game.batch);

		//////Make the ground for Cthulu//////

		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0 / PPM, 10 / PPM);
		groundBodyDef.type = BodyType.StaticBody;
		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape ground = new PolygonShape();
		ground.setAsBox(camera.viewportWidth / PPM, 0.0f / PPM);
		groundBody.createFixture(ground, 0.0f);
		ground.dispose();
		
		/////Make the left wall //////
		BodyDef leftBodyDef = new BodyDef();
		leftBodyDef.position.set(0 / PPM, 10 / PPM);
		leftBodyDef.type = BodyType.StaticBody;
		Body leftBody = world.createBody(leftBodyDef);

		PolygonShape leftWall = new PolygonShape();
		leftWall.setAsBox(0.0f / PPM, camera.viewportHeight / PPM);
		leftBody.createFixture(leftWall, 0.0f);
		leftWall.dispose();
		
        /////Make the right wall //////
		BodyDef rightBodyDef = new BodyDef();
		rightBodyDef.position.set(camera.viewportWidth / PPM,  0/ PPM);
		rightBodyDef.type = BodyType.StaticBody;
		Body rightBody = world.createBody(rightBodyDef);

		PolygonShape rightWall = new PolygonShape();
		rightWall.setAsBox(0 / PPM, camera.viewportHeight / PPM);
		rightBody.createFixture(rightWall, 0.0f);
		rightWall.dispose();
			
		 /////Make the top walls //////
		BodyDef topBodyDef = new BodyDef();
		topBodyDef.position.set(camera.viewportWidth / PPM, camera.viewportHeight / PPM);
		topBodyDef.type = BodyType.StaticBody;
		Body topBody = world.createBody(topBodyDef);

		PolygonShape topWall = new PolygonShape();
		topWall.setAsBox(camera.viewportWidth / PPM, 0 / PPM);
		topBody.createFixture(topWall, 0.0f);
		topWall.dispose();

		//////Make a Square for Cthulu/////////
		BodyDef squareBodyDef = new BodyDef();
		squareBodyDef.type = BodyType.DynamicBody;
		squareBodyDef.position.set(100 / PPM, 300 / PPM);
		squareBody = world.createBody(squareBodyDef);

		PolygonShape square = new PolygonShape();
		square.set(goodVertices);

		FixtureDef squareFixtureDef = new FixtureDef();
		squareFixtureDef.shape = square;
		squareFixtureDef.density = 0.5f;
		squareFixtureDef.friction = 0.5f;
		squareFixtureDef.restitution = 0.0f;

		@SuppressWarnings("unused") // We need the fixture despite eclipse's beliefs
		Fixture squareFixture = squareBody.createFixture(squareFixtureDef);
		square.dispose();

		//////Make a Bad Square for Cthulhu/////////
		BodyDef evilBodyDef = new BodyDef();
		evilBodyDef.type = BodyType.DynamicBody;
		evilBodyDef.position.set(100 / PPM, 300 / PPM);
		evilSquareBody = world.createBody(evilBodyDef);

		PolygonShape evilSquare = new PolygonShape();
		evilSquare.set(evilVertices);

		FixtureDef evilFixtureDef = new FixtureDef();
		evilFixtureDef.shape = evilSquare;
		evilFixtureDef.density = 0.5f;
		evilFixtureDef.friction = 0.5f;
		evilFixtureDef.restitution = 0.0f;

		@SuppressWarnings("unused") // We need the fixture despite eclipse's beliefs
		Fixture evilFixture = evilSquareBody.createFixture(evilFixtureDef);
		evilSquare.dispose();

	}

	@Override
	public void render(float delta) {
		float speed = 10f;
		
		if (Gdx.input.isKeyPressed(Keys.A))
			squareBody.applyForceToCenter(new Vector2(-speed, 0), true);
		if (Gdx.input.isKeyPressed(Keys.D))
			squareBody.applyForceToCenter(new Vector2(speed, 0), true);
		if (Gdx.input.isKeyPressed(Keys.W))
			squareBody.applyForceToCenter(new Vector2(0, speed), true);
		if (Gdx.input.isKeyPressed(Keys.S))
			squareBody.applyForceToCenter(new Vector2(0, -speed), true);
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			evilSquareBody.applyForceToCenter(new Vector2(-speed, 0), true);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			evilSquareBody.applyForceToCenter(new Vector2(speed, 0), true);
		if (Gdx.input.isKeyPressed(Keys.UP))
			evilSquareBody.applyForceToCenter(new Vector2(0, speed), true);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			evilSquareBody.applyForceToCenter(new Vector2(0, -speed), true);

		Gdx.gl.glClearColor(0.23f, 0.23f, 0.21f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		B2DCam.update();
		// game.batch.setProjectionMatrix(camera.combined);
		world.step(1 / 60f, 6, 2);
		debugRenderer.render(world, B2DCam.combined);
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