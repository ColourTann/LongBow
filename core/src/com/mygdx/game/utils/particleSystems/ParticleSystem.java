package com.mygdx.game.utils.particleSystems;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.particles.Particle;

public abstract class ParticleSystem {
	float x,y,dx,dy,life,maxLife,ticks;
	ArrayList<Particle> particles = new ArrayList<Particle>();
	public abstract void update(float delta);
	public abstract void render(SpriteBatch sb);
	public void updateParticles(float delta){
		for(Particle p:particles){
			p.update(delta);
		}
	}
}
