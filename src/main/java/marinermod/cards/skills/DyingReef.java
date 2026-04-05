package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.util.CardStats;

public class DyingReef extends BaseCard implements AbyssalPatch.AbyssalInterface {
    public static final String ID = makeID(DyingReef.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public DyingReef() {
        super(ID, info);
        setBlock(7,3);
        tags.add(MarinerMod.REEF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void onAbyssal(AbstractPlayer p) {
        addToBot(new SummonBarnacleAction());
    }
}
