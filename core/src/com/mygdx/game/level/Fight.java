package com.mygdx.game.level;

import java.util.ArrayList;

import com.mygdx.game.Game;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Follower;
import com.mygdx.game.enemies.Square;

public class Fight {
	public enum FightType{TWINS, SQUARE}
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
			
			Enemy.enemies.add(new Square(80, Game.height-80));
			
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
