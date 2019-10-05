package theSpeedster.actions.utility;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theSpeedster.TheSpeedster;
import theSpeedster.mechanics.speed.AbstractSpeedTime;

public class SetSpeedModeAction extends AbstractGameAction {
    AbstractSpeedTime instance;

    public SetSpeedModeAction(AbstractSpeedTime instance) {
        this.instance = instance;
    }

    public void update() {
        TheSpeedster.speedScreen = instance;
        isDone = true;
    }
}
