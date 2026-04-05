package marinermod.cards.powercards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RetainCardPower;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.SunkenPatch;
import marinermod.powers.DrowningPower;
import marinermod.util.CardStats;

public class ShiftingSeas extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface {
    public static final String ID = makeID(ShiftingSeas.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public ShiftingSeas() {
        super(ID, info);
        setMagic(2,0);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RetainCardPower(p, magicNumber)));
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        addToTop(new SelectCardsInHandAction(3, cardStrings.EXTENDED_DESCRIPTION[0], true, true, (c -> {
            if (c.cardID == ShiftingSeas.ID || c.retain || c.selfRetain) return false;
            return true;
        }), (retainList)->{

            for (AbstractCard c : retainList) {
                c.retain = true;
            }

        }));
    }
}
