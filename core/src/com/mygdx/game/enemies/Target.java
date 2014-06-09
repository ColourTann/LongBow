package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.player.Arrow;
import com.mygdx.game.sound.SoundLibrary;
import com.mygdx.game.utils.Colours;
import com.mygdx.game.utils.maths.CircleCollider;

public class Target extends Enemy{
float dyingTicks;
	public Target(float x, float y) {
		super(x, y, "target.png");
		this.spr.scale(2);
		this.col=new CircleCollider(x+16, y+16, 20*2);
		spr.setPosition(x, y);
	}

	@Override
	public void update(float delta) {
		if(dying){
			dyingTicks+=delta*3;
			if(dyingTicks>.4){
				for (Arrow a:Arrow.arrows){
					a.unStick();
				}
			}
			if(dyingTicks>0){
				
				spr.setColor(Colours.withAlpha(Colours.green, 1-dyingTicks));
			}
		}
	}

	@Override
	public void collide(Arrow a) {
		if(dying||dead)return;
		if(col.collidePoint(a.getArrowPoint())){
			a.stickIn(this);
			SoundLibrary.enemyHit.play(.1f);
			dead=true;
			spr.setColor(Colours.green);
		}
	}

	@Override
	public boolean killPlayerOnContact() {
		return false;
	}

	@Override
	public boolean isDefeated() {
		return dead;
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
	}

}
