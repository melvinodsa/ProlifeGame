package com.jy.prolife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


/**
 * Created by hacker on 27/3/15.
 */
public class Assets {
    public static Texture texture_play;
    public static Sprite sprite_play;

    public static Sound btn_sound;
    public static Sound tap_sound;
    public static Sound hit_sound;

    public static Texture texture_man;
    public static TextureRegion[][] frames_man;
    public static Texture texture_baby;
    public static TextureRegion[][] frames_baby;
    public static Animation animation_baby;
    public static Texture texture_girl;
    public static TextureRegion[][] frames_girl;
    public static Animation animation_girl;
    public static Animation animation_man;
    public static TextureRegion current_frame;

    public static Texture texture_wall;
    public static TextureRegion[][] frames_wall;
    public static Texture texture_wall1;
    public static TextureRegion[][] frames_wall1;
    public static Texture texture_wall2;
    public static TextureRegion[][] frames_wall2;
    public static TextureRegion current_wall;
    public static TextureRegion next_wall;

    public static Texture texture_ground;
    public static Sprite sprite_ground;
    public static Texture texture_grass;
    public static Sprite sprite_grass;
    public static Texture texture_hill;
    public static Sprite sprite_hill;
    public static Texture texture_sky;
    public static Sprite sprite_sky;
    public static Texture texture_sun;
    public static Sprite sprite_sun;
    public static Texture back_texture;
    public static Sprite back_sprite;


    public static BitmapFont font;
    public static Preferences settings;


    public static  void load(){

        back_texture = new Texture(Gdx.files.internal("WelcomeScreen/back.png"));
        back_sprite = new Sprite(back_texture);
        back_sprite.flip(false, true);

        texture_ground = new Texture(Gdx.files.internal("GameScreen/ground.png"));
        sprite_ground = new Sprite(texture_ground);
        sprite_ground.flip(false, true);
        texture_grass = new Texture(Gdx.files.internal("GameScreen/back1.png"));
        sprite_grass = new Sprite(texture_grass);
        sprite_grass.flip(false, true);
        texture_hill = new Texture(Gdx.files.internal("GameScreen/hill.png"));
        sprite_hill = new Sprite(texture_hill);
        sprite_hill.flip(false, true);
        texture_sky = new Texture(Gdx.files.internal("GameScreen/sky.png"));
        sprite_sky = new Sprite(texture_sky);
        sprite_sky.flip(false, true);
        texture_sun = new Texture(Gdx.files.internal("GameScreen/sun.png"));
        sprite_sun = new Sprite(texture_sun);
        sprite_sun.flip(false, true);

        texture_wall = new Texture(Gdx.files.internal("GameScreen/wall_sprite.png"));
        frames_wall = TextureRegion.split(texture_wall, 40, 794);
        for (int i = 0; i < 7; i++){
            frames_wall[0][i].flip(false, true);
        }
        texture_wall1 = new Texture(Gdx.files.internal("GameScreen/wall_sprite_1.png"));
        frames_wall1 = TextureRegion.split(texture_wall1, 40, 794);
        for (int i = 0; i < 7; i++){
            frames_wall1[0][i].flip(false, true);
        }
        texture_wall2 = new Texture(Gdx.files.internal("GameScreen/wall_sprite_2.png"));
        frames_wall2 = TextureRegion.split(texture_wall2, 40, 794);
        for (int i = 0; i < 7; i++){
            frames_wall2[0][i].flip(false, true);
        }

        texture_play = new Texture(Gdx.files.internal("WelcomeScreen/btn_ply.png"));
        texture_play.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite_play = new Sprite(texture_play);
        sprite_play.flip(false, true);

        btn_sound = Gdx.audio.newSound(Gdx.files.internal("WelcomeScreen/btn_play.mp3"));
        tap_sound = Gdx.audio.newSound(Gdx.files.internal("GameScreen/fly.mp3"));
        hit_sound = Gdx.audio.newSound(Gdx.files.internal("GameScreen/hit.mp3"));

        texture_man = new Texture(Gdx.files.internal("GameScreen/flappy_man_animation.png"));
        frames_man = TextureRegion.split(texture_man, 150, 60);
        frames_man[0][0].flip(false, true);
        frames_man[0][1].flip(false, true);
        animation_man = new Animation(0.3F, frames_man[0]);
        texture_baby = new Texture(Gdx.files.internal("GameScreen/baby_man_animation.png"));
        frames_baby = TextureRegion.split(texture_baby, 150, 60);
        frames_baby[0][0].flip(false, true);
        frames_baby[0][1].flip(false, true);
        animation_baby = new Animation(0.3F, frames_baby[0]);
        texture_girl = new Texture(Gdx.files.internal("GameScreen/baby_girl_animation.png"));
        frames_girl = TextureRegion.split(texture_girl, 150, 60);
        frames_girl[0][0].flip(false, true);
        frames_girl[0][1].flip(false, true);
        animation_girl = new Animation(0.3F, frames_girl[0]);

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("GameScreen/my_font.ttf"));
        font = gen.generateFont(50);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.valueOf("ff060a"));
        font.setScale(1, -1);

        settings = Gdx.app.getPreferences("highscore");
    }
}
