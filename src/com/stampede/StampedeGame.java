package com.stampede;

import java.util.Random;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.*;;

class GameState {
    /** Current score obtained by the player. */
    public int score;
    /** Number of darts available to the user. */
    public int numDarts;
    /** The animals present in the game. */
    public ArrayList<Animal> animals;

    public GameState(int score, int numDarts, ArrayList<Animal> animals) {
        this.score = score;
        this.numDarts = numDarts;
        this.animals = animals;
    }
}

class GameResources {
    /** Used for drawing batches of sprites. */
    public SpriteBatch spriteBatch;
    /** Texture containing the normal sprites to use in-game. */
    public Texture spriteTexture;
    /** Texture containing the background sprites to use in-game. */
    public Texture backgroundSpriteTexture;
    /** Camera. */
    public OrthographicCamera camera;

    public GameResources(SpriteBatch spriteBatch, Texture spriteTexture, Texture backgroundSpriteTexture, OrthographicCamera camera)
    {
        this.spriteBatch = spriteBatch;
        this.spriteTexture = spriteTexture;
        this.backgroundSpriteTexture = backgroundSpriteTexture;
        this.camera = camera;
    }

    public void dispose() {
        spriteBatch.dispose();
        spriteTexture.dispose();
        backgroundSpriteTexture.dispose();
    }
}

public class StampedeGame extends Game {
    /** Contains game state that will change as the game progresses. */
    private GameState state;
    /** Contains resources to be used for graphics and such throughout. */
    private GameResources resources;

    // Screens. They are made public so that other screens can access
    // them to switch the active game screen.
    public LevelOne levelOne;
    public GameOverScreen gameOverScreen;

    @Override
    public void create() {
        // Set up the game resources.
        OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        SpriteBatch spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
        resources = new GameResources(
            spriteBatch,
            new Texture(Gdx.files.internal("data/sprites.png")),
            new Texture(Gdx.files.internal("data/backgroundsprites.png")),
            camera);
        levelOne = new LevelOne(this);
        gameOverScreen = new GameOverScreen(this);

        // Set up the game state.
        state = new GameState(0, 5, new ArrayList<Animal>());

        // Start with the level one screen.
        setScreen(levelOne);
    }

	@Override
	public void dispose() {
        resources.dispose();
	}

    public GameState getState() {
        return state;
    }

    public GameResources getResources() {
        return resources;
    }
}
