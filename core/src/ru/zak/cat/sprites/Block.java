package ru.zak.cat.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
    public static final int BLOCK_HEIGHT = 100;
    private Texture block;
    private Vector2 posBlock;
    private Rectangle botCoins;

    public Texture getBlock() {
        return block;
    }


    public Vector2 getPosBlock() {
        return posBlock;
    }

    public Block (float x){
        block = new Texture("block.png");
        posBlock = new Vector2(x, 0);
        botCoins = new Rectangle(posBlock.x , posBlock.y , block.getWidth() , block.getHeight());
    }

    public Rectangle getRectangleCoinBot () {
        return botCoins;
    }
}
