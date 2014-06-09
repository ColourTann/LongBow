package com.mygdx.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;

public class SoundLibrary {
	public static Sound shoot;
	public static Sound enemyShoot;
	public static Sound playerDie;
	public static Sound victory;
	public static Sound miss;
	public static Sound jump;
	public static Sound enemyHit;
	public static void setup(){
		playerDie=Gdx.audio.newSound(Gdx.files.internal("playerdie.wav"));
		jump=Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		enemyShoot=Gdx.audio.newSound(Gdx.files.internal("enemyshoot.wav"));
		shoot=Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
		miss=Gdx.audio.newSound(Gdx.files.internal("miss.wav"));
		victory=Gdx.audio.newSound(Gdx.files.internal("victory.wav"));
		enemyHit=Gdx.audio.newSound(Gdx.files.internal("enemyhit.wav"));
	}
}
