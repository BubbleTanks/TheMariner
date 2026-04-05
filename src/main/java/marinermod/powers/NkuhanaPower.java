package marinermod.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import marinermod.MarinerMod;

public class NkuhanaPower extends BasePower {

    public static String ID = MarinerMod.makeID(NkuhanaPower.class.getSimpleName());

    public NkuhanaPower(AbstractCreature owner) {
        super(ID, PowerType.BUFF, false, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atEndOfTurn(boolean isPlayer) {

        if (AbstractDungeon.player.drawPile.isEmpty()) {
            addToBot(new VFXAction(new WhirlwindEffect(new Color(0.2F, 0.9F, 0.6F, 1.0F), true)));
            addToBot(new SkipEnemiesTurnAction());
        }

        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, NkuhanaPower.ID));
    }

}


