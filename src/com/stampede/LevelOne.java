package com.stampede;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;

public class LevelOne implements Screen, GestureListener {
    /** Reference back to the game object. */
    private StampedeGame game;
    /** Sprite for the background of the level. */
    private Sprite backgroundSprite;
    /** Number of rows of animals. */
    private static final int NUM_ROWS = 5;
    /** Size of each row. */
    private float rowSize;

    public LevelOne(StampedeGame game) {
        this.game = game;
		TextureRegion backgroundTextureRegion =
            new TextureRegion(game.getResources().backgroundSpriteTexture,2,2,1280,720);
        backgroundSprite = new Sprite(backgroundTextureRegion);
        rowSize = Gdx.graphics.getHeight() / NUM_ROWS;
    }

    public void reset() {
        game.getState().score = 0;
        game.getState().numDarts = 5;
		TextureRegion cowRegion = new TextureRegion(game.getResources().spriteTexture,2,724,100,75);
        game.getState().animals.clear();
        for(int i = 0; i < NUM_ROWS; ++i) {
            float randomVelocity = ((float)Math.random() * 25.0f) + 5.0f;
            game.getState().animals.add(new Animal(100, i, 0, randomVelocity, new Sprite(cowRegion)));
        }
    }

    @Override
    public void render(float delta) {
        // UDPATE THE STATE OF THE GAME.
        updateGameState(game.getState(), Gdx.graphics.getDeltaTime());

        // RENDER THE GAME.
        SpriteBatch spriteBatch = game.getResources().spriteBatch;
        spriteBatch.begin();

        // Draw the background.
        spriteBatch.draw(backgroundSprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        // Draw the animals.
        for(Animal animal : game.getState().animals) {
            spriteBatch.draw(
                animal.getSprite(),
                calculateXPos(animal.getHorizPercentage()),
                calculateYPos(animal.getRow()));
        }

        spriteBatch.end();
    }

    public void updateGameState(GameState gameState, float deltaTime) {
        for(Animal animal : gameState.animals) {
            float newHorizPercentage =
                animal.getHorizPercentage() + (deltaTime * animal.velocity);
            animal.setHorizPercentage(newHorizPercentage);
        }
    }

    public float calculateXPos(float horizPercentage) {
        return (horizPercentage / 100) * Gdx.graphics.getWidth();
    }

    public float calculateYPos(int row) {
        return (float)row * rowSize;
    }

	@Override
	public void show() {
        // Set this screen up to handle user input.
		InputMultiplexer im = new InputMultiplexer();
	    GestureDetector detector = new GestureDetector(this);
	    im.addProcessor(detector);
		Gdx.input.setInputProcessor(im);

        // Reset the game state.
        reset();

        // Render the screen.
		render(0);
	}

    @Override
	public boolean touchDown(float x, float y, int pointer, int button) {
        // Check if an animal was hit. TODO: Fix potential performance issue??
        Vector2 touchPoint = new Vector2(x, y);
        for(Animal animal : game.getState().animals) {
            Vector2 animalPosition = new Vector2(
                calculateXPos(animal.getHorizPercentage()),
                calculateYPos(animal.getRow()));
            if((x >= animalPosition.x && (x <= animalPosition.x + animal.sprite.getWidth())) &&
               (y >= animalPosition.y && (y <= animalPosition.y + animal.sprite.getHeight())))
            {
                animal.setVelocity(0);
                game.getState().score += 1000;
            }
        }

        // Indicate that a dart was used.
        --game.getState().numDarts;
        if(game.getState().numDarts <= 0) {
            game.setScreen(game.gameOverScreen);
        }

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
