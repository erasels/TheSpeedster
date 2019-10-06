package theSpeedster.mechanics.speed;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WindyParticleEffect;
import theSpeedster.util.UC;

import java.util.function.Consumer;

public class SpeedClickEnemyTime extends AbstractSpeedTime {
    public SpeedClickEnemyTime(float duration, Consumer<AbstractMonster> effect) {
        super(Location.BEHIND_MONSTER, duration);
        effectAction = effect;
    }

    @Override
    public void effect() {
        if(InputHelper.justClickedLeft) {
            AbstractMonster m = UC.getAliveMonsters().stream().filter(mon -> mon.hb.hovered).findAny().orElse(null);//AlchHelper.getClosestMonster(InputHelper.mX, InputHelper.mY, 300f);
            if(m != null)
                effectAction.accept(m);
        }
    }

    @Override
    protected void renderEffects(SpriteBatch sb) {
        AbstractDungeon.effectsQueue.add(new WindyParticleEffect());
    }
}
