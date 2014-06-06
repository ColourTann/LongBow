package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;

public class Arm extends Sprite{
	
	float x;
	float y;
	float angle=0;
	public Arm(){
		super(new Texture("backarm.png"));
		setOrigin(Bow.xOrig, this.getHeight()/2+Bow.yOrig);
		setPosition(x, y);
		setScale(3, 3);

	}
	public void update(float delta, Player p){
		float mx=Gdx.input.getX();
		float my=Game.height-Gdx.input.getY();
		x=p.x+Bow.xpo;
		y=p.y+Bow.ypo+p.height/2;
		setPosition(x, y);
		float facing=mx-x>0?1:-1;
		setScale(facing*3,3);


		if(facing==1){
			setOrigin(Bow.xOrig, this.getHeight()/2+Bow.yOrig);
		}
		else{
			setPosition(p.x-6, p.y+Bow.ypo+p.height/2);
		}
		
		
		
		double angle=Math.atan2(mx-x, y-my);
		angle*=57.3;
		
		if(angle<0)angle+=180;
		
		
		angle-=90;
		
		
	
		
		setRotation((float) angle);
	}
	public void render(SpriteBatch sb){
		this.draw(sb);
	}
}
