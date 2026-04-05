package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.DredgeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.Fields;
import marinermod.powers.ShipHullPower;
import marinermod.util.CardStats;

public class Ahoy extends BaseCard {
    public static final String ID = makeID(Ahoy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    public Ahoy() {
        super(ID, info);
        setMagic(1,1);
        Fields.marinerField.ahoyCard.set(this,true);
        Fields.marinerField.trawlCard.set(this,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DredgeAction(magicNumber));
    }
}
