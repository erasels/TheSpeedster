package theSpeedster.mechanics.speed.ButtonGenerators;

import com.badlogic.gdx.math.MathUtils;
import theSpeedster.ui.buttons.TimedButton;
import theSpeedster.util.UC;

public class BasicButtonGenerator extends AbstractButtonGenerator{

    public BasicButtonGenerator(float intensity) {
        super(intensity, null);
        this.clickEffect = tb -> instance.triggerEffect();
    }

    public void logic() {
        timeSince -= UC.gt();
        if(timeSince < 0) {
            while(true) {
                //Better left unseeded or it'd be easy to cheese
                float x = MathUtils.random(MIN_X, MAX_X);
                float y = MathUtils.random(MIN_Y, MAX_Y);
                TimedButton toBeAdded = new TimedButton(x, y, intensity * 2f, clickEffect, false);
                if(buttons.stream().noneMatch(tb -> toBeAdded.getHb().intersects(tb.getHb()))) {
                    buttons.add(new TimedButton(x, y, intensity * 2f, clickEffect, false));
                    break;
                }
            }

            timeSince = intensity/2f;
        }
    }
}
