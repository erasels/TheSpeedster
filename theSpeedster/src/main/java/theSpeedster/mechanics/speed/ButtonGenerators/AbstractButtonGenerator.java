package theSpeedster.mechanics.speed.ButtonGenerators;

import theSpeedster.mechanics.speed.AbstractSpeedTime;
import theSpeedster.ui.buttons.TimedButton;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class AbstractButtonGenerator {
    protected float intensity, timeSince;
    protected ArrayList<TimedButton> buttons;
    protected Consumer<TimedButton> clickEffect;
    protected AbstractSpeedTime instance;

    private static final float MAX_Y = 250.0F;
    private static final float MIN_Y = 150.0F;
    private static final float MIN_X = -350.0F;
    private static final float MAX_X = 150.0F;

    public AbstractButtonGenerator(float intensity, Consumer<TimedButton> clickEffect) {
        this.intensity = intensity; //Somewhere between 0.5 and 1
        this.clickEffect = clickEffect;
        timeSince = 0f;
    }

    public void setButtons(ArrayList<TimedButton> buttons) {
        this.buttons = buttons;
    }

    public void setInstance(AbstractSpeedTime instance) {
        this.instance = instance;
    }

    public abstract void logic();
}
