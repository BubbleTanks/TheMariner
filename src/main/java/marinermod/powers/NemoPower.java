package marinermod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;

public class NemoPower extends BasePower {

    public static String ID = MarinerMod.makeID(NemoPower.class.getSimpleName());

    public NemoPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {

            int block = 0;

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying) block += amount;
            }

            addToBot(new GainBlockAction(AbstractDungeon.player, block));
        }
    }

}


