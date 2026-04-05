package marinermod.cards.powercards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.DrowningPower;
import marinermod.powers.HardeningPower;
import marinermod.util.CardStats;

public class Hardening extends BaseCard {
    public static final String ID = makeID(Hardening.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Hardening() {
        super(ID, info);
        setMagic(1,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HardeningPower(p, magicNumber)));
    }
}
