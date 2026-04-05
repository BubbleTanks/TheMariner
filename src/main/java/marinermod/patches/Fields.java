package marinermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fields {

    @SpirePatch(clz = AbstractMonster.class, method = SpirePatch.CLASS)
    public static class BehaveYourself {
        public static SpireField<Boolean> stopFuckingPlaying = new SpireField<>(() -> false);
    }

    @SpirePatch(clz= AbstractCard.class, method = SpirePatch.CLASS)
    public static class marinerField {
        public static SpireField<Boolean> trawlCard = new SpireField<>(() -> false);
        public static SpireField<Boolean> ballastCard = new SpireField<>(() -> false);
        public static SpireField<Boolean> ahoyCard = new SpireField<>(() -> false);
    }

}
