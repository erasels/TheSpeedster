package theSpeedster.ui.buttons;

import basemod.ClickableUIElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.RarePotionParticleEffect;
import org.apache.commons.lang3.math.NumberUtils;
import theSpeedster.TheSpeedster;
import theSpeedster.util.TextureLoader;
import theSpeedster.util.UC;
import theSpeedster.vfx.general.ButtonConfirmedEffect;

import java.util.function.Consumer;

public class TimedButton extends ClickableUIElement {
    public boolean isDone, ordered, lockDuration;
    public int order;

    protected float startingDuration, duration, sparkleTimer;
    protected Consumer<TimedButton> clickEffect;

    //TODO: Make TimedButtonSpeedTime and add an arraylist fo these, iterate over them in the update and check for isDone before removing
    public TimedButton(Texture texture, float x, float y, float timer, Consumer<TimedButton> clickEffect, boolean ordered) {
        super(texture, x, y, texture.getWidth(), texture.getHeight());
        startingDuration = duration = timer;
        this.ordered = ordered;
        this.clickEffect = clickEffect;
        if(ordered) {
            this.tint = Color.FIREBRICK.cpy();
        } else {
            this.tint = Color.FOREST.cpy();
        }
        isDone = false;
    }

    public TimedButton(float x, float y, float timer, Consumer<TimedButton> clickEffect, boolean ordered) {
        this(TextureLoader.getTexture(TheSpeedster.makeUIPath("NormalButton.png")), x, y, timer, clickEffect, ordered);
    }

    @Override
    protected void onHover() {
        if(!ordered || order != 0) {
            tint.a = 0.75f;
        }
    }

    @Override
    protected void onUnhover() {
        tint.a = 0;
    }

    @Override
    protected void onClick() {
        UC.doVfx(new ButtonConfirmedEffect(x, y));
        clickEffect.accept(this);
        isDone = true;
        setClickable(false);
        lockDuration = true;
    }

    public float getPercentageTimeLeft() {
        return NumberUtils.min(duration/startingDuration, 0f);
    }

    public void update(int order) {
        this.order = order;
        super.update();

        if(!lockDuration) {
            duration -= UC.gt();
        }
        if(duration < 0 && !lockDuration) {
            isDone = true;
            setClickable(false);
            lockDuration = true;
        }
    }

    @Override
    public void render(SpriteBatch sb, Color col) {
        col.a = getPercentageTimeLeft();
        super.render(sb, col);

        if(ordered) {
            //TODO: Make number render on button
        }

        if(!ordered || order != 0) {
            renderParticles(sb);
        }
    }

    public void renderParticles(SpriteBatch sb) {
        this.sparkleTimer -= Gdx.graphics.getDeltaTime();
        if (this.sparkleTimer < 0.0F) {
            float xOffset = MathUtils.random(0, hb_w/2f);
            float yOffset = MathUtils.random(0, hb_h/2f);
            if(MathUtils.randomBoolean()) {
                xOffset = -xOffset;
            }
            if(MathUtils.randomBoolean()) {
                yOffset = -yOffset;
            }
            AbstractDungeon.topLevelEffects.add(new RarePotionParticleEffect(x+xOffset, y+yOffset));
            this.sparkleTimer = MathUtils.random(0.2F, 0.4F);
        }
    }
}
