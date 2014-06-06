package com.mygdx.game.utils.particleSystems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.particles.Flame;
import com.mygdx.game.utils.particles.Particle;

public class Fire extends ParticleSystem{
	float intensity;
	public Fire(float x, float y, float dx, float dy){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
	}
	@Override
	public void update(float delta) {
		float freq=.1f;
		ticks+=delta;
		if(ticks>freq){
			ticks-=freq;
			particles.add(new Flame(x,y,dy,dy));
		}
		updateParticles(delta);
	}
	@Override
	public void render(SpriteBatch sb) {
		for(Particle p:particles){
			p.render(sb);
		}
	}
}
