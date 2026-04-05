package marinermod.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.patches.SunkenPatch;

public class LeadBootsPower extends BasePower {

    public static String ID = MarinerMod.makeID(LeadBootsPower.class.getSimpleName());

    public LeadBootsPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new SubmergeAction(amount));
    }
}


