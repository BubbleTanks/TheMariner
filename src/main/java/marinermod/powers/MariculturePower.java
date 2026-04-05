package marinermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.patches.OnMonsterDeathPatch;

public class MariculturePower extends BasePower implements OnMonsterDeathPatch.MonsterDeathInterface {

    public static String ID = MarinerMod.makeID(MariculturePower.class.getSimpleName());

    public MariculturePower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void onMonsterDeath(AbstractMonster m) {

        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

    }
}


