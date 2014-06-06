package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;

public class Bow{
	static float xOrig=9.5f;
	static float yOrig=0;
	static float xpo=-12;
	static float ypo=10;
	float x;
	float y;
	float angle=0;
	int currentFrame=0;
	int bowFacing;
	public Sprite[] frames= new Sprite[4];
	public Bow(){
		
		Texture t=new Texture("bow.png");
		for(int i=0;i<4;i++){
			frames[i]=new Sprite(t, 22*i, 0, 22, 28);
			frames[i].setOrigin(xOrig, frames[i].getHeight()/2+yOrig);
			frames[i].setScale(3, 3);
		}
		
		
		
	}
	public void update(float delta, Player p, int frame){
		float mx=Gdx.input.getX();
		float my=Game.height-Gdx.input.getY();
		currentFrame=frame;
		Sprite spr=frames[frame];
		x=p.x+xpo;
		y=p.y+ypo+p.height/2;
		spr.setPosition(x, y);
		
		float facing=mx-x>0?1:-1;
		spr.setScale(facing*3,3);
		bowFacing=(int)facing;

		if(facing==1){
			spr.setOrigin(Bow.xOrig, spr.getHeight()/2+Bow.yOrig);
		}
		else{
			spr.setPosition(p.x-3, p.y+Bow.ypo+p.height/2);
		}
		
		
		angle=(float) Math.atan2(mx-x, y-my);
		angle*=57.3;
		if(angle<0)angle+=180;
		angle-=90;
		
		spr.setRotation((float) angle);
	}
	public void render(SpriteBatch sb){
		
		frames[currentFrame].draw(sb);
	}
}
