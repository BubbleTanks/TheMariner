package marinermod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import marinermod.MarinerMod;

public class BermudaPower extends BasePower implements InvisiblePower {

    public static String ID = MarinerMod.makeID(BermudaPower.class.getSimpleName());

    public BermudaPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

}


