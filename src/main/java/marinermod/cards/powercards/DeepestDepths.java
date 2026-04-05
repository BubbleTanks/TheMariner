package marinermod.cards.powercards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.DeepestDepthsPower;
import marinermod.util.CardStats;

public class DeepestDepths extends BaseCard {
    public static final String ID = makeID(DeepestDepths.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public DeepestDepths() {
        super(ID, info);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToTop(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], true, false, (c -> {
            return true;
        }), (retainList)->{

            for (AbstractCard c : retainList) {
                addToBot(new ApplyPowerAction(p, p, new DeepestDepthsPower(p, c)));
            }

        }));


    }
}
