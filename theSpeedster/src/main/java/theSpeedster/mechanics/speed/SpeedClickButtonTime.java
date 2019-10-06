package theSpeedster.mechanics.speed;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import theSpeedster.mechanics.speed.ButtonGenerators.AbstractButtonGenerator;
import theSpeedster.ui.buttons.TimedButton;

import java.util.ArrayList;

public class SpeedClickButtonTime extends AbstractSpeedTime {
    private static Color col = Color.WHITE.cpy();
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

    public void triggerEffect() {
        effectAction.run();
    }

    @Override
    public void update() {
        super.update();
        buttonGenerator.logic();
        int c = 0;
        for (TimedButton b : buttons) {
            b.update(b.ordered ? c++ : 0);
        }
        buttons.removeIf(b -> b.isDone);
    }

    @Override
    public void render(SpriteBatch sb) {
        buttons.forEach(b -> {
            b.render(sb, col);
            col.a = 1.0f;
        });
    }

    @Override
    protected void renderEffects(SpriteBatch sb) {
        //Thinking about bottom fog effect
    }
}
