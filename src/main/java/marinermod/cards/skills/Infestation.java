package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

public class Infestation extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface {
    public static final String ID = makeID(Infestation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0
    );

    public Infestation() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        addToBot(new SummonBarnacleAction());
        if (upgraded) addToBot(new SummonBarnacleAction());
        addToBot(new MakeTempCardInDrawPileAction(this.makeStatEquivalentCopy(), 1, false, true, false));
    }
}
