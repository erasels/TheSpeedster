package theSpeedster;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theSpeedster.cards.variables.MagicNumber2;
import theSpeedster.cards.variables.ShowNumber;
import theSpeedster.characters.SpeedsterCharacter;
import theSpeedster.mechanics.speed.AbstractSpeedTime;
import theSpeedster.util.TextureLoader;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class TheSpeedster implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        PreStartGameSubscriber,
        PostUpdateSubscriber {
    public static final Logger logger = LogManager.getLogger(TheSpeedster.class.getName());
    private static String modID;

    public static Properties theSpeedsterSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;

    public static AbstractSpeedTime speedScreen;

    private static final String MODNAME = "The Speedster";
    private static final String AUTHOR = "erasels";
    private static final String DESCRIPTION = "TODO"; //TODO: Write character Description

    public static final Color SPEEDSTER_GREEN = CardHelper.getColor(36, 60, 36);

    private static final String ATTACK_SPEEDSTER_GREEN = "theSpeedsterResources/images/512/bg_attack_immortal_red.png";
    private static final String SKILL_SPEEDSTER_GREEN = "theSpeedsterResources/images/512/bg_skill_immortal_red.png";
    private static final String POWER_SPEEDSTER_GREEN = "theSpeedsterResources/images/512/bg_power_immortal_red.png";

    private static final String ENERGY_ORB_SPEEDSTER_GREEN = "theSpeedsterResources/images/512/card_immortal_red_orb.png";
    private static final String CARD_ENERGY_ORB = "theSpeedsterResources/images/512/card_small_orb.png";

    private static final String ATTACK_SPEEDSTER_GREEN_PORTRAIT = "theSpeedsterResources/images/1024/bg_attack_immortal_red.png";
    private static final String SKILL_SPEEDSTER_GREEN_PORTRAIT = "theSpeedsterResources/images/1024/bg_skill_immortal_red.png";
    private static final String POWER_SPEEDSTER_GREEN_PORTRAIT = "theSpeedsterResources/images/1024/bg_power_immortal_red.png";
    private static final String ENERGY_ORB_SPEEDSTER_GREEN_PORTRAIT = "theSpeedsterResources/images/1024/card_immortal_red_orb.png";

    private static final String THE_SPEEDSTER_BUTTON = "theSpeedsterResources/images/charSelect/CharacterButton.png";
    private static final String THE_SPEEDSTER_PORTRAIT = "theSpeedsterResources/images/charSelect/CharacterPortraitBG.png";
    public static final String THE_SPEEDSTER_SHOULDER_1 = "theSpeedsterResources/images/char/shoulder.png";
    public static final String THE_SPEEDSTER_SHOULDER_2 = "theSpeedsterResources/images/char/shoulder2.png";
    public static final String THE_SPEEDSTER_CORPSE = "theSpeedsterResources/images/char/corpse.png";

    public static final String BADGE_IMAGE = "theSpeedsterResources/images/Badge.png";

    public static final String THE_SPEEDSTER_SKELETON_ATLAS = "theSpeedsterResources/images/char/skeleton.atlas";
    public static final String THE_SPEEDSTER_SKELETON_JSON = "theSpeedsterResources/images/char/skeleton.json";

    public TheSpeedster() {
        BaseMod.subscribe(this);

        setModID("theSpeedster");

        logger.info("Creating the color " + SpeedsterCharacter.Enums.COLOR_SPEEDSTER.toString());

        BaseMod.addColor(SpeedsterCharacter.Enums.COLOR_SPEEDSTER, SPEEDSTER_GREEN, SPEEDSTER_GREEN, SPEEDSTER_GREEN,
                SPEEDSTER_GREEN, SPEEDSTER_GREEN, SPEEDSTER_GREEN, SPEEDSTER_GREEN,
                ATTACK_SPEEDSTER_GREEN, SKILL_SPEEDSTER_GREEN, POWER_SPEEDSTER_GREEN, ENERGY_ORB_SPEEDSTER_GREEN,
                ATTACK_SPEEDSTER_GREEN_PORTRAIT, SKILL_SPEEDSTER_GREEN_PORTRAIT, POWER_SPEEDSTER_GREEN_PORTRAIT,
                ENERGY_ORB_SPEEDSTER_GREEN_PORTRAIT, CARD_ENERGY_ORB);

        theSpeedsterSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("theSpeedster", "theSpeedsterConfig", theSpeedsterSettings);
            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        TheSpeedster immortalMod = new TheSpeedster();
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + SpeedsterCharacter.Enums.THE_SPEEDSTER.toString());
        BaseMod.addCharacter(new SpeedsterCharacter("the Speedster", SpeedsterCharacter.Enums.THE_SPEEDSTER), THE_SPEEDSTER_BUTTON, THE_SPEEDSTER_PORTRAIT, SpeedsterCharacter.Enums.THE_SPEEDSTER);
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();

        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, enablePlaceholder, settingsPanel, (label) -> {
        }, (button) -> {
            enablePlaceholder = button.enabled;
            try {
                SpireConfig config = new SpireConfig("theSpeedster", "theSpeedsterConfig", theSpeedsterSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsPanel.addUIElement(enableNormalsButton);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    @Override
    public void receivePostUpdate() {
        if (speedScreen != null && speedScreen.isDone) {
            speedScreen = null;
        }
    }

    @Override
    public void receivePreStartGame() {
    }

    @Override
    public void receiveEditRelics() {
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new MagicNumber2());
        BaseMod.addDynamicVariable(new ShowNumber());

        try {
            AutoLoader.addCards();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/cardStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/powerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/relicStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, getModID() + "Resources/localization/eng/eventStrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, getModID() + "Resources/localization/eng/potionStrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/characterStrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, getModID() + "Resources/localization/eng/orbStrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, getModID() + "Resources/localization/eng/uiStrings.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, getModID() + "Resources/localization/eng/monsterStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/keywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    public static String makePath(String resourcePath) {
        return getModID() + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return getModID() + "Resources/images/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeUIPath(String resourcePath) {
        return getModID() + "Resources/images/ui/" + resourcePath;
    }

    public static String makeCharPath(String resourcePath) {
        return getModID() + "Resources/images/char/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }


    public static void setModID(String ID) {
        modID = ID;
    }

    public static String getModID() { // NO
        return modID;
    }
}
