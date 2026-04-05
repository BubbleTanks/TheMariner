package marinermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Reptomancer;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import marinermod.monsters.Barnacle;

import java.util.Iterator;

public class SummonerPatch {

    @SpirePatch2(clz = Reptomancer.class, method = "canSpawn")
    public static class SummonerClass1 {
        @SpireInsertPatch(rloc = 1, localvars = {"aliveCount"})
        public static void canBarnacle(@ByRef int[] aliveCount) {

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m.id == Barnacle.ID && !m.isDying) {
                    aliveCount[0]--;
                }
            }

        }
    }

    @SpirePatch2(clz = GremlinLeader.class, method = "numAliveGremlins")
    public static class SummonerClass2 {
        @SpireInsertPatch(rloc = 1, localvars = {"count"})
        public static void canBarnacle(@ByRef int[] count) {

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m.id == Barnacle.ID && !m.isDying) {
                    count[0]--;
                }
            }

        }
    }

}
