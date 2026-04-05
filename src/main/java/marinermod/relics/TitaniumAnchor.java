package marinermod.relics;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.actions.AnchorAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cardmods.AnchorMod;
import marinermod.cardmods.BeckonMod;
import marinermod.character.MarinerCharacter;
import marinermod.patches.OnMonsterDeathPatch;

public class TitaniumAnchor extends BaseRelic implements OnCreateCardInterface {

    public static final String ID = MarinerMod.makeID(TitaniumAnchor.class.getSimpleName());

    public TitaniumAnchor() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY);
        pool = MarinerCharacter.Meta.CARD_COLOR;
        this.tips.add(new PowerTip(MarinerMod.keywords.get("Sunken").PROPER_NAME, MarinerMod.keywords.get("Sunken").DESCRIPTION));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        addToBot(new AnchorAction());
    }

    @Override
    public void onCreateCard(AbstractCard c) {

        CardModifierManager.addModifier(c, new AnchorMod());
        c.applyPowers();

    }
}
