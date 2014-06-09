package com.mygdx.game.level;

import java.util.ArrayList;

import com.mygdx.game.Game;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Follower;
import com.mygdx.game.enemies.Square;
import com.mygdx.game.enemies.Target;

public class Fight {
	public enum FightType{TUTORIAL, TWINS, SQUARE}
	public static FightType type;
	static ArrayList<FightType> types= new ArrayList<Fight.FightType>();
	public static int fightNumber=0;
	public static ArrayList<Enemy> combatants = new ArrayList<Enemy>();
	
	public Fight (FightType type){
		this.type=type;
		setup();
	}

	public static void initFights(){
		types.add(FightType.TUTORIAL);
		types.add(FightType.SQUARE);
		types.add(FightType.TWINS);
		types.add(FightType.SQUARE);
		types.add(FightType.TWINS);
		types.add(FightType.SQUARE);
		types.add(FightType.TWINS);
		
	}
	
	private static void setup() {	
		switch(type){
		
		case TWINS:
			if(fightNumber==2){
			Enemy e1=new Follower(Game.width-80, Game.height-80, true, false,1);
			Enemy e2=new Follower(Game.width-80, Game.height-80, false, true,1);
			Enemy.enemies.add(e1);
			Enemy.enemies.add(e2);
			combatants.add(e1);
			combatants.add(e2);
			}
			if(fightNumber==4){
				Enemy e1=new Follower(Game.width-80, Game.height-80, true, false,1.3f);
				Enemy e2=new Follower(Game.width-80, Game.height-80, false, true,1.3f);
				Enemy.enemies.add(e1);
				Enemy.enemies.add(e2);
				combatants.add(e1);
				combatants.add(e2);
			}
			if(fightNumber==6){
				Enemy e1=new Follower(Game.width-80, Game.height-80, true, false,1);
				Enemy e2=new Follower(Game.width-80, Game.height-80, false, true,1);
				Enemy.enemies.add(e1);
				Enemy.enemies.add(e2);
				combatants.add(e1);
				combatants.add(e2);
				Enemy e3=new Follower(Game.width-80, Game.height-80, true, true,1);
				Enemy.enemies.add(e3);
				combatants.add(e3);
			}
			break;
		case SQUARE:
			
			Enemy e=new Square(80, Game.height-80,fightNumber);
			Enemy.enemies.add(e);
			combatants.add(e);

			break;
		case TUTORIAL:
			ArrayList<Target> targets=new ArrayList<Target>();
			targets.add(new Target(40, 50));
			targets.add(new Target(40, 250));
			targets.add(new Target(40, 450));
			targets.add(new Target(Game.width-70, 100));
			targets.add(new Target(Game.width-70, 300));
			targets.add(new Target(Game.width-70, 500));
			for(Enemy t:targets){
				Enemy.enemies.add(t);
				combatants.add(t);
			}
			
			

			break;
		default:
			break;

		}
	}
	public boolean checkVictory(){
		if(fightNumber>6)return false;
		for(Enemy e:combatants){
			if(!e.isDefeated()){
				return false;
			}
		}
		for(Enemy e:combatants){
			e.destroy();
		}
		
		return true;
	}
	public static void restart(){
		Game.newRoundAlpha=1;
		fightNumber--;
		setNextFight();
	}
	public static void setNextFight() {
		if(fightNumber>=6){
			fightNumber+=5;
			Game.won=true;
			return;
		}
		fightNumber++;
		Game.newRoundAlpha=1;
		combatants.clear();
		
		type=types.get(fightNumber);
		setup();
	}

	
}
