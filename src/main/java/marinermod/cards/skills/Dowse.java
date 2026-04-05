package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.DredgeAction;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.util.CardStats;

public class Dowse extends BaseCard {
    public static final String ID = makeID(Dowse.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public Dowse() {
        super(ID, info);
        setMagic(3,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SubmergeAction(magicNumber));
        addToBot(new DrawCardAction(1));
    }
}
