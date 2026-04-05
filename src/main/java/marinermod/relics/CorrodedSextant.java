package marinermod.relics;

import borealiscards.cards.curses.ElectromagneticInstability;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.character.MarinerCharacter;
import marinermod.patches.OnMonsterDeathPatch;

public class CorrodedSextant extends BaseRelic {

    public static final String ID = MarinerMod.makeID(CorrodedSextant.class.getSimpleName());

    public CorrodedSextant() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK);
        pool = MarinerCharacter.Meta.CARD_COLOR;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onMonsterDeath(AbstractMonster m) {

        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainBlockAction(AbstractDungeon.player, 8));
        }
    }

}
