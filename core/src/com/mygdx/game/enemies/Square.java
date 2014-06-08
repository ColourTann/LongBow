package com.mygdx.game.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.player.Arrow;
import com.mygdx.game.player.Player;
import com.mygdx.game.utils.maths.BoxCollider;
import com.mygdx.game.utils.maths.Sink;

public class Square extends Enemy {
	float spd=1000;
	Sink direction;
	public Square(float x, float y) {
		super(x, y, "square.png");
		//spr.setOriginCenter();
		spr.setScale(3);
		col=new BoxCollider(x-spr.getWidth()/2*3, y-spr.getHeight()/2*3, 32*3, 32*3);
		direction=new Sink(1,0);
	}

	@Override
	public void update(float delta) {
		float newX=direction.x+x;
		float newY=direction.y+y;
		if(newX>Game.width-80||newX<80||newY<80||newY>Game.height-80){
			direction.rotate();
		}
		
		x+=direction.x*delta*spd;
		y+=direction.y*delta*spd;
		
		col.x=x-spr.getWidth()/2*3;
		col.y=y-spr.getWidth()/2*3;
		
		spr.setX(x-spr.getWidth()/2);
		spr.setY(y-spr.getHeight()/2);
	}

	@Override
	public void collide(Arrow a) {
		a.stickIn(this);
	}

	@Override
	public boolean killPlayerOnContact() {
		return false;
	}

	@Override
	public boolean isDefeated() {
		return false;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void debugRender() {
	}

	@Override
	public void updateCollider(float x, float y) {
	}

}
