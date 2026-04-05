package marinermod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.monsters.Barnacle;
import marinermod.patches.OnMonsterDeathPatch;

public class NautilusPower extends BasePower implements OnMonsterDeathPatch.MonsterDeathInterface {

    public static String ID = MarinerMod.makeID(NautilusPower.class.getSimpleName());

    public NautilusPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0]);
        for (int i = 0; i < amount; i++) {
            description += "[R] ";
        }
        description += LocalizedStrings.PERIOD;
    }

    public void onMonsterDeath(AbstractMonster m) {

        if (m.id == Barnacle.ID) {
            addToBot(new GainEnergyAction(amount));
        }

    }

}


