package marinermod.cards.powercards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.DeepestDepthsPower;
import marinermod.powers.LeadBootsPower;
import marinermod.util.CardStats;

public class LeadBoots extends BaseCard {
    public static final String ID = makeID(LeadBoots.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public LeadBoots() {
        super(ID, info);
        setMagic(2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LeadBootsPower(p, magicNumber)));
    }
}
