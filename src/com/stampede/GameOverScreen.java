package com.stampede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;


public class GameOverScreen implements Screen, GestureListener{
	private Sprite game_over, try_again;
	private StampedeGame game;
	Preferences prefs;
	private int hs;
	public GameOverScreen(StampedeGame game){
		this.setGame(game);
		prefs = Gdx.app.getPreferences("Preferences");
		
		
	}
		@Override
	public void render(float delta) {
			System.out.print("Rendering has begun!");
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			game.batch.setProjectionMatrix(game.camera.combined);
			game.batch.begin();
			game.batch.draw(game_over, 0, 0);
			game.batch.draw(try_again, Gdx.graphics.getWidth()/2-try_again.getWidth()/2, Gdx.graphics.getHeight()*.6f);
			game.bitmap_font.setColor(1, 1, 1, 1);
			game.bitmap_font.draw(game.batch,"SCORE: "+Integer.toString(game.score)+"\n HIGH: "+prefs.getInteger("highscore",0), Gdx.graphics.getWidth()/2.37f, Gdx.graphics.getHeight()-200);
		    game.batch.end();
				
		    }
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//Texture gameover = new Texture(Gdx.files.internal("data/gameover.png"));
		//gameover.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gd = new GestureDetector(this);
		im.addProcessor(gd);
		Gdx.input.setInputProcessor(im);
		
		
		if (game.score>prefs.getInteger("highscore", 0)){
			prefs.putInteger("highscore", game.score);
			prefs.flush();
			
		game.message = Integer.toString(prefs.getInteger("highscore"));
		Gdx.app.log("MyLibGDXGame", game.message);
		}
			
		TextureRegion gameover = new TextureRegion(game.background,2,724,1280,720);
		TextureRegion tryagain = new TextureRegion(game.sprites,84,2,136,34);
		try_again = new Sprite(tryagain);
		
		try_again.setOrigin(Gdx.graphics.getWidth()/2-try_again.getRegionWidth()/2, Gdx.graphics.getHeight()*.6f);
		game_over = new Sprite(gameover);
		
		render(0);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		//gameover.dispose();
		//tryagain.dispose();
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
	public StampedeGame getGame() {
		return game;
	}
	public void setGame(StampedeGame game) {
		this.game = game;
	}
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if ((x>=560) &&(y>=410)&&((x<=760) &&(y<=500))){
			game.message = ("Touch down! coord: "+x+", "+y);
			 // System.out.print(message);
			Gdx.app.log("MyLibGDXGame", game.message);
	    	
	    	game.resetGame();
		}
		return true;
	}
	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
