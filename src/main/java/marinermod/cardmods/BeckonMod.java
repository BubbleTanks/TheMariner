package marinermod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.patches.Fields;
import marinermod.powers.BermudaPower;

public class BeckonMod extends AbstractCardModifier {
    public static String ID = MarinerMod.makeID(BeckonMod.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MarinerMod.makeID(BeckonMod.class.getSimpleName()));

    @Override
    public AbstractCardModifier makeCopy() {
        return new BeckonMod();
    }

    public void onApplyPowers(AbstractCard card) {
        card.setCostForTurn(0);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardModifierManager.addModifier(card, new BallastMod());
                this.isDone = true;
            }
        });
    }

    public boolean shouldApply(AbstractCard card) {
        return (!CardModifierManager.hasModifier(card,ID));
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}

