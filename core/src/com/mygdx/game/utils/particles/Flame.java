package com.mygdx.game.utils.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.Colors;

public class Flame extends Particle{
	Color c;
	public Flame(float x, float y, float dx, float dy, Color c){
		this.c=c;
		System.out.println(dx);
		this.x=x;
		this.y=y;
		this.dx=dx+random(50);
		this.dy=dy+random(50);
		life=.7f;
		maxLife=life;
	//	System.out.println(random(100));
	}
	@Override
	public void update(float delta) {
		life-=delta;
		ratio=life/maxLife;
		if(life<=0){
			dead=true;
		}
		x+=dx*delta;
		y+=dy*delta;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(Colors.withAlpha(c, ratio/10));
		float size=.4f;
		//sb.draw(t, x, y);
		sb.draw(t, x-t.getWidth()/2, y-t.getHeight()/2, t.getWidth()/2, t.getHeight()/2, t.getWidth(), t.getHeight(), size*ratio, size*ratio, 0, 0, 0, t.getWidth(), t.getHeight(), false, false);
		
	}

	
}
