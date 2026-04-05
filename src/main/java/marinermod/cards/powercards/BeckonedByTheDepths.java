package marinermod.cards.powercards;

import basemod.AutoAdd;
import borealiscards.patches.rarities.CustomRarity;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.BeckonAction;
import marinermod.actions.BermudaAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.BeckonPower;
import marinermod.powers.BermudaPower;
import marinermod.util.CardStats;

public class BeckonedByTheDepths extends BaseCard {
    public static final String ID = makeID(BeckonedByTheDepths.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public BeckonedByTheDepths() {
        super(ID, info);
        setEthereal(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BeckonPower(p)));
        addToBot(new BeckonAction());
    }
}
