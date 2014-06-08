package com.mygdx.game.utils.particleSystems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.utils.particles.Flame;
import com.mygdx.game.utils.particles.Particle;

public class Fire extends ParticleSystem{
	float intensity;
	Color c;
	public Fire(float x, float y, float dx, float dy, Color c){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.c=c;
	}
	@Override
	public void update(float delta) {
		x+=dx*delta;
		y+=dy*delta;
		float freq=.001f;
		ticks+=delta;
		if(ticks>freq){
			ticks-=freq;
			particles.add(new Flame(x,y,0,0,c));
		}
		updateParticles(delta);
	}
	@Override
	public void render(SpriteBatch sb) {
		for(Particle p:particles){
			if(p.dead)continue;
			p.render(sb);
		}
	}
}
