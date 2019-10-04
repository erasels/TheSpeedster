package theSpeedster.actions.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theSpeedster.patches.cards.DrawnCardHook;
import theSpeedster.util.UC;

import java.util.function.Consumer;

public class CallbackDrawAction extends DrawCardAction {
    private Consumer<AbstractCard> callback;

    public CallbackDrawAction(int numCards, Consumer<AbstractCard> callback) {
        super(UC.p(), numCards, false);
        this.callback = callback;
    }

    @Override
    public void update() {
        DrawnCardHook.callback = callback;
        super.update();
        DrawnCardHook.callback = null;
    }
}