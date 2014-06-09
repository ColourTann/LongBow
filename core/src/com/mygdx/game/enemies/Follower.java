package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Game;
import com.mygdx.game.player.Arrow;
import com.mygdx.game.player.Player;
import com.mygdx.game.sound.SoundLibrary;
import com.mygdx.game.utils.Colours;
import com.mygdx.game.utils.maths.BoxCollider;
import com.mygdx.game.utils.maths.CircleCollider;

public class Follower extends Enemy{
	boolean followX;
	boolean followY;
	float maxStunTime=.5f;
	
	private float stunTime;
	int width=64;
	int height=64;
	float spd=170;
	float dyingTime;
	public Follower(float x, float y, boolean followX, boolean followY, float spdMult) {
		
		super(x, y, "follower.png");
		
		if(followX&&followY)spd*=.7f;
		this.followX=followX;
		this.followY=followY;
		//spr.setOrigin(16, 16);
		spd*=spdMult;
		spr.setScale(2);
		x=Game.width-60;
		y=Game.width-60;
		
		col=new CircleCollider(x, y, spr.getWidth());
		//col=new BoxCollider(x, y, 30, 50);
	}

	@Override
	public void update(float delta) {
		
		if(dead) return;
		if(dying){
			dyingTime+=delta*4;
			spr.setColor(new Color(1, 1, 1, 1-dyingTime));
			return;
		}
		stunTime-=delta;
		
		
		
		if(stunTime>0){
			spr.setColor(Colours.white);	
		}
		else{
			spr.setColor(Colours.red);
		}
		
		
		if(stunTime>0) return;
		float checkDist=1;
		
		
		if(followX){
			if(Math.abs(x-Player.p.x)>checkDist){
				x+=spd*delta*(x<Player.p.x?1:-1);
			}
			else{
				if(Math.abs(y-Player.p.getY())>checkDist){
					y+=spd*delta*(y<Player.p.getY()?1:-1);
				}
			}
		}
		if(followY){
			if(Math.abs(y-Player.p.getY())>checkDist){
				y+=spd*delta*(y<Player.p.getY()?1:-1);
			}
			else{
				if(Math.abs(x-Player.p.x)>checkDist){
					x+=spd*delta*(x<Player.p.x?1:-1);
				}
			}
		}
		spr.setCenter(x, y);
		updateCollider(x, y);
		
	}


	@Override
	public void collide(Arrow a) {
		if(stunTime>=maxStunTime-.3f||dying||dead){
			return;
		}
		SoundLibrary.enemyHit.play(.1f);
		stun();
		
	}
	
	public void stun(){
		
		stunTime=maxStunTime;
	}

	@Override
	public boolean isDefeated() {
		return stunTime>0;
	}

	@Override
	public void destroy() {
		dying=true;
	}

	@Override
	public void debugRender() {
		col.debugDraw();
	}

	@Override
	public void updateCollider(float x, float y) {
		col.x=x;
		col.y=y;		
	}

	@Override
	public boolean killPlayerOnContact() {
		return stunTime<=0;
	}

	

}
