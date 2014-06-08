package com.mygdx.game.utils.maths;

public class Sink {
	public float x,y;
	public Sink(float x, float y){
		this.x=x;
		this.y=y;
	}
	public static Sink getVector(Sink from, Sink to){
		return new Sink(to.x-from.x,to.y-from.y);
	}
	public void normalise(){
		float dist = Math.abs(x)+Math.abs(y);
		x=x/dist;
		y=y/dist;
	}
	public void rotate(){
		float tempx=x;
		float tempy=y;
		y=-x;
		x=tempy;
	}
	public String toString(){
		return x+":"+y;
	}
}
