package com.mygdx.game.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Game;

public class Platform {
	public static ArrayList<Platform> platforms = new ArrayList<Platform>();
	int x;
	public int y;
	int width;
	public int height;
	public boolean edge;
	Color c= new Color(.188f,.204f,.427f,1);
	public Platform(int x, int y, int width, int height, boolean edge){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.edge=edge;
	}
	public void render(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		sr.setColor(c);
		sr.rect(x, y, width, height);
		sr.end();
	}
	public boolean contains(int x2, float y2) {
		
		if(x2<x)return false;
		
		if(x2>x+width) return false;
		
		if(y2<y) return false;
		
		if(y2>y+height) return false;
		
		return true;
	}
	public static void init() {
		
		// bottom edge //
		platforms.add(new Platform(0, 0, Game.width, 0, true));
		
		int platWidth= Game.width/5;
		int platHeight= 5;
		int x0=Game.width/8;
		int x1=Game.width/2-platWidth/2;
		int x2=Game.width/8*7-platWidth;
		int yGap=100;
		
		for(int i=1;i<5;i++){
			if(i%2==0){
				platforms.add(new Platform(x0, yGap*i, platWidth, platHeight, false));
				platforms.add(new Platform(x2, yGap*i, platWidth, platHeight, false));
			}
			else{
				platforms.add(new Platform(x1, yGap*i, platWidth, platHeight, false));
			}
		}
		
		
		
		
	}
}
