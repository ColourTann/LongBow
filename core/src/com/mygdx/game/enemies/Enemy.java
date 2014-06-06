package com.mygdx.game.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.Arrow;
import com.mygdx.game.utils.Collider;

public abstract class Enemy {
	public static ArrayList<Enemy> enemies= new ArrayList<Enemy>();
	float x,y,dx,dy;
	Sprite spr;
	public Collider col;
	boolean dying,dead;
	
	public Enemy(float x,float y, String imgPath){
		this.x=x;
		this.y=y;
		this.spr=new Sprite(new Texture(imgPath));
	}
	public abstract void update(float delta);
	public void checkCollision(Arrow a){
		
		if(col.collidePoint(a.getArrowPoint())){
			collide(a);
		}
	}
	public abstract void collide(Arrow a);
	public void render(SpriteBatch batch){
		spr.draw(batch);
	}
	public abstract boolean killPlayerOnContact();
	public abstract boolean isDefeated();
	public abstract void destroy();
	public abstract void debugRender();
	public abstract void updateCollider(float x, float y);
}
