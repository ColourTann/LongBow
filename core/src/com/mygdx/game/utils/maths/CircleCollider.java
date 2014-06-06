package com.mygdx.game.utils.maths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;


public class CircleCollider extends Collider{
	float r;
	
	public CircleCollider(float x, float y, float r){
		this.x=x;
		this.y=y;
		this.r=r;
	}
	
	@Override
	public boolean collidePoint(Sink s) {
		float xDist=s.x-x;
		float yDist=s.y-y;
		float dist=xDist*xDist+yDist*yDist;
		return dist<=r*r;
	}

	@Override
	public void debugDraw() {
		ShapeRenderer sr = new ShapeRenderer();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sr.begin(ShapeType.Filled);
		
		
		sr.setColor(1, 1, 1, .3f);
		if(override!=null) sr.setColor(override);
		
		sr.circle(x, y, r);
		sr.end();
		sr.dispose();
	}

	@Override
	public boolean collideWith(Collider c) {
		if(c instanceof CircleCollider){
			CircleCollider circ = (CircleCollider) c;
			float maxDist=(r+circ.r)*(r+circ.r);
			float xDist=x-circ.x;
			float yDist=y-circ.y;
			float actualDist=xDist*xDist+yDist*yDist;
			
			if(actualDist<=maxDist){
				return true;
			}
			return false;
		}
		if(c instanceof BoxCollider){
			BoxCollider bc = (BoxCollider) c;
			return circleBoxCollide(this, bc);
		}
		return false;
	}
	

}
