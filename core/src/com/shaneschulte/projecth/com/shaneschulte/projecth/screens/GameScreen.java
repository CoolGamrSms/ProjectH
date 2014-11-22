package com.shaneschulte.projecth.com.shaneschulte.projecth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shaneschulte.projecth.ProjectH;

/**
 * Created by shane on 11/21/14.
 */
public class GameScreen implements Screen {

    final ProjectH game;
    Texture bg;
    OrthographicCamera camera;
    float screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;

    public GameScreen(final ProjectH game) {
        this.game = game;
        Gdx.app.log("GameScreen", "Attached");

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        bg = new Texture(Gdx.files.internal("background.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.disableBlending();
        game.batch.draw(bg, 0, 0, screenWidth, screenHeight);
        game.batch.enableBlending();
        game.font.setScale(0.8f);
        game.batch.end();

        //game.font.draw(game.batch, "FUCK THIS SHIT", 0, screenHeight/2);



        //game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(0, screenHeight*0.96f, screenWidth, screenHeight*0.04f);
        for(int i = 1; i < 5; ++i) {
            shapeRenderer.setColor(0, 0, 0, (i)/8f);
            shapeRenderer.rect(0, screenHeight*(0.81f-0.15f*i),
                                  screenWidth, screenHeight*0.15f);
        }
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        game.batch.begin();
        game.font.setColor(0.2f, 0.7f, 0.2f, 1);

        String names[] = {"Scan", "Browser", "Messages", "Store", "Settings"};
        for(int i = 0; i < 5; ++i) {
            BitmapFont.TextBounds bounds = game.font.getBounds(names[i]);
            game.font.drawWrapped(game.batch, names[i], 0,
                    screenHeight * (0.81f - 0.15f * (i-1) - 0.075f)+bounds.height/2,
                    screenWidth, BitmapFont.HAlignment.CENTER);
        }
        game.batch.end();


        if (Gdx.input.isTouched()) {
            //game.setScreen(new GameScreen(game));
            //dispose();
            Gdx.app.log("GameScreen", "SOMETHING JUST HAPPENED");
        }
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
