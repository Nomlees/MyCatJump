package ru.zak.cat.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import ru.zak.cat.MyCatJump;
import ru.zak.cat.sprites.Cat;
import ru.zak.cat.sprites.Award;

public class PlayState extends State {

    public static final int AWARD_SPACING = 40;
    public static final int AWARD_COUNT = 200;

    protected GameStateManager gsm;

    private Cat cat;
    private Texture bg;
    private BitmapFont font;
    private Array<Award> awards;
    private int countCoins;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        cat = new Cat(0, 0);
        camera.setToOrtho(false, MyCatJump.WIDTH/2, MyCatJump.HEIGHT/2);
        bg = new Texture("fon.png");
        font = new BitmapFont();
        awards = new Array<Award>();
        for (int i = 0; i < AWARD_COUNT; i++) {
           awards.add(new Award(i * (AWARD_SPACING + Award.AWARD_WIDTH)));
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
//        font.draw(sb, "Score: " + countCoins, camera.position.x, 90);

        for (Award award : awards) {
            sb.draw(award.getBotAward(), award.getPosBotAward().x, award.getPosBotAward().y);
        }
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
