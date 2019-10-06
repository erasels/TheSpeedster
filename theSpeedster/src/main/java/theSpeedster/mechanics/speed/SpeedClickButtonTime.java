package theSpeedster.mechanics.speed;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import theSpeedster.mechanics.speed.ButtonGenerators.AbstractButtonGenerator;
import theSpeedster.ui.buttons.TimedButton;

import java.util.ArrayList;

public class SpeedClickButtonTime extends AbstractSpeedTime {
    protected ArrayList<TimedButton> buttons;
    protected Runnable effectAction;
    protected AbstractButtonGenerator buttonGenerator;

    public SpeedClickButtonTime(float duration, Runnable effect, AbstractButtonGenerator buttonGenerator) {
        super(Location.COMPLETE, duration);
        effectAction = effect;
        buttons = new ArrayList<>();
        this.buttonGenerator = buttonGenerator;
        buttonGenerator.setInstance(this);
        buttonGenerator.setButtons(this.buttons);
    }

    @Override
    public void effect() {
        effectAction.run();
    }

    @Override
    public void update() {
        buttonGenerator.logic();
        super.update();
        int c = 0;
        for(TimedButton b : buttons) {
            b.update(b.ordered?c++:0);
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        buttons.forEach(b -> b.render(sb));
    }

    @Override
    protected void renderEffects(SpriteBatch sb) {
        //Thinking about bottom fog effect
    }
}
