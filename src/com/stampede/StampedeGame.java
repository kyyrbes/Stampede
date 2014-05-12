package com.stampede;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class StampedeGame extends Game implements ApplicationListener, GestureListener {
	//setting all sorts of variables for later
    public OrthographicCamera camera;
	public SpriteBatch batch;
	Texture background;
	public Texture sprites;
	public Sprite sprite,fence_sprite, ani_sprite1, ani_sprite2, ani_sprite3, ani_sprite4, ani_sprite5, bullet_sprite1, bullet_sprite2, bullet_sprite3, bullet_sprite4, bullet_sprite5;
	private static Random randomNum = new Random();
	CameraInputController controller;
	public  GestureDetector gd = new GestureDetector(this);
	public InputMultiplexer im = new InputMultiplexer();
	public String message= "";
	public Vector2 animal_pos1,animal_pos2,animal_pos3,animal_pos4,animal_pos5;
	public Vector2 animal_vel1,animal_vel2,animal_vel3,animal_vel4,animal_vel5;
	public int bullet_count=5;
	public int score;
	public BitmapFontCache bitmap_cache;
	public BitmapFont bitmap_font;

	
	MainMenuScreen main_menu;
	GameOverScreen game_over;
	LevelOne level_one;
	
	@Override
	public void create() {
		//initiate scoreboard
		score=0;
		//FileHandle font = new FileHandle("data/arial.fnt");
		bitmap_font = new BitmapFont(true);
		bitmap_cache = new BitmapFontCache(bitmap_font);
		game_over = new GameOverScreen(this);
		level_one = new LevelOne(this);
		batch = new SpriteBatch();
		bitmap_font.setScale(2f);
		//get width and height of screen
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		//initialize animal velocities and positions
		animal_pos1 = new Vector2(0, 0); animal_vel1 = new Vector2(w/4, 0); animal_pos1.x=10; animal_pos1.y=90;
		animal_pos2 = new Vector2(0, 0); animal_vel2 = new Vector2(w/5, 0); animal_pos2.x=100; animal_pos2.y=180;
		animal_pos3 = new Vector2(0, 0); animal_vel3 = new Vector2(w/2, 0); animal_pos3.x=60; animal_pos3.y=275;
		animal_pos4 = new Vector2(0, 0); animal_vel4 = new Vector2(w/8, 0); animal_pos4.x=360; animal_pos4.y=370;
		animal_pos5 = new Vector2(0, 0); animal_vel5 = new Vector2(w/3, 0); animal_pos5.x=200; animal_pos5.y=520;
		//set camera and input settings
		camera = new OrthographicCamera();
		camera.setToOrtho(true,w,h);
		InputMultiplexer im = new InputMultiplexer();
	   // GestureDetector gd = new GestureDetector(this);
	    im.addProcessor(gd);
		Gdx.input.setInputProcessor(im);
		//create batcher and transform
		
		sprites = new Texture(Gdx.files.internal("data/sprites.png"));
		sprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		background = new Texture(Gdx.files.internal("data/backgroundsprites.png"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion region = new TextureRegion(background,2,2,1280,720);
		
		sprite = new Sprite(region);
		sprite.setSize(1f, 1f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(0,0);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		TextureRegion fence = new TextureRegion(sprites,2,2,60,720);
		fence_sprite = new Sprite(fence);
		fence_sprite.setSize(60,720);
		fence_sprite.setOrigin(w-60, -h);
		sprite.setPosition(w-60, -h);
		
		spawn_animals();
		set_bullets();
		setScreen(level_one);
	}

	@Override
	public void dispose() {
		batch.dispose();
		//background.dispose();
		
		//bullet.dispose();
		//animal_tex.dispose();
		
	}
	
	public void spawn_animals(){
	
		TextureRegion animal_tex = new TextureRegion(sprites,2,722,100,75);
		
		ani_sprite1 = new Sprite(animal_tex);
		ani_sprite1.setSize(ani_sprite1.getWidth(), ani_sprite1.getHeight());
		ani_sprite1.setOrigin(0, 0);
		ani_sprite1.setPosition(0,0);
		ani_sprite2 = new Sprite(animal_tex);
		ani_sprite2.setSize(ani_sprite2.getWidth(), ani_sprite2.getHeight());
		ani_sprite2.setOrigin(0, 0);
		ani_sprite2.setPosition(0,0);
		ani_sprite3 = new Sprite(animal_tex);
		ani_sprite3.setSize(ani_sprite3.getWidth(), ani_sprite3.getHeight());
		ani_sprite3.setOrigin(0, 0);
		ani_sprite3.setPosition(0,0);
		ani_sprite4 = new Sprite(animal_tex);
		ani_sprite4.setSize(ani_sprite4.getWidth(), ani_sprite4.getHeight());
		ani_sprite4.setOrigin(0, 0);
		ani_sprite4.setPosition(0,0);
		ani_sprite5 = new Sprite(animal_tex);
		ani_sprite5.setSize(ani_sprite5.getWidth(), ani_sprite5.getHeight());
		ani_sprite5.setOrigin(0, 0);
		ani_sprite5.setPosition(0,0);
	}
	public void set_bullets(){
		TextureRegion bullet = new TextureRegion(sprites,62,2,20,59);
		//bullet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bullet_sprite1 = new Sprite(bullet);
		bullet_sprite1.setSize(bullet_sprite1.getWidth(), bullet_sprite1.getHeight());
		bullet_sprite1.setOrigin(0, 0);
		bullet_sprite1.setPosition(0,0);
		bullet_sprite2 = new Sprite(bullet);
		bullet_sprite2.setSize(bullet_sprite2.getWidth(), bullet_sprite2.getHeight());
		bullet_sprite2.setOrigin(0, 0);
		bullet_sprite2.setPosition(0,0);
		bullet_sprite3 = new Sprite(bullet);
		bullet_sprite3.setSize(bullet_sprite3.getWidth(), bullet_sprite3.getHeight());
		bullet_sprite3.setOrigin(0, 0);
		bullet_sprite3.setPosition(0,0);
		bullet_sprite4 = new Sprite(bullet);
		bullet_sprite4.setSize(bullet_sprite4.getWidth(), bullet_sprite4.getHeight());
		bullet_sprite4.setOrigin(0, 0);
		bullet_sprite4.setPosition(0,0);
		bullet_sprite5 = new Sprite(bullet);
		bullet_sprite5.setSize(bullet_sprite5.getWidth(), bullet_sprite5.getHeight());
		bullet_sprite5.setOrigin(0, 0);
		bullet_sprite5.setPosition(0,0);
	
	}
	public void resetGame(){
	
		bullet_count=5;
		score=0;
		animal_pos1 = new Vector2(0, 0);  animal_vel1 = new Vector2(Gdx.graphics.getWidth()/4, 0);  animal_pos1.x=10;  animal_pos1.y=90;
		animal_pos2 = new Vector2(0, 0);  animal_vel2 = new Vector2(Gdx.graphics.getWidth()/5, 0);  animal_pos2.x=100;  animal_pos2.y=180;
		animal_pos3 = new Vector2(0, 0);  animal_vel3 = new Vector2(Gdx.graphics.getWidth()/3, 0);  animal_pos3.x=60;  animal_pos3.y=275;
		animal_pos4 = new Vector2(0, 0);  animal_vel4 = new Vector2(Gdx.graphics.getWidth()/8, 0);  animal_pos4.x=360;  animal_pos4.y=370;
		animal_pos5 = new Vector2(0, 0);  animal_vel5 = new Vector2(Gdx.graphics.getWidth()/3, 0);  animal_pos5.x=200;  animal_pos5.y=520;
		im.addProcessor(gd);
		Gdx.input.setInputProcessor(im);
		
		}
	
	@Override
	public void render() {	
		
		float delta = 0;
		//if (reset==true){
			
		level_one.render(delta);
		//resetGame(reset);
		if (bullet_count==0){
			//b//ullet.dispose();
			//animal_tex.dispose();
			//fence.dispose();
			//background.dispose();
		//	dispose();
		
		setScreen(game_over);
		}
		}
		//}
	public void GameOver(){
		setScreen(game_over);
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (bullet_count>=1){
		if ((x>=animal_pos1.x) &&(y>=animal_pos1.y)&&((x<=animal_pos1.x+ani_sprite1.getWidth()) &&(y<=animal_pos1.y+ani_sprite1.getHeight()))){
			animal_vel1.x=0;
			animal_vel1.y=0;
			bullet_count--;
			score += ((Gdx.graphics.getWidth()-animal_pos1.x-ani_sprite1.getWidth())*3);

			// message = ("Touch down in animal! coord: "+x+", "+y+" animal Pos:"+animal_pos1.x+", "+animal_pos1.y+" Animal dimensions: "+ani_sprite1.getWidth()+", "+ani_sprite1.getHeight());
			  // System.out.print(message);
			  // Gdx.app.log("MyLibGDXGame", message);
			}
		else if ((x>=animal_pos2.x) &&(y>=animal_pos2.y)&&((x<=animal_pos2.x+ani_sprite2.getWidth()) &&(y<=animal_pos2.y+ani_sprite2.getHeight()))){
			animal_vel2.x=0;
			animal_vel2.y=0;
			score += ((Gdx.graphics.getWidth()-animal_pos2.x-ani_sprite2.getWidth())*3);
			bullet_count--;
			}
		else if ((x>=animal_pos3.x) &&(y>=animal_pos3.y)&&((x<=animal_pos3.x+ani_sprite3.getWidth()) &&(y<=animal_pos3.y+ani_sprite3.getHeight()))){
			animal_vel3.x=0;
			animal_vel3.y=0;
			score += ((Gdx.graphics.getWidth()-animal_pos3.x-ani_sprite3.getWidth())*3);
			bullet_count--;
			}
		else if ((x>=animal_pos4.x) &&(y>=animal_pos4.y)&&((x<=animal_pos4.x+ani_sprite4.getWidth()) &&(y<=animal_pos4.y+ani_sprite4.getHeight()))){
			animal_vel4.x=0;
			animal_vel4.y=0;
			bullet_count--;
			score += ((Gdx.graphics.getWidth()-animal_pos4.x-ani_sprite4.getWidth())*3);

			}
		else if ((x>=animal_pos5.x) &&(y>=animal_pos5.y)&&((x<=animal_pos5.x+ani_sprite5.getWidth()) &&(y<=animal_pos5.y+ani_sprite5.getHeight()))){
			animal_vel5.x=0;
			animal_vel5.y=0;
			score += ((Gdx.graphics.getWidth()-animal_pos5.x-ani_sprite5.getWidth())*3);
			bullet_count--;
			}
		
	else
	{	  // message =("Touch down! coord: "+x+", "+y+" animal Pos:"+animal_pos1.x+", "+animal_pos1.y);
	//   System.out.print(message);
	//   Gdx.app.log("MyLibGDXGame", message);
		bullet_count--;
		}
		}
		return true;
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

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}
}
