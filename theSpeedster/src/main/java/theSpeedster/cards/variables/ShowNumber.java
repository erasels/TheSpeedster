package theSpeedster.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theSpeedster.cards.abstracts.SpeedsterCard;

public class ShowNumber extends DynamicVariable {
    @Override
    public String key() {
        return "theSpeedster:SN";
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof SpeedsterCard) {
            return ((SpeedsterCard) card).baseShowNumber;
        }
        return -1;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof SpeedsterCard) {
            return ((SpeedsterCard) card).showNumber;
        }
        return -1;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof SpeedsterCard) {
            return ((SpeedsterCard) card).isShowNumberModified;
        }
        return false;
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof SpeedsterCard) {
            ((SpeedsterCard) card).isShowNumberModified = true;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}
