package com.stampede;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;

public class GameOverScreen implements Screen, GestureListener {
    /** Reference back to the game object. */
    private StampedeGame game;
    /** Font to use for rendering text. */
    private BitmapFont textFont;
    /** Sprite for the game over text. */
    private Sprite gameOverSprite;
    /** Sprite for the try again text, */
    private Sprite tryAgainSprite;

    public GameOverScreen(StampedeGame game) {
        this.game = game;
        textFont = new BitmapFont(true);
        textFont.setColor(1, 1, 1, 1);

        // Create the sprites.
		TextureRegion gameOverRegion = new TextureRegion(game.getResources().backgroundSpriteTexture, 2, 724, 1280, 720);
		gameOverSprite = new Sprite(gameOverRegion);
		TextureRegion tryAgainRegion = new TextureRegion(game.getResources().spriteTexture, 84, 2, 136, 34);
		tryAgainSprite = new Sprite(tryAgainRegion);
		tryAgainSprite.setOrigin(Gdx.graphics.getWidth() / 2 - tryAgainRegion.getRegionWidth() / 2, Gdx.graphics.getHeight() * 0.6f);
    }

    @Override
    public void render(float delta) {
        // Draw the sprites.
        SpriteBatch spriteBatch = game.getResources().spriteBatch;
        spriteBatch.begin();
        spriteBatch.draw(
            gameOverSprite,
            0, 0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());
        spriteBatch.draw(
            tryAgainSprite,
            Gdx.graphics.getWidth() / 2 - tryAgainSprite.getWidth() / 2,
            Gdx.graphics.getHeight() * 0.6f);
        textFont.draw(
            spriteBatch,
            "SCORE: " + Integer.toString(game.getState().score) +
                "\n HIGH: 0" /*+ prefs.getInteger("highscore", 0)*/,
            Gdx.graphics.getWidth() / 2.37f, Gdx.graphics.getHeight() - 200);
        spriteBatch.end();
    }

	@Override
	public void show() {
        // Set this screen up to handle user input.
		InputMultiplexer im = new InputMultiplexer();
	    GestureDetector detector = new GestureDetector(this);
	    im.addProcessor(detector);
		Gdx.input.setInputProcessor(im);

        // Render the screen.
		render(0);
	}

    @Override
	public boolean touchDown(float x, float y, int pointer, int button) {
        game.levelOne.reset();
        game.setScreen(game.levelOne);

        return true;
    }

    // Unused methods.
	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public boolean longPress(float x, float y) { return false; }

	@Override
	public boolean fling(float velocityX, float velocityY, int button) { return false; }

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) { return false; }

	@Override
	public boolean panStop(float x, float y, int pointer, int button) { return false; }

	@Override
	public boolean zoom(float initialDistance, float distance) { return false; }

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
        Vector2 pointer1, Vector2 pointer2) { return false; }

	@Override
	public boolean tap(float x, float y, int count, int button) { return false; }
}
