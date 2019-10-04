package theSpeedster.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theSpeedster.cards.abstracts.SpeedsterCard;

public class MagicNumber2 extends DynamicVariable {
    @Override
    public String key() {
        return "theSpeedster:M2";
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof SpeedsterCard) {
            return ((SpeedsterCard) card).baseMagicNumber2;
        }
        return -1;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof SpeedsterCard) {
            return ((SpeedsterCard) card).magicNumber2;
        }
        return -1;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof SpeedsterCard) {
            return ((SpeedsterCard) card).isMagicNumber2Modified;
        }
        return false;
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof SpeedsterCard) {
            ((SpeedsterCard) card).isMagicNumber2Modified = true;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}
