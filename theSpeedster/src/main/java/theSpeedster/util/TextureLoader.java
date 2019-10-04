package theSpeedster.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

import static basemod.abstracts.CustomCard.imgMap;
import static theSpeedster.TheSpeedster.makeCardPath;
import static theSpeedster.TheSpeedster.makeUIPath;

// Thank you Blank The Evil!

// Welcome to the utilities package. This package is for small utilities that make our life easier.
// You honestly don't need to bother with this unless you want to know how we're loading the textures.


public class TextureLoader {
    private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
    public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());

    /**
     * @param textureString - String path to the texture you want to load relative to resources,
     *                      Example: "theDefaultResources/images/ui/missing_texture.png"
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided
     */
    public static Texture getTexture(final String textureString) {
        if (textures.get(textureString) == null) {
            try {
                loadTexture(textureString);
            } catch (GdxRuntimeException e) {
                logger.error("Could not find texture: " + textureString);
                return getTexture("theSpeedsterResources/images/ui/missing_texture.png");
            }
        }
        return textures.get(textureString);
    }

    /**
     * @param - String path to the texture you want to load relative to resources,
     *          * Example: "img/ui/missingtexture.png"
     * @return <b>com.badlogic.gdx.graphics.TextureAtlas.AtlasRegion</b> - The texture is returned as an AtlasRegion
     */
    public static TextureAtlas.AtlasRegion getTextureAsAtlasRegion(String textureString) {
        Texture texture = getTexture(textureString);
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    /**
     * Creates an instance of the texture, applies a linear filter to it, and places it in the HashMap
     *
     * @param textureString - String path to the texture you want to load relative to resources,
     *                      Example: "img/ui/missingtexture.png"
     * @throws GdxRuntimeException
     */
    private static void loadTexture(final String textureString) throws GdxRuntimeException {
        logger.info("The Immortal | Loading Texture: " + textureString);
        Texture texture = new Texture(textureString);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        textures.put(textureString, texture);
    }

    public static String getAndLoadCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString = getCardTextureString(cardName, cardType);
        String originalString = textureString;

        if (textures.get(textureString) == null) {
            try {
                loadTexture(textureString);
            } catch (GdxRuntimeException e) {
                switch (cardType) {
                    case ATTACK:
                        textureString = makeCardPath("attacks/default.png");
                        break;
                    case SKILL:
                        textureString = makeCardPath("skills/default.png");
                        break;
                    case POWER:
                        textureString = makeCardPath("powers/default.png");
                        break;
                    default:
                        textureString = makeUIPath("missing_texture.png");
                        break;
                }

                loadCardTexture(originalString, textureString, true);
            }
        }
        //no exception, file exists
        return originalString;
    }

    private static void loadCardTexture(final String textureKey, final String textureString, boolean linearFilter) throws GdxRuntimeException {
        if (!textures.containsKey(textureString))
        {
            Texture texture = new Texture(textureString);
            if (linearFilter)
            {
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            else
            {
                texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            }
            if (!imgMap.containsKey(textureKey))
                imgMap.put(textureKey, texture);
            textures.put(textureKey, texture);
        }
        else
        {
            if (!imgMap.containsKey(textureKey))
                imgMap.put(textureKey, textures.get(textureString));
            textures.put(textureKey, textures.get(textureString));
        }
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
                textureString = makeCardPath("attacks/" + cardName + ".png");
                break;
            case SKILL:
                textureString = makeCardPath("skills/" + cardName + ".png");
                break;
            case POWER:
                textureString = makeCardPath("powers/" + cardName + ".png");
                break;
            default:
                textureString = makeUIPath("missing_texture.png");
                break;
        }
        //no exception, file exists
        return textureString;
    }

}
