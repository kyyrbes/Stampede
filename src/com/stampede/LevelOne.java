package com.stampede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

// Hello there.
public class LevelOne implements Screen {
private StampedeGame game;
public LevelOne(StampedeGame game){
	this.setGame(game);
}
	@Override
	public void render(float delta) {
		if (game.bullet_count==0){
			//b//ullet.dispose();
			//animal_tex.dispose();
			//fence.dispose();
			//background.dispose();
		//	dispose();
			
		}
		System.out.print("Rendering has begun!");
		float deltaTime=Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		
		game.batch.draw(game.sprite, 0, 0);
		game.batch.draw(game.ani_sprite1, game.animal_pos1.x, game.animal_pos1.y);
		game.batch.draw(game.ani_sprite2, game.animal_pos2.x, game.animal_pos2.y);
		game.batch.draw(game.ani_sprite3, game.animal_pos3.x, game.animal_pos3.y);
		game.batch.draw(game.ani_sprite4, game.animal_pos4.x, game.animal_pos4.y);
		game.batch.draw(game.ani_sprite5, game.animal_pos5.x, game.animal_pos5.y);
		if (game.bullet_count==5){
			game.batch.draw(game.bullet_sprite1, Gdx.graphics.getWidth()-300, (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*.98f));}
		if (game.bullet_count>=4){
			game.batch.draw(game.bullet_sprite2, Gdx.graphics.getWidth()-330, (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*.98f));}
		if (game.bullet_count>=3){
			game.batch.draw(game.bullet_sprite3, Gdx.graphics.getWidth()-360, (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*.98f));}
		if (game.bullet_count>=2){
			game.batch.draw(game.bullet_sprite4, Gdx.graphics.getWidth()-390, (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*.98f));}
		if (game.bullet_count>=1){
			game.batch.draw(game.bullet_sprite5, Gdx.graphics.getWidth()-420, (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*.98f));}
		//game.bitmap_font.setColor(1, 1, 1, 1);
		//game.bitmap_font.draw(game.batch,"SCORE: "+Integer.toString(game.score), Gdx.graphics.getWidth()/2f, Gdx.graphics.getWidth()/2f);
		
		game.batch.draw(game.fence_sprite,Gdx.graphics.getWidth()-60,0);
		game.batch.end();
		
		game.animal_pos1.x += game.animal_vel1.x * deltaTime;
		game.animal_pos2.x += game.animal_vel2.x * deltaTime;
		game.animal_pos3.x += game.animal_vel3.x * deltaTime;
		game.animal_pos4.x += game.animal_vel4.x * deltaTime;
		game.animal_pos5.x += game.animal_vel5.x * deltaTime;
		
		if (game.animal_pos1.x >= Gdx.graphics.getWidth() - 100 ){ game.animal_vel1.x *= 0; game.animal_pos1.x = (Gdx.graphics.getWidth()-100);}
		if (game.animal_pos2.x >= Gdx.graphics.getWidth() - 100 ){ game.animal_vel2.x *= 0; game.animal_pos2.x = (Gdx.graphics.getWidth()-100);}
		if (game.animal_pos3.x >= Gdx.graphics.getWidth() - 100 ){ game.animal_vel3.x *= 0; game.animal_pos3.x = (Gdx.graphics.getWidth()-100);}
		if (game.animal_pos4.x >= Gdx.graphics.getWidth() - 100 ){ game.animal_vel4.x *= 0; game.animal_pos4.x = (Gdx.graphics.getWidth()-100);}
		if (game.animal_pos5.x >= Gdx.graphics.getWidth() - 100 ){ game.animal_vel5.x *= 0; game.animal_pos5.x = (Gdx.graphics.getWidth()-100);}
	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		render(0);
		
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
	public StampedeGame getGame() {
		return game;
	}
	public void setGame(StampedeGame game) {
		this.game = game;
	}

}
