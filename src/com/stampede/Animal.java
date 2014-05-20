package com.stampede;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animal {
    /** Health points of the animal. */
    protected int hp;
    /** Row the animal is in. */
    protected int row;
    /** Percent across the screen that the animal is. */
    protected float horizPercentage;
    /** Horizontal velocity measured in percentage of screen per second. */
    protected float velocity;
    /** Sprite representation of the animal. */
    protected Sprite sprite;

    public Animal(int hp, int row, float horizPercentage, float velocity, Sprite sprite) {
        this.hp = hp;
        this.row = row;
        this.horizPercentage = horizPercentage;
        this.velocity = velocity;
        this.sprite = sprite;
    }

    public int getHP() {
        return hp;
    }

    public int getRow() {
        return row;
    }

    public float getHorizPercentage() {
        return horizPercentage;
    }

    public void setHorizPercentage(float horizPercentage) {
        this.horizPercentage = horizPercentage;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
