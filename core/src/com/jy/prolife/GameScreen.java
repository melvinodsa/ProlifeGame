package com.jy.prolife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by hacker on 27/3/15.
 */
public class GameScreen implements Screen {

    Game game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Animation temp;
    Float showTime;
    int gameState;
    int manY;
    Float rotation;
    int gravityEffect;
    int firstWallX, secondWallX;
    Random rand;
    int tempInt;
    Boolean firstOnStage, secondOneStage;
    int upperBound, lowerBound;
    int groundX, grassX, hillX, skyX;
    int score;

    public GameScreen(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 480, 854);
        batch = new SpriteBatch();
        showTime = 0F;
        gameState = 0;
        manY = 397;
        rotation = 0F;
        gravityEffect = 0;
        firstWallX = GameConstants.WALL_INITIAL_X;
        secondWallX = GameConstants.WALL_INITIAL_X;
        rand = new Random();
        tempInt = 0;
        firstOnStage = true;
        secondOneStage = false;
        upperBound = 0;
        lowerBound = 0;
        groundX = 0;
        grassX = 0;
        hillX = 0;
        skyX = 0;
        score = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    private void update(float delta) {
        switch (gameState){
            case 0:
                loadAnimation();
                break;
            case 1:
                updateAnimation();
                break;
        }

        generalUpdate();

        if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            manY -= 5 + (manY/100);
        }

        if(manY > 427){
            gravityEffect = 1;
        }

        if(manY < 427){
            gravityEffect = -1;
        }

        if((manY + 60) > 854){
            nextScreen();
        }
    }

    private void nextScreen() {
        Assets.hit_sound.play();
        Gdx.input.vibrate(100);
        if(score > Assets.settings.getInteger("highscore", 0)){
            Assets.settings.putInteger("highscore", score);
            Assets.settings.flush();
        }
        game.setScreen(new WelcomeScreen(game));
        game.getScreen().dispose();
    }

    private void generalUpdate() {
        manY += 5 + gravityEffect;
        rotation = (float)(manY - 427)/10;
        groundX -= 4;
        grassX -= 3;
        hillX -= 2;
        skyX -= 1;
        if(skyX < -479){
            skyX=0;
        }
        if(hillX < -478){
            hillX=0;
        }
        if(grassX < -477){
            grassX=0;
        }
        if(groundX < -476){
            groundX=0;
        }
        if((firstWallX < 200 && firstWallX > 196) || (secondWallX < 200 && secondWallX > 196)){
            Assets.tap_sound.play();
            score += 1;
        }
        if(firstOnStage || secondWallX < 150){
            firstWallX -= GameConstants.WALL_SPEED;
        }
        if(secondOneStage || firstWallX < 150){
            secondWallX -= GameConstants.WALL_SPEED;
        }
        if (firstWallX < GameConstants.WALL_END){
            firstOnStage = false;
            secondOneStage = true;
        }
        if (secondWallX < GameConstants.WALL_END){
            firstOnStage = true;
            secondOneStage = false;
        }
        if((firstWallX > 200 && firstWallX < 240) || (secondWallX > 200 && secondWallX < 240)){
            if((manY < upperBound ) || (manY + rotation < upperBound ) || ((manY + 40) > lowerBound) || ((manY + 40 + rotation ) > lowerBound) ){
               nextScreen();
            }
        }
    }


    private void loadAnimation() {
        temp = Assets.animation_man;
        showTime += Gdx.graphics.getDeltaTime();
        Assets.current_frame = temp.getKeyFrame(showTime, true);
        gameState = 1;
        tempInt = rand.nextInt(7);
        Assets.current_wall = Assets.frames_wall[0][tempInt];
        Assets.next_wall = Assets.frames_wall[0][tempInt];
        upperBound = 150 + (50*tempInt);
        lowerBound = 350 + (50*tempInt);
    }

    private void updateAnimation() {
        tempInt = rand.nextInt(7);
        showTime += Gdx.graphics.getDeltaTime();
        Assets.current_frame = temp.getKeyFrame(showTime, true);
        if(score > 5){
            temp = Assets.animation_girl;
            if (secondOneStage && firstWallX < GameConstants.WALL_END) {
                Gdx.app.log("second",Integer.toString(secondWallX));
                Assets.current_wall = Assets.frames_wall1[0][tempInt];
                firstWallX = GameConstants.WALL_INITIAL_X;
                upperBound = 150 + (50 * tempInt);
                lowerBound = 350 + (50 * tempInt);
            }
            else if (firstOnStage && secondWallX < GameConstants.WALL_END) {
                Gdx.app.log("first",Integer.toString(firstWallX));
                Assets.next_wall = Assets.frames_wall1[0][tempInt];
                secondWallX = GameConstants.WALL_INITIAL_X;
                upperBound = 150 + (50 * tempInt);
                lowerBound = 350 + (50 * tempInt);
            }
        }
        else if(score > 2){
            temp = Assets.animation_baby;
            if (secondOneStage && firstWallX < GameConstants.WALL_END) {
                Gdx.app.log("second",Integer.toString(secondWallX));
                Assets.current_wall = Assets.frames_wall1[0][tempInt];
                firstWallX = GameConstants.WALL_INITIAL_X;
                upperBound = 150 + (50 * tempInt);
                lowerBound = 350 + (50 * tempInt);
            }
            else if (firstOnStage && secondWallX < GameConstants.WALL_END) {
                Gdx.app.log("first",Integer.toString(firstWallX));
                Assets.next_wall = Assets.frames_wall1[0][tempInt];
                secondWallX = GameConstants.WALL_INITIAL_X;
                upperBound = 150 + (50 * tempInt);
                lowerBound = 350 + (50 * tempInt);
            }
        }
        else {
            if (secondOneStage && firstWallX < GameConstants.WALL_END) {
                Gdx.app.log("second",Integer.toString(secondWallX));
                Assets.current_wall = Assets.frames_wall1[0][tempInt];
                firstWallX = GameConstants.WALL_INITIAL_X;
                upperBound = 150 + (50 * tempInt);
                lowerBound = 350 + (50 * tempInt);
            }
            else if (firstOnStage && secondWallX < GameConstants.WALL_END) {
                Gdx.app.log("first",Integer.toString(firstWallX));
                Assets.next_wall = Assets.frames_wall1[0][tempInt];
                secondWallX = GameConstants.WALL_INITIAL_X;
                upperBound = 150 + (50 * tempInt);
                lowerBound = 350 + (50 * tempInt);
            }
        }
    }

    private void draw(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(Assets.sprite_sky, skyX, 0);
        batch.draw(Assets.sprite_sun, 0, 50);
        batch.draw(Assets.sprite_hill, hillX, 349);
        batch.draw(Assets.sprite_ground, groundX, 794);
        batch.draw(Assets.sprite_grass, grassX, 649);
        batch.draw(Assets.current_frame, 200, manY, 0, 0, 60, 60, 1, 1, rotation);
        batch.draw(Assets.current_wall, firstWallX, 0);
        batch.draw(Assets.next_wall, secondWallX, 0);
        Assets.font.draw(batch, ""+score, 225, 100);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
