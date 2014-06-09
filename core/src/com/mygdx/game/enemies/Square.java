package com.mygdx.game.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Game;
import com.mygdx.game.player.Arrow;
import com.mygdx.game.player.Player;
import com.mygdx.game.sound.SoundLibrary;
import com.mygdx.game.utils.Colours;
import com.mygdx.game.utils.maths.BoxCollider;
import com.mygdx.game.utils.maths.Sink;
import com.mygdx.game.utils.particleSystems.Fire;
import com.mygdx.game.utils.particleSystems.ParticleSystem;
import com.mygdx.game.utils.particles.Flame;
import com.mygdx.game.utils.particles.Particle;


public class Square extends Enemy {
	float spd=80;
	Sink direction;
	float timeSinceLastHit=500;
	int health=5;
	float dyingTime=0;
	float attackTime=1.4f;
	ArrayList<Fire> fires = new ArrayList<Fire>();
	public Square(float x, float y, float spdMult) {
		super(x, y, "square.png");
		//spr.setOriginCenter();
		spr.setScale(3);
		col=new BoxCollider(x-spr.getWidth()/2*3, y-spr.getHeight()/2*3, 32*3, 32*3);
		direction=new Sink(1,0);
		spd+=spdMult*20;
		health+=spdMult*1.5;
		attackTime-=spdMult*.13f;
	}

	@Override
	public void update(float delta) {
		checkShots();
		if(dead) return;
		if(dying){
			dyingTime+=delta;
			
				for(Arrow a:Arrow.arrows){
					if(a.stuckEnemy==this){
						if(Math.random()<=delta*50){
						a.unStick();
						break;
						}
					}
				}
			
			if(dyingTime>1){
				dead=true;
			}
			spr.setColor(Colours.withAlpha(Colours.red, 1-dyingTime));
			return;
			
		}
		if(timeSinceLastHit<1){
		spr.setColor(new Color(1,timeSinceLastHit,timeSinceLastHit,timeSinceLastHit));
			
		}
		else{
			spr.setColor(Color.WHITE);
		}
		timeSinceLastHit+=delta*3;
		float newX=x+direction.x*delta*spd;
		float newY=y+direction.y*delta*spd;
		if(newX>Game.width-80||newX<80||newY<80||newY>Game.height-80){
			direction.rotate();
		}
		
		x+=direction.x*delta*spd;
		y+=direction.y*delta*spd;
		
		col.x=x-spr.getWidth()/2*3;
		col.y=y-spr.getWidth()/2*3;
		
		spr.setX(x-spr.getWidth()/2);
		spr.setY(y-spr.getHeight()/2);
		
		ticks+=delta;
		
		if(ticks>attackTime){
			ticks-=attackTime;
			attack();
		}
		
	}

	private void attack() {
		SoundLibrary.enemyShoot.play();
		Sink from=new Sink(x,y);
		Sink to=new Sink(Player.p.x,Player.p.y);
		Sink vector = Sink.getVector(from, to);
		vector.normalise();
		float atkSpd=500;
		Fire f=new Fire(this.x,this.y,vector.x*atkSpd,vector.y*atkSpd,new Color(1,.3f,.1f,1));
		fires.add(f);
		ParticleSystem.systems.add(f);
	}

	@Override
	public void collide(Arrow a) {
		if(dying||dead)return;
		SoundLibrary.enemyHit.play(.1f);
		a.stickIn(this);
		health--;
		timeSinceLastHit=0;
	}

	@Override
	public boolean killPlayerOnContact() {
		return !isDefeated();
	}
	
	public void checkShots(){
		for(Fire f:fires){
			if(f.checkCollision()){
				Player.p.die();
			}
		}
	}

	@Override
	public boolean isDefeated() {
		return health<=0;
	}

	@Override
	public void destroy() {
		dying=true;
		
		
	}

	@Override
	public void debugRender() {
	}

	@Override
	public void updateCollider(float x, float y) {
	}

}
