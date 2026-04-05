package marinermod.cards.powercards;

import basemod.AutoAdd;
import borealiscards.BorealisCards;
import borealiscards.patches.rarities.CustomRarity;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.BermudaAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.BermudaPower;
import marinermod.powers.DrowningPower;
import marinermod.util.CardStats;

@AutoAdd.Ignore
public class BermudaTriangle extends BaseCard {
    public static final String ID = makeID(BermudaTriangle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CustomRarity.EXOTIC,
            CardTarget.SELF,
            1
    );

    public BermudaTriangle() {
        super(ID, info);
        setMagic(2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BermudaPower(p, magicNumber)));
        addToBot(new BermudaAction());
    }
}
