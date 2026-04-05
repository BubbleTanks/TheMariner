package marinermod.cards.powercards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.LetVandPower;
import marinermod.powers.WateryGravesPower;
import marinermod.util.CardStats;

public class WateryGraves extends BaseCard {
    public static final String ID = makeID(WateryGraves.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public WateryGraves() {
        super(ID, info);
        setMagic(1,0);
        setInnate(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WateryGravesPower(p, magicNumber)));
    }
}
