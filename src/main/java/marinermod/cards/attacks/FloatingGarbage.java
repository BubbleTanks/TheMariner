package marinermod.cards.attacks;

import basemod.AutoAdd;
import borealiscards.patches.rarities.CustomRarity;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.*;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.patches.Fields;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

@AutoAdd.Ignore
public class FloatingGarbage extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface, AbyssalPatch.AbyssalInterface {
    public static final String ID = makeID(FloatingGarbage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CustomRarity.EXOTIC,
            CardTarget.ENEMY,
            2
    );

    public FloatingGarbage() {
        super(ID, info);
        setDamage(12,3);
        setMagic(2,1);
        Fields.marinerField.trawlCard.set(this, true);
    }

    @Override
    public void upgrade() {
        Fields.marinerField.ballastCard.set(this, true);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LethalAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY, (hasKilled)->{
            if (hasKilled) addToBot(new MakeTempCardInDrawPileAction(this.makeStatEquivalentCopy(), 1, true, true));
        }));
        addToBot(new SubmergeAction(magicNumber));
        addToBot(new DredgeAction(magicNumber));
    }

    @Override
    public void onAbyssal(AbstractPlayer p) {
        if (upgraded) {
            addToBot(new PlayFloatingGarbageAction(this));
        }
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new SummonBarnacleAction());
        }
    }
}
