package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.Game;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
        	GwtApplicationConfiguration cfg= new GwtApplicationConfiguration(800, 600);
        	cfg.fps=300;
        	
                return cfg;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Game();
        }
}