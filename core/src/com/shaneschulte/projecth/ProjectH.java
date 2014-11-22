package com.shaneschulte.projecth;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shaneschulte.projecth.com.shaneschulte.projecth.screens.GameScreen;

public class ProjectH extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        Gdx.app.log("ProjectH", "created");

        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont(Gdx.files.internal("monkey.fnt"),Gdx.files.internal("monkey.png"),false);
        setScreen(new GameScreen(this));
    }
}