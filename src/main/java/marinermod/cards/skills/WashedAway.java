package marinermod.cards.skills;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

public class WashedAway extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface {
    public static final String ID = makeID(WashedAway.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public WashedAway() {
        super(ID, info);
        setBlock(8,3);
        setDamage(6,3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        AbstractMonster target = null;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (target == null) {
                target = m;
            } else if (m.currentHealth > target.currentHealth) {
                target = m;
            } else if (m.currentHealth == target.currentHealth) {
                if (AbstractDungeon.cardRng.randomBoolean()) {
                    target = m;
                }
            }
        }

        if (target != null) {
            addToBot(new DamageAction(target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }
}
