package marinermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import marinermod.actions.LethalAOEAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

public class SorrowfulRain extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface {
    public static final String ID = makeID(SorrowfulRain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    public SorrowfulRain() {
        super(ID, info);
        setDamage(8,3);
        setMagic(2);
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        }
    }
}

