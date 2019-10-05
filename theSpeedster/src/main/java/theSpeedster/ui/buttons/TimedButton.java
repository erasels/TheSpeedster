package theSpeedster.ui.buttons;

import basemod.ClickableUIElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.apache.commons.lang3.math.NumberUtils;
import theSpeedster.util.UC;

import java.util.function.Consumer;

public class TimedButton extends ClickableUIElement {
    public boolean isDone;

    protected float startingDuration, duration;
    protected Consumer<TimedButton> clickEffect;

    //TODO: Make TimedButtonSpeedTime and add an arraylist fo these, iterate over them in the update and check for isDone before removing
    public TimedButton(Texture texture, float x, float y, float timer, Consumer<TimedButton> clickEffect) {
        super(texture, x, y, texture.getWidth(), texture.getHeight());
        startingDuration = duration = timer;
        this.clickEffect = clickEffect;
        this.tint = Color.GOLDENROD.cpy();
        isDone = false;
    }

    @Override
    protected void onHover() {
        //Could make button order a thing and make it tint red when this isn't the next in the order
        tint.a = 0.75f;
    }

    @Override
    protected void onUnhover() {
        tint.a = 0;
    }

    @Override
    protected void onClick() {
        clickEffect.accept(this);
    }

    public float getPercentageTimeLeft() {
        return NumberUtils.min(duration/startingDuration, 0f);
    }

    @Override
    public void update() {
        super.update();

        duration -= UC.gt();
        if(duration < 0) {
            isDone = true;
            setClickable(false);
        }
    }

    @Override
    public void render(SpriteBatch sb, Color col) {
        col.a = getPercentageTimeLeft();
        super.render(sb, col);
    }
}
