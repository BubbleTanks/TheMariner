package marinermod.relics;

import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BagOfPreparation;
import marinermod.MarinerMod;
import marinermod.actions.SummonBarnacleAction;
import marinermod.character.MarinerCharacter;

public class EncrustedSpyglass extends BaseRelic {

    public static final String ID = MarinerMod.makeID(EncrustedSpyglass.class.getSimpleName());

    public EncrustedSpyglass() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK);
        pool = MarinerCharacter.Meta.CARD_COLOR;
        this.tips.add(new PowerTip(MarinerMod.keywords.get("Barnacle").PROPER_NAME, MarinerMod.keywords.get("Barnacle").DESCRIPTION));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new SummonBarnacleAction());
    }
}
