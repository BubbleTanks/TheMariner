package marinermod.patches;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.sun.jndi.ldap.Ber;
import marinermod.cardmods.AnchorMod;
import marinermod.cardmods.BermudaMod;
import marinermod.powers.DrowningPower;

import javax.smartcardio.Card;
import java.awt.event.WindowStateListener;

public class SunkenPatch {

    @SpirePatch2(clz = AbstractRoom.class, method = "endTurn")
    public static class SunkenClass{
        @SpireInsertPatch(rloc = 1)
        public static void SunkenHook() {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof SunkenCardInterface || CardModifierManager.hasModifier(c, BermudaMod.ID) || CardModifierManager.hasModifier(c, AnchorMod.ID)) {
                    AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                        @Override
                        public void update() {
                            c.target_x = Settings.WIDTH/2f;
                            c.target_y = Settings.HEIGHT/2f;
                            c.flash();
                            this.isDone = true;
                        }
                    });
                    int j = 1;
                    if (AbstractDungeon.player.hasPower(DrowningPower.ID)) {
                        j += AbstractDungeon.player.getPower(DrowningPower.ID).amount;
                        AbstractDungeon.player.getPower(DrowningPower.ID).flash();
                    }
                    for (int i = 0; i < j; i++) {
                        if (c instanceof SunkenCardInterface) {

                            ((SunkenCardInterface) c).onSunken(AbstractDungeon.player);

                        }
                        if (CardModifierManager.hasModifier(c, BermudaMod.ID)) {
                            for (AbstractCardModifier c2 : CardModifierManager.getModifiers(c, BermudaMod.ID)) {
                                ((BermudaMod) c2).onSunken(AbstractDungeon.player);
                            }
                        }
                        if (CardModifierManager.hasModifier(c, AnchorMod.ID)) {
                            for (AbstractCardModifier c2 : CardModifierManager.getModifiers(c, AnchorMod.ID)) {
                                ((AnchorMod) c2).onSunken(AbstractDungeon.player);
                            }
                        }
                    }
                    for (AbstractPower p : AbstractDungeon.player.powers) {
                        if (p instanceof SunkenPowerInterface) {
                            ((SunkenPowerInterface) p).onSunken(AbstractDungeon.player, c);
                        }
                    }
                }
            }
        }

        public interface SunkenCardInterface {

            void onSunken(AbstractPlayer p);

        }

        public interface SunkenPowerInterface {

            void onSunken(AbstractPlayer p, AbstractCard c);

        }
    }
}
