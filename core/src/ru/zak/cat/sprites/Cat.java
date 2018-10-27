package ru.zak.cat.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cat {
        private static final int MOVEMENT = 100;
        private static final int GRAVITY = -10;
        private Vector3 position;
        private Vector3 velosity;
        private Texture cat;
        private Rectangle boundsCat;

        public Cat(int x, int y) {
            position = new Vector3(x,y,0);
            velosity = new Vector3(0,0,0);
            cat = new Texture("2.png");
            boundsCat = new Rectangle(x,y, cat.getWidth(), cat.getHeight());
        }

        public Vector3 getPosition() {
            return position;
        }

        public Texture getCat() {
            return cat;
        }

        public void update(float dt) {
            if (position.y > 0 )
                velosity.add(0 , GRAVITY , 0);
            velosity.scl(dt);
            position.add(MOVEMENT * dt,velosity.y, 0);
            if (position.y < 0 )
                position.y = 0;
            velosity.scl(1 / dt);
            boundsCat.setPosition(position.x , position.y);
        }


        public void jump() {
            velosity.y = 100;
        }

        public Rectangle getBoundsCat() {
            return boundsCat;
        }
    }
