package marinermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.actions.LethalAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.util.CardStats;

public class Cripple extends BaseCard {
    public static final String ID = makeID(Cripple.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public Cripple() {
        super(ID, info);
        setDamage(14,4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LethalAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY, (hasKilled)->{
            if (!hasKilled) {
                addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
            }
        }));
    }
}
