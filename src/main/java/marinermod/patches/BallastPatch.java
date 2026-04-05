package marinermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BallastPatch {

    @SpirePatch2(clz = SoulGroup.class, method = "shuffle")
    public static class SoulShufflePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> SoulReturn(AbstractCard card) {

            if (Fields.marinerField.ballastCard.get(card)) {

                AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.player.discardPile.addToTop(card);
                        this.isDone = true;
                    }
                });

                return SpireReturn.Return();
            }

            return SpireReturn.Continue();
        }
    }



}
