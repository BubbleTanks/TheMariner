package marinermod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnDrawPileShufflePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.cards.skills.RottenCoral;

public class LetVandPower extends BasePower implements OnDrawPileShufflePower, NonStackablePower {

    public static String ID = MarinerMod.makeID(LetVandPower.class.getSimpleName());
    public boolean upgraded;

    public LetVandPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(ID, PowerType.BUFF, false, owner, amount);
        this.upgraded = upgraded;
    }

    public void updateDescription() {
        String plural = DESCRIPTIONS[2];
        if (amount > 1) plural = DESCRIPTIONS[3];
        if (!upgraded) this.description = String.format(DESCRIPTIONS[0], this.amount, plural);
        if (upgraded) this.description = String.format(DESCRIPTIONS[1], this.amount, plural);
    }

    @Override
    public void onShuffle() {
        for (int i = 0; i < amount; i++) {
            AbstractCard coral = new RottenCoral();
            if (upgraded) coral.upgrade();
            addToBot(new MakeTempCardInHandAction(coral));
        }
    }

    public boolean isStackable(AbstractPower po) {
        if(po instanceof LetVandPower) {
            return ((LetVandPower) po).upgraded == this.upgraded;
        }
        return false;
    }
}


