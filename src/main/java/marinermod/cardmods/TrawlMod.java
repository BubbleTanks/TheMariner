package marinermod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import marinermod.MarinerMod;
import marinermod.patches.Fields;

public class TrawlMod extends AbstractCardModifier {
    public static String ID = MarinerMod.makeID(TrawlMod.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MarinerMod.makeID(TrawlMod.class.getSimpleName()));

    @Override
    public AbstractCardModifier makeCopy() {
        return new TrawlMod();
    }

    public void onInitialApplication(AbstractCard card) {
        Fields.marinerField.trawlCard.set(card, true);
    }

    public void onRemove(AbstractCard card) {
        Fields.marinerField.trawlCard.set(card, false);
    }

    public boolean shouldApply(AbstractCard card) {
        return (!CardModifierManager.hasModifier(card,ID) || !Fields.marinerField.trawlCard.get(card));
    }

    public String modifyDescription(String raw, AbstractCard card) {
        return raw + uiStrings.TEXT[0];
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}

