package marinermod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.Fields;
import marinermod.util.CardStats;

public class Twilight extends BaseCard implements SubmergeAction.SubmergeInterface {
    public static final String ID = makeID(Twilight.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public Twilight() {
        super(ID, info);
        setMagic(2,1);
        Fields.marinerField.trawlCard.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false)));
    }

    @Override
    public void onSubmerge(AbstractPlayer p) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                setCostForTurn(0);
                this.isDone = true;
            }
        });
    }
}
