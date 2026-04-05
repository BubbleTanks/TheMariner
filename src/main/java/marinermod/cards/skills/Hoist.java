package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.DredgeAction;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.util.CardStats;

public class Hoist extends BaseCard {
    public static final String ID = makeID(Hoist.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Hoist() {
        super(ID, info);
        setMagic(2,2);
        setCustomVar("magic2", VariableType.MAGIC, 4,2);
        setCustomVar("magic3", VariableType.MAGIC, 6,2);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DredgeAction(magicNumber));
        addToBot(new DredgeAction(customVar("magic2")));
        addToBot(new DredgeAction(customVar("magic3")));
    }
}
