package marinermod.patches;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import marinermod.cardmods.BermudaMod;
import marinermod.cards.skills.Atlantis;

import javax.smartcardio.Card;
import java.util.Iterator;

public class AbyssalPatch {

    @SpirePatch2(clz = EmptyDeckShuffleAction.class, method = "update")
    @SpirePatch2(clz = ShuffleAllAction.class, method = "update")
    public static class ShuffleActionPatch2 {
        @SpireInsertPatch(locator = Locator2.class, localvars = {"e"})
        public static void onShuffle(AbstractCard e) {

                if (e instanceof AbyssalInterface) {
                    ((AbyssalInterface) e).onAbyssal(AbstractDungeon.player);
                }

                if (CardModifierManager.hasModifier(e, BermudaMod.ID)) {
                    for (AbstractCardModifier e2 : CardModifierManager.getModifiers(e, BermudaMod.ID)) {
                        ((BermudaMod) e2).onAbyssal(AbstractDungeon.player);
                    }
                }

        }

        private static class Locator2 extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Iterator.class, "remove");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    public interface AbyssalInterface {

        void onAbyssal(AbstractPlayer p);

    }
}
