package com.mygdx.game.utils.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Particle {
	public static Texture t = new Texture("particle.png");
	float x,y,rotation,dx,dy,dr,life,maxLife;
	boolean dead;
	Sprite spr;
	public abstract void update(float delta);
	public abstract void render(SpriteBatch sb);
}
