package marinermod.cards.skills;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.util.CardStats;

public class RottenCoral extends BaseCard {
    public static final String ID = makeID(RottenCoral.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            0
    );

    public RottenCoral() {
        super(ID, info);
        setMagic(3,1);
        setSelfRetain(true);
        setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SubmergeAction(magicNumber));
        addToBot(new DrawCardAction(1));
    }


}
