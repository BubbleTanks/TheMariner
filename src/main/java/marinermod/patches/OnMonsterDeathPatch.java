package marinermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import marinermod.monsters.Barnacle;

import java.util.Iterator;

public class OnMonsterDeathPatch {

    @SpirePatch2(clz = AbstractMonster.class, method = "die", paramtypez = {boolean.class})
    public static class MonsterDiePower {
        @SpireInsertPatch(locator = Locator.class)
        public static void onDie(AbstractMonster __instance) {

            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    Fields.BehaveYourself.stopFuckingPlaying.set(__instance, true);
                    this.isDone = true;
                }
            });

            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            boolean isBarnacle = true;

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {

                if (!(m instanceof Barnacle) && !m.isDeadOrEscaped() && !m.isDead && !m.isDying) {
                    isBarnacle = false;
                }

            }

            if (isBarnacle) {

                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if (m.id == Barnacle.ID && !m.isDead && !m.isDying) {
                        AbstractDungeon.actionManager.addToTop(new HideHealthBarAction(m));
                        AbstractDungeon.actionManager.addToTop(new SuicideAction(m));
                    }
                }

            }

            }

            for (AbstractPower p : AbstractDungeon.player.powers) {

                if (p instanceof MonsterDeathInterface) {
                    ((MonsterDeathInterface) p).onMonsterDeath(__instance);
                }

            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    public interface MonsterDeathInterface {

        void onMonsterDeath(AbstractMonster m);

    }

}
