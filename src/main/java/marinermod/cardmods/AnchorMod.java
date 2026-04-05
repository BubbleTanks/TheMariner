package marinermod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.powers.BermudaPower;

public class AnchorMod extends AbstractCardModifier {
    public static String ID = MarinerMod.makeID(AnchorMod.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MarinerMod.makeID(AnchorMod.class.getSimpleName()));

    @Override
    public AbstractCardModifier makeCopy() {
        return new AnchorMod();
    }

    public void onSunken(AbstractPlayer p) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(2, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }

    public boolean shouldApply(AbstractCard card) {
        return (!CardModifierManager.hasModifier(card,ID));
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}

