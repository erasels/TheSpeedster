package theSpeedster.vfx.general;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSpeedster.actions.utility.SetSpeedModeAction;
import theSpeedster.mechanics.speed.AbstractSpeedTime;
import theSpeedster.util.UC;

public class BeginSpeedModeEffect extends TextEffect {
    private AbstractSpeedTime instance;

    public BeginSpeedModeEffect(Color col, String msg, float duration, AbstractSpeedTime instance) {
        super(col, msg, duration);
        this.instance = instance;
    }

    @Override
    public void update() {
        super.update();
        AbstractDungeon.player.hand.group.forEach(c -> c.target_y = -AbstractCard.IMG_HEIGHT);
        if(isDone) {
            UC.atb(new SetSpeedModeAction(instance));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            UC.displayTimer(sb, msg + UC.get2DecString(duration), Settings.HEIGHT - (180.0f * Settings.scale), color);
        }
    }
}
