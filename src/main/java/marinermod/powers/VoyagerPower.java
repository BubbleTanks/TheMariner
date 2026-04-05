package marinermod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;

public class VoyagerPower extends BasePower {

    public static String ID = MarinerMod.makeID(VoyagerPower.class.getSimpleName());

    public VoyagerPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void atStartOfTurn() {
        SubmergeAction.cardsVoyaged = 0;
    }

    public void onVictory() {
        SubmergeAction.cardsVoyaged = 0;
    }

}


