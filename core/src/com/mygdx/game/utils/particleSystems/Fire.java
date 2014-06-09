package com.mygdx.game.utils.particleSystems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.player.Player;
import com.mygdx.game.utils.maths.CircleCollider;
import com.mygdx.game.utils.maths.Collider;
import com.mygdx.game.utils.particles.Flame;
import com.mygdx.game.utils.particles.Particle;

public class Fire extends ParticleSystem{
	float intensity;
	Color c;
	Collider col;
	boolean dead;
	public Fire(float x, float y, float dx, float dy, Color c){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.c=c;
		col=new CircleCollider(x, y, 10);
	}
	
	public boolean checkCollision(){
		if(dead) return false;
		
		if(col.collideWith(Player.p.col)){
		
			return true;
		}
		
		return false;
	}
	
	@Override
	public void update(float delta) {
		x+=dx*delta;
		y+=dy*delta;
		col.x=x;
		col.y=y;
		float freq=.001f;
		ticks+=delta;
		while(ticks>freq){
			ticks-=freq;
			particles.add(new Flame(x,y,0,0,c));
		}
		updateParticles(delta);
		if(x>Game.width+500||x<-500||y>Game.height+500||y<-500){
			dead=true;
		}
	}
	@Override
	public void render(SpriteBatch sb) {
		if(dead) return;
		for(Particle p:particles){
			if(p.dead)continue;
			p.render(sb);
		}
		sb.end();
		
		//col.debugDraw();
		sb.begin();
	}
}
