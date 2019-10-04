package theSpeedster.cards.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSpeedster.TheSpeedster;
import theSpeedster.cards.abstracts.SpeedsterCard;
import theSpeedster.util.CardInfo;

import static theSpeedster.TheSpeedster.makeID;
import static theSpeedster.util.UC.*;

public class TestCard extends SpeedsterCard {
    private final static CardInfo cardInfo = new CardInfo(
            "TestCard",
            0,
            CardType.SKILL,
            CardTarget.SELF);

    public final static String ID = makeID(cardInfo.cardName);


    public TestCard() {
        super(cardInfo, false);
        p(); //Stupid intellij stuff s, s
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.overlayMenu.showBlackScreen(0.5f);
        TheSpeedster.pogModo = true;
    }
}