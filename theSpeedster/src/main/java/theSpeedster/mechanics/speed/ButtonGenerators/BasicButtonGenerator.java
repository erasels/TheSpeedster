package theSpeedster.mechanics.speed.ButtonGenerators;

import com.badlogic.gdx.math.MathUtils;
import theSpeedster.ui.buttons.TimedButton;
import theSpeedster.util.UC;

public class BasicButtonGenerator extends AbstractButtonGenerator{
    private static final float MAX_Y = 250.0F;
    private static final float MIN_Y = 150.0F;
    private static final float MIN_X = -350.0F;
    private static final float MAX_X = 150.0F;

    public BasicButtonGenerator(float intensity) {
        super(intensity, null);
        this.clickEffect = tb -> instance.effect();
    }

    public void logic() {
        timeSince -= UC.gt();
        if(timeSince < 0) {
            float x = MathUtils.random(MIN_X, MAX_X);
            float y = MathUtils.random(MIN_Y, MAX_Y);
            buttons.add(new TimedButton(x, y, intensity*2f, clickEffect, false));

            timeSince = intensity/2f;
        }
    }
}
