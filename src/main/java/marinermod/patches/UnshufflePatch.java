package marinermod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.Iterator;

public class UnshufflePatch {

    @SpirePatch2(clz = DrawCardAction.class, method = "update")
    public static class StopShuffling {
        @SpireInsertPatch(locator = Locator2.class)
        public static SpireReturn<Void> john(DrawCardAction __instance) {

            int i = 0;

            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (Fields.marinerField.ballastCard.get(c)) {
                    i++;
                }
            }


            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() - i == 0) {

                ReflectionHacks.privateMethod(DrawCardAction.class, "endActionWithFollowUp").invoke(__instance);

                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }

        private static class Locator2 extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "hand");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

    }

}
