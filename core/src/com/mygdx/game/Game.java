package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.level.Fight;
import com.mygdx.game.level.Platform;
import com.mygdx.game.level.Fight.FightType;
import com.mygdx.game.player.Arrow;
import com.mygdx.game.player.Player;
import com.mygdx.game.utils.BoxCollider;
import com.mygdx.game.utils.CircleCollider;
import com.mygdx.game.utils.Colors;

public class Game extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	ShapeRenderer sr;
	BitmapFont f;
	public static int width=800;
	public static int height=600;
	Fight arena;
	BoxCollider test= new BoxCollider(0, 0, 20,30);
	@Override
	public void create () {
		f= new BitmapFont();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		Player.init();
		Platform.init();
		arena=new Fight(FightType.TWINS);
		Gdx.input.setInputProcessor(this);
	}

	void init() {
		Enemy.enemies.clear();
		Platform.platforms.clear();
		Arrow.arrows.clear();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		Player.init();
		Platform.init();
		arena=new Fight(FightType.TWINS);
		Gdx.input.setInputProcessor(this);
		
	}
	
	@Override
	public void render () {
		float delta=Gdx.graphics.getDeltaTime();
		update(delta);

		
		Gdx.gl.glClearColor(.07843f, .047059f, .10980f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for(Platform p:Platform.platforms){
			p.render(sr);
		}

		batch.begin();
		f.draw(batch, "FPS: "+(int)(1/delta), 0, height);



		Player.p.render(batch);

		for(Enemy e:Enemy.enemies){
			e.render(batch);
		}


		for(Arrow a:Arrow.arrows )if(a!=null) a.draw(batch);





		batch.end();

		for(Enemy e:Enemy.enemies){
			//e.debugRender();
		}
		//Player.p.col.debugDraw();
		//test.debugDraw();


	}

	private void update(float delta) {
		Player.p.update(delta);
		for(Arrow a:Arrow.arrows){
			if(a!=null){
				a.update(delta);
			}
		}
		test.x=Gdx.input.getX();
		test.y=height-Gdx.input.getY();
		
		for(Enemy e:Enemy.enemies){
			e.update(delta);
		
		}
		if(arena.checkVictory()){
			//System.out.println("won");
		}
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Input.Keys.W==keycode)Player.p.jump(); 
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		switch(character){
		case 'r':init(); break;

		}
		
		
		return false;
	}

	

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
