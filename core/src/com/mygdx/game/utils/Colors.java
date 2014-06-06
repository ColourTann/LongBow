package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;

public class Colors {
	public static Color black=new Color(.07843137f,.04705882f,.1098039f,1);
	public static Color white=new Color(.8745098f,.9372549f,.843137f,1);
	public static Color red=new Color(.82745f,.270588f,.2862745f,1);
			
	public static Color shiftedTowards(Color source, Color target, float amount){
		if(amount>1) amount=1;
		if(amount<0) amount=0;
		System.out.println(amount);
		float r=source.r+((target.r-source.r)*amount);
		float g=source.g+(target.g-source.g)*amount;
		float b=source.b+(target.b-source.b)*amount;
		return new Color(r, g, b, 1);
	}
	public static Color withAlpha(Color source, float alphaMult){
		return new Color(source.r,source.g,source.b,source.a*alphaMult);
	}
}

