package com.jy.prolife;

import com.badlogic.gdx.Game;

public class JyGame extends Game {

	
	@Override
	public void create () {
        Assets.load();
		setScreen(new WelcomeScreen(this));
	}

}
