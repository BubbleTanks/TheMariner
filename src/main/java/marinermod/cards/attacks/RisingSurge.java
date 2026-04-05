package marinermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.Fields;
import marinermod.util.CardStats;

public class RisingSurge extends BaseCard implements SubmergeAction.SubmergeInterface {
    public static final String ID = makeID(RisingSurge.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    public boolean submergedThisTurn = false;

    public RisingSurge() {
        super(ID, info);
        setDamage(14, 4);
    }

    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if (submergedThisTurn) {
            damage *= 3;
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (submergedThisTurn) {
            damage *= 3;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!submergedThisTurn) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public void onSubmerge(AbstractPlayer p) {
        submergedThisTurn = true;
    }
}
