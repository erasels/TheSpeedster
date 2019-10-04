package theSpeedster.relics.abstracts;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theSpeedster.TheSpeedster;
import theSpeedster.util.TextureLoader;

public abstract class ImmortalRelic extends AbstractRelic {
    public ImmortalRelic(String setId, String imgName, RelicTier tier, LandingSound sfx) {
        super(setId, "", tier, sfx);

        imgUrl = imgName;

        if (img == null || outlineImg == null) {
            img = TextureLoader.getTexture(TheSpeedster.makeRelicPath(imgName));
            largeImg = TextureLoader.getTexture(TheSpeedster.makeRelicPath(imgName));
            outlineImg = TextureLoader.getTexture(TheSpeedster.makeRelicOutlinePath(imgName));
        }
    }
}
