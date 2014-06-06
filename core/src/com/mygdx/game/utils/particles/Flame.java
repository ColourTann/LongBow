package com.mygdx.game.utils.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.Colors;

public class Flame extends Particle{
	
	public Flame(float x, float y, float dx, float dy){
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		
	}
	@Override
	public void update(float delta) {
		x+=dx*delta;
		y+=dy*delta;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(Colors.red);
		//sb.draw(t, x, y);
		sb.draw(t, x, y, -t.getWidth()/2, -t.getHeight()/2, t.getWidth(), t.getHeight(), .5f, .5f, 0, 0, 0, t.getWidth(), t.getHeight(), false, false);
		
	}

	
}
