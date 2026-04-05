package marinermod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import marinermod.MarinerMod;

public class EnrichmentPower extends BasePower {

    public static String ID = MarinerMod.makeID(EnrichmentPower.class.getSimpleName());

    public EnrichmentPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        int i = 0;
        if (amount == 1) i++;
        this.description = String.format(DESCRIPTIONS[i], this.amount);
    }









}
