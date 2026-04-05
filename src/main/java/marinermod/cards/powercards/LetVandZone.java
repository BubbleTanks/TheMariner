package marinermod.cards.powercards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.cards.BaseCard;
import marinermod.cards.skills.RottenCoral;
import marinermod.character.MarinerCharacter;
import marinermod.powers.DrowningPower;
import marinermod.powers.LetVandPower;
import marinermod.util.CardStats;

public class LetVandZone extends BaseCard {
    public static final String ID = makeID(LetVandZone.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public LetVandZone() {
        super(ID, info);
        setMagic(1,0);
        cardsToPreview = new RottenCoral();
    }

    public void upgrade() {
        if (!upgraded) cardsToPreview.upgrade();
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LetVandPower(p, magicNumber, upgraded)));
    }
}
