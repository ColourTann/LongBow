package com.mygdx.game.level;

import java.util.ArrayList;

import com.mygdx.game.Game;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Follower;
import com.mygdx.game.enemies.Square;
import com.mygdx.game.enemies.Target;

public class Fight {
	public enum FightType{TUTORIAL, TWINS, SQUARE}
	public FightType type;
	public ArrayList<Enemy> combatants = new ArrayList<Enemy>();
	public Fight (FightType type){
		this.type=type;
		setup();
	}

	private void setup() {	
		switch(type){
		case TWINS:
			Enemy e1=new Follower(Game.width/2, Game.height/2, true, false);
			Enemy e2=new Follower(Game.width/2, Game.height/2, false, true);
			Enemy.enemies.add(e1);
			Enemy.enemies.add(e2);
			combatants.add(e1);
			combatants.add(e2);
			break;
		case SQUARE:

			Enemy e=new Square(80, Game.height-80);
			Enemy.enemies.add(e);
			combatants.add(e);

			break;
		case TUTORIAL:
ArrayList<Target> targets=new ArrayList<Target>();
			targets.add(new Target(50, 50));
			targets.add(new Target(50, 250));
			targets.add(new Target(50, 450));
			targets.add(new Target(Game.width-50, 100));
			targets.add(new Target(Game.width-50, 200));
			targets.add(new Target(Game.width-50, 300));
			for(Enemy t:targets){
				Enemy.enemies.add(t);
				combatants.add(e);
			}
			
			

			break;
		default:
			break;

		}
	}
	public boolean checkVictory(){
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
}
