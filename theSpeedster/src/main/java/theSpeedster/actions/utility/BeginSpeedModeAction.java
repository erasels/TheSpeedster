package theSpeedster.actions.utility;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import theSpeedster.mechanics.speed.AbstractSpeedTime;
import theSpeedster.util.UC;
import theSpeedster.vfx.general.BeginSpeedModeEffect;

import static theSpeedster.TheSpeedster.makeID;

public class BeginSpeedModeAction extends AbstractGameAction {
    AbstractSpeedTime instance;
    private static final float START_DURATION = 1.5f;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SpeedModePrepText"));

    public BeginSpeedModeAction(AbstractSpeedTime instance) {
        this.instance = instance;
    }

    public void update() {
        UC.doVfx(new BorderLongFlashEffect(Color.WHITE.cpy()));
        UC.doVfx(new BeginSpeedModeEffect(Color.FIREBRICK, uiStrings.TEXT[0], START_DURATION, instance));
        isDone = true;
    }
}
