package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Game;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.level.Platform;
import com.mygdx.game.utils.maths.BoxCollider;
import com.mygdx.game.utils.maths.Collider;

public class Player {
	public static Player p;

	float scale=3;
	float width=22*scale;
	float height=30*scale;
	TextureRegion bodyFrames[] = new TextureRegion[9];
	TextureRegion jumpFrames[] = new TextureRegion[3];
	float animSpd=.05f;
	float ticks=0;
	float timeSpentHolding;
	int apparentWidth=(int)(width/4);
	
	
	float dx=0;
	float dy=0;
	public float x=0;
	public float y=0;

	float spd=900;
	float jumpHeight=540;
	float gravity=1250;
	float drag =.05f;

	int facing=1;
	Arrow currentArrow;
	Arm arm;
	Bow bow;
	public Collider col;

	private boolean dying;
	public Player(){
		Texture tempPlayerTexture= new Texture("playersheet.png");
		for(int i=0;i<9;i++){
			bodyFrames[i]=new TextureRegion(tempPlayerTexture, i*22, 0, 22, 30);
		}
		tempPlayerTexture= new Texture("jump.png");
		for(int i=0;i<3;i++){
			jumpFrames[i]=new TextureRegion(tempPlayerTexture, i*22, 0, 22, 30);
		}
		bow=new Bow();
		arm=new Arm();
		col=new BoxCollider(x-apparentWidth/2, 0, apparentWidth, height);
	}

	public float getX(){
		return x;
	}
	public float getY(){
		return y+height/2;
	}
	
	public static void init(){
		p=new Player();
	}

	private void checkCollisions(float delta) {
		// check downwards //
		if(dy<0){
			float down =traceDown((int)x, (int)y, (int) Math.max(1, Math.abs(dy)*delta),Gdx.input.isKeyPressed(Input.Keys.S));

			if(down>=0){
				dy=0;
				y=down;
			}
		}
	}

	private boolean grounded() {
		return traceDown((int)x,(int)y,1,false)!=-1;
	}
	private int traceDown(int startX, int startY, int pixToCheck, boolean ignorePlatforms){
		int left = rayDown((int)(startX-width/6), startY, pixToCheck, ignorePlatforms);
		int right=rayDown((int)(startX+width/6), startY, pixToCheck, ignorePlatforms);
		return Math.max(left, right);
	}
	private int rayDown(int startX, int startY, int pixToCheck, boolean ignorePlatforms){
		for(int i=0;i<pixToCheck;i++){
			if(startY-i<=0){
				return startY+i;
			}
			for(Platform plat: Platform.platforms){
				
				if(plat.y+plat.height>startY) continue;
				
				if(ignorePlatforms&&!plat.edge) continue;
				
				if(plat.contains(startX,startY+i)) 	return startY+i;
				
			}
		}
		return -1;
	}

	public void update(float delta){
		
		
		
		if(dying) return;
		input(delta);

		int walkDir=dx>0?1:-1;

		//facing and crap //		
		int mult=1;
		if(facing!=walkDir) mult=-1;
		ticks+=Math.abs(dx)*delta*mult;
		if(ticks<0) ticks+=8/animSpd;

		// gravity //
		dy-=delta*gravity;

		// check collisions //
		checkCollisions(delta);

		// moving //
		x+=dx*delta;
		y+=dy*delta;
		x=Math.max(apparentWidth/2, Math.min(x, Game.width-apparentWidth/2));
		
		// drag //
		dx*=Math.pow(drag, delta);

		// animating different parts //
		int frame=(int)(timeSpentHolding*4)%4;
		if(timeSpentHolding*4>3){
			frame=3;
		}
		bow.update(delta, this, frame);
		arm.update(delta, this);
		col.x=x-apparentWidth/2;
		col.y=y;
		for (Enemy e: Enemy.enemies){
			if(e.killPlayerOnContact()&&col.collideWith(e.col)) die();
		}
	}

	private void input(float delta) {
		if(dying) return;

		// L/R MOVE //
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			dx-=spd*delta;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			dx+=spd*delta;	
		}

		// JUMPING //
	
			
		



		// LEFT CLICK //
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if(bow.currentFrame==1&&currentArrow==null){
				currentArrow=Arrow.create(this);
			}
			timeSpentHolding+=delta;
		}
		else{
			if(currentArrow!=null){
				shoot();
			}
			timeSpentHolding=0;
		}

		face(Gdx.input.getX()-x>0?1:-1);




	}

	private void shoot() {

		currentArrow.fire(bow.currentFrame+.5f);
		currentArrow=null;
	}
	
	private void die(){
		System.out.println("urk dead");
		dying=true;
	}

	private void face(int side){
		if(side!=facing)ticks+=4/animSpd;
		facing=side;
	}

	public void render(SpriteBatch sb){


		if(facing==-1){
			bow.render(sb);
		}
		else{
			arm.draw(sb);
		}
		if(dy!=0){
			int jumpFrameToRender=0;
			if(dy>0){
				jumpFrameToRender=2;
			}
			else if(dy<-300){
				jumpFrameToRender=0;
			}
			else{
				jumpFrameToRender=1;
			}
			sb.draw(jumpFrames[jumpFrameToRender], 
					x-(width-2)/2*facing, 
					y,
					22*scale*facing,
					30*scale);
		}
		else{
			int frameToRender=(int)(ticks*animSpd)%8;
			if(Math.abs(dx)<30f){
				frameToRender=8;
				ticks=0;
			}

			sb.draw(bodyFrames[frameToRender], 
					x-(width-2)/2*facing, 
					y,
					22*scale*facing,
					30*scale);
		}
		if(facing==1){
			bow.render(sb);

		}
		else{
			arm.draw(sb);
		}
	}

	public void jump() {
		if(grounded())dy=jumpHeight;
		
	}
}
