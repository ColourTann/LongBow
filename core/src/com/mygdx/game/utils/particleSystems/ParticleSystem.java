package com.mygdx.game.utils.particleSystems;

import java.util.ArrayList;




import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.utils.particles.Particle;

public abstract class ParticleSystem {
	public static ArrayList<ParticleSystem> systems= new ArrayList<ParticleSystem>();
	
	protected float x,y,dx,dy,life,maxLife,ticks;
	protected ArrayList<Particle> particles = new ArrayList<Particle>();
	abstract void update(float delta);
	abstract void render(SpriteBatch sb);
	protected void updateParticles(float delta){
		for(Particle p:particles){
			p.update(delta);
		}
	}
	public static void updateAll(float delta){
		float threshold=800;
		for(int i=0;i<systems.size();i++){
			ParticleSystem ps = systems.get(i);
			if(ps.x>Game.width+threshold||ps.x<-threshold||ps.y>Game.height+threshold||ps.y<-threshold){
				systems.remove(i);
				i--;
			}
		}
		for(ParticleSystem ps:systems){
			ps.update(delta);
		}
	}
	
	public static void renderAll(SpriteBatch sb){
		for(ParticleSystem ps:systems){
			ps.render(sb);
		}
	}
	
	
}
