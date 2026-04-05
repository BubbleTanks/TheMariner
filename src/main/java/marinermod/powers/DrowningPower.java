package marinermod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;

public class DrowningPower extends BasePower {

    public static String ID = MarinerMod.makeID(DrowningPower.class.getSimpleName());

    public DrowningPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        int i = 0;
        if (amount == 1) i++;
        this.description = String.format(DESCRIPTIONS[i], this.amount);
    }

}


