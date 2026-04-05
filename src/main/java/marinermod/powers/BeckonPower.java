package marinermod.powers;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import marinermod.MarinerMod;
import marinermod.cardmods.BeckonMod;

public class BeckonPower extends BasePower implements OnCreateCardInterface {

    public static String ID = MarinerMod.makeID(BeckonPower.class.getSimpleName());

    public BeckonPower(AbstractCreature owner) {
        super(ID, PowerType.BUFF, false, owner, -1);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }


    @Override
    public void onCreateCard(AbstractCard c) {

        CardModifierManager.addModifier(c, new BeckonMod());
        c.applyPowers();

    }
}


