package marinermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.util.CardStats;

public class Overpopulation extends BaseCard {
    public static final String ID = makeID(Overpopulation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0
    );

    public Overpopulation() {
        super(ID, info);
        setExhaust(true);
        setInnate(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SummonBarnacleAction());
        addToBot(new SummonBarnacleAction());
        addToBot(new SummonBarnacleAction());
        addToBot(new SummonBarnacleAction());
        addToBot(new SummonBarnacleAction());
    }
}
