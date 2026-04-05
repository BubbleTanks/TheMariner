package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

public class TidePool extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface, AbyssalPatch.AbyssalInterface {
    public static final String ID = makeID(TidePool.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public TidePool() {
        super(ID, info);
        setBlock(15,3);
        setCustomVar("block2", VariableType.BLOCK, 5,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void onAbyssal(AbstractPlayer p) {
        addToBot(new GainBlockAction(p, customVar("block2")));
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        addToBot(new GainBlockAction(p, customVar("block2")));
    }
}
