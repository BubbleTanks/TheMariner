package marinermod.powers;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.cardmods.BallastMod;
import marinermod.patches.SunkenPatch;

public class DeepestDepthsPower extends BasePower implements NonStackablePower {

    public static String ID = MarinerMod.makeID(DeepestDepthsPower.class.getSimpleName());
    public AbstractCard card = null;

    public DeepestDepthsPower(AbstractCreature owner, AbstractCard card) {
        super(ID, PowerType.BUFF, false, owner, -1);

        this.card = card.makeStatEquivalentCopy();
        updateDescription();
        if (this.card != null) CardModifierManager.addModifier(this.card, new BallastMod());
    }

    public void updateDescription() {
        if (card != null) this.description = String.format(DESCRIPTIONS[0], card.name);
        else this.description = String.format(DESCRIPTIONS[0], "null");
    }

    public void atStartOfTurnPostDraw() {
        if (card != null) {
            flash();
            addToBot(new MakeTempCardInHandAction(card));
        }
    }

}
