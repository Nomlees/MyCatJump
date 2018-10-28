package ru.zak.cat.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Cat {
        private static final int MOVEMENT = 100;
        private static final int GRAVITY = -10;
        private Vector3 position;
        private Vector3 velosity;
        private Texture cat;
        private Animation catAnimation;
        private Rectangle boundsCat;

        public Cat(int x, int y) {
            position = new Vector3(x,y,0);
            velosity = new Vector3(0,50,0);
//            cat = new Texture("2.png");
            cat = new Texture("catatlas.png");
            catAnimation = new Animation(new TextureRegion(cat), 5, 0.8f);

            boundsCat = new Rectangle(x,y, cat.getWidth()/5, cat.getHeight());
        }

    public TextureRegion getCat() {

        return catAnimation.getFrame();
    }

        public Vector3 getPosition() {
            return position;
        }

//        public Texture getCat() {
//            return cat;
//        }

        public void update(float dt) {
            catAnimation.update(dt);
            if (position.y > 50 )
                velosity.add(0 , GRAVITY , 0);
            velosity.scl(dt);
            position.add(MOVEMENT * dt,velosity.y, 0);
            if (position.y < 50 )
                position.y = 50;
            velosity.scl(1 / dt);
            boundsCat.setPosition(position.x , position.y);
        }


        public void jump() {
            velosity.y = 400;
        }

        public Rectangle getBoundsCat() {
            return boundsCat;
        }
    }
