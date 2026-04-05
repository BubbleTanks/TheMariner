package marinermod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.patches.Fields;
import marinermod.powers.BermudaPower;

public class BermudaMod extends AbstractCardModifier {
    public static String ID = MarinerMod.makeID(BermudaMod.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MarinerMod.makeID(BermudaMod.class.getSimpleName()));

    @Override
    public AbstractCardModifier makeCopy() {
        return new BermudaMod();
    }

    public void onSunken(AbstractPlayer p) {
        if (AbstractDungeon.player.hasPower(BermudaPower.ID)) {
            addToBot(new SubmergeAction(AbstractDungeon.player.getPower(BermudaPower.ID).amount));
        }
    }

    public void onAbyssal(AbstractPlayer p) {
        if (AbstractDungeon.player.hasPower(BermudaPower.ID)) {
            addToBot(new SubmergeAction(AbstractDungeon.player.getPower(BermudaPower.ID).amount));
        }
    }

    public boolean shouldApply(AbstractCard card) {
        return (!CardModifierManager.hasModifier(card,ID));
    }

    public String modifyDescription(String raw, AbstractCard card) {
        return raw + String.format(uiStrings.TEXT[0], AbstractDungeon.player.getPower(BermudaPower.ID).amount, AbstractDungeon.player.getPower(BermudaPower.ID).amount);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}

