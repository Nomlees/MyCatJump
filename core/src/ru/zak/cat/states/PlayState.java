package ru.zak.cat.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import ru.zak.cat.MyCatJump;
import ru.zak.cat.sprites.Block;
import ru.zak.cat.sprites.Cat;
import ru.zak.cat.sprites.Award;

/**
 * Класс игрового экрана
 */

public class PlayState extends State {

    public static final int AWARD_SPACING = 300;
    public static final int BLOCK_SPACING = 300;
    public static final int BLOCK_COUNT = 200;
    public static final int AWARD_COUNT = 200;

    protected GameStateManager gsm;

    private Cat cat;

    private Texture bg;
    private BitmapFont font;
    private Array<Award> awards;

    private Array<Block> blocks;
    private int countCoins;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        cat = new Cat(0, 0);
        camera.setToOrtho(false, MyCatJump.WIDTH/2, MyCatJump.HEIGHT/2);
        bg = new Texture("Picture.png");
        font = new BitmapFont();
        blocks = new Array<Block>();
        awards = new Array<Award>();
        for (int i = 0; i < AWARD_COUNT; i++) {
           awards.add(new Award(i * (AWARD_SPACING + Award.AWARD_WIDTH)));
        }
        for (int i = 0; i < BLOCK_COUNT; i++) {
            blocks.add(new Block(i * (BLOCK_SPACING + Block.BLOCK_WIDTH)));
        }

    }


    protected void handleInput() {
        if (Gdx.input.justTouched())
            cat.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        cat.update(dt);
        camera.position.x = cat.getPosition().x + 205;
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);
        for (Block block : blocks) {
            sb.draw(block.getBlock(), block.getPosBlock().x, block.getPosBlock().y);
        }
        for (Award award : awards) {
            sb.draw(award.getAward(), award.getPosAward().x, award.getPosAward().y);
        }

        font.draw(sb, "Score: " + countCoins, camera.position.x, 240);
        sb.end();

        Iterator<Award> iterator = awards.iterator();
        while (iterator.hasNext()) {
            Award coin = iterator.next();
            if (cat.getBoundsCat().overlaps(coin.getRectangleCoinBot())) {
                countCoins++;
                iterator.remove();
            }

        }

    }

    @Override
    public void dispose() {
        bg.dispose();
    }

}
