package com.mygdx.game.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Game;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.utils.maths.Sink;

public class Arrow extends Sprite{
	public static ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	float dx;
	float dy;
	float x;
	float y;
	float rotation;
	boolean fired=false;
	boolean dead=false;
	private boolean impacted;
	private boolean stuckIn;
	public Enemy stuckEnemy;
	Sink stuckOffset;
	public Arrow(){
		super(new Texture("arrow.png"));
		setScale(3);
	}
	public static Arrow create(Player p){
		Arrow a= new Arrow();
		a.x=p.x;
		a.y=p.y+20+p.height/2;
		a.setRotation(p.bow.angle);
		a.setOrigin(0, a.getHeight()/2);
		arrows.add(a);
		return a;
		/*for(int i=0;i<arrows.length;i++){
			if(arrows[i]==null||arrows[i]!=null&&arrows[i].dead){

				arrows[i]=new Arrow();
				arrows[i].x=p.x;
				arrows[i].y=p.y+20+p.height/2;

				arrows[i].setRotation(p.bow.angle);
				arrows[i].setOrigin(0, arrows[i].getHeight()/2);
				return arrows[i];
			}
		}*/
		
	}
	public void offset(int xAmount){
		x+=xAmount;
		setOrigin(-xAmount, getOriginY());
	}
	public void fire(float power){
		//ddx-=getOriginX()*300;
		
		x-=(getWidth()/2-getOriginX());
		setOrigin(getWidth()/2, getHeight()/2);
		float angle=Player.p.bow.angle;
		if(Player.p.bow.bowFacing==-1){
			angle+=180;
		}
		fired=true;
		dy=(float) Math.sin(angle*.01745f)*300*power;
		dx=(float) Math.cos(angle*.01745f)*300*power;
		
		
		
	}
	
	public void update(float delta){
		if(stuckIn){
			x=stuckEnemy.x+stuckOffset.x;
			y=stuckEnemy.y+stuckOffset.y;
			this.setX(x);
			this.setY(y);
			return;
		}
		if(impacted){
			return;
		}
		if(fired){
			dy-=delta*700;									
			x+=dx*delta;
			y+=dy*delta;
			rotation=(float) (Math.atan2(dy, dx)*57.296f);
			checkImpact();
		}
		
		else{
			x=Player.p.x;
			y=Player.p.y+20+Player.p.height/2;
			offset(6-Player.p.bow.currentFrame*2);
			rotation=Player.p.bow.angle;
			if(Player.p.bow.bowFacing==-1)rotation+=180;
		}
		setRotation(rotation);
		this.setX(x);
		this.setY(y);
		if(y<-50){
			dead=true;
		}
	}
	
	private void checkImpact() {
		
		Sink s=getArrowPoint();
		
		int endX=(int)s.x;
		int endY=(int)s.y;
		
		if(endY<=0||endY>Game.height||endX<0||endX>Game.width){
		impact();
			return;
		}
		for (Enemy e:Enemy.enemies){
			e.checkCollision(this);
		}
	}
	
	public void impact(){
		impacted=true;
	}
	
	public void stickIn(Enemy e){
		stuckIn=true;
		stuckEnemy=e;
		stuckOffset=new Sink(x-e.x,y-e.y);
	}
	
	public Sink getArrowPoint(){
		int ey=(int) (Math.sin(rotation*.01745f)*15);
		int ex=(int) (Math.cos(rotation*.01745f)*15);
		int endX=(int)(x+7+ex);
		int endY=(int)(y+2+ey);
		return new Sink(endX, endY);
	}
	
	
	
	

}
