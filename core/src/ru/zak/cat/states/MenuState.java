package ru.zak.cat.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.zak.cat.MyCatJump;

public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Texture menuCat;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyCatJump.WIDTH / 2, MyCatJump.HEIGHT / 2);
        background = new Texture("fon.png");
        playBtn = new Texture("Playbtn.png");
        menuCat = new Texture("catMenu.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }



    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0, MyCatJump.WIDTH, MyCatJump.HEIGHT);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y - playBtn.getHeight() /2);
        sb.draw(menuCat, 25, camera.position.y - menuCat.getHeight() /6 );
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        menuCat.dispose();

    }
}
