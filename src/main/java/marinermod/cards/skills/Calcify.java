package marinermod.cards.skills;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import marinermod.actions.DredgeAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.monsters.Barnacle;
import marinermod.patches.SunkenPatch;
import marinermod.powers.EnrichmentPower;
import marinermod.util.CardStats;

public class Calcify extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface {
    public static final String ID = makeID(Calcify.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Calcify() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SummonBarnacleAction());
        addToBot(new SummonBarnacleAction());
        if (upgraded) {
            addToBot(new DrawCardAction(1));
        }
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        addToBot(new SummonBarnacleAction());
    }
}
