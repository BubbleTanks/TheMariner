package marinermod.cards.powercards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.Fields;
import marinermod.powers.DrowningPower;
import marinermod.powers.NemoPower;
import marinermod.util.CardStats;

public class NemosMap extends BaseCard {
    public static final String ID = makeID(NemosMap.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public NemosMap() {
        super(ID, info);
        setMagic(1);
        Fields.marinerField.ballastCard.set(this,true);
    }

    public void upgrade() {
        if (!upgraded) {
            Fields.marinerField.ballastCard.set(this,false);
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NemoPower(p, magicNumber)));
    }
}
