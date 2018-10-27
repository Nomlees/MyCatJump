package ru.zak.cat.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Award {

    public static final int AWARD_WIDTH = 100;
    private Texture botAward;
    private Vector2 posBotAward;
    private Rectangle botCoins;

    public Texture getBotAward() {
        return botAward;
    }


    public Vector2 getPosBotAward() {
        return posBotAward;
    }

    public Award(float x){
        botAward = new Texture("lac.png");
        posBotAward = new Vector2(x, 0);
        botCoins = new Rectangle(posBotAward.x , posBotAward.y , botAward.getWidth() , botAward.getHeight());
    }

    public Rectangle getRectangleCoinBot () {
        return botCoins;
    }

}
