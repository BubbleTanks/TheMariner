package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.actions.DredgeAction;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.util.CardStats;

public class GlimmeringReef extends BaseCard implements AbyssalPatch.AbyssalInterface {
    public static final String ID = makeID(GlimmeringReef.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );

    public GlimmeringReef() {
        super(ID, info);
        setMagic(2,0);
        setCustomVar("magic2", VariableType.MAGIC, 2,1);
        setCustomVar("magic3", VariableType.MAGIC, 1,1);
        tags.add(MarinerMod.REEF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new SubmergeAction(customVar("magic2")));
    }

    @Override
    public void onAbyssal(AbstractPlayer p) {
        addToBot(new DrawCardAction(customVar("magic2")));
    }
}
