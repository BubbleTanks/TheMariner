package marinermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;

public class TenebrisPower extends BasePower {

    public static String ID = MarinerMod.makeID(TenebrisPower.class.getSimpleName());

    public TenebrisPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
            addToTop(new SubmergeAction(amount));
            flash();
    }

}


