package com.mygdx.game.enemies;

import com.mygdx.game.player.Arrow;
import com.mygdx.game.utils.maths.CircleCollider;

public class Target extends Enemy{

	public Target(float x, float y) {
		super(x, y, "target.png");
		this.spr.scale(3);
		this.col=new CircleCollider(x, y, 16*3);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void collide(Arrow a) {
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
		col.debugDraw();
	}

	@Override
	public void updateCollider(float x, float y) {
	}

}
