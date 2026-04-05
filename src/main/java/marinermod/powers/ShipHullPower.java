package marinermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marinermod.MarinerMod;
import marinermod.actions.SummonBarnacleAction;

public class ShipHullPower extends BasePower {

    public static String ID = MarinerMod.makeID(ShipHullPower.class.getSimpleName());

    public ShipHullPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        int i = 0;
        if (amount == 1) i++;
        this.description = String.format(DESCRIPTIONS[i], this.amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            flash();
            for (int j = 0; j < amount; j++) {
                addToTop(new SummonBarnacleAction());
            }
        }

        return damageAmount;
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ShipHullPower.ID));
    }

}


