package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.util.CardStats;

public class Fathom extends BaseCard implements AbyssalPatch.AbyssalInterface {
    public static final String ID = makeID(Fathom.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0
    );

    public Fathom() {
        this(0);
    }

    public Fathom(int upgrades) {
        super(ID, info);
        setCustomVar("magic",2);
        timesUpgraded = upgrades;
    }

    public void upgrade() {
        setCustomVar("magic", customVar("magic") + 1);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        super.upgrade();
    }

    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                upgrade();
                superFlash();
                applyPowers();
                this.isDone = true;
            }
        });
    }

    @Override
    public void onAbyssal(AbstractPlayer p) {
        addToBot(new SubmergeAction(customVar("magic")));
    }
}
