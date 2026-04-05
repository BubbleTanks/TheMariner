package marinermod.actions;

import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinThief;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import marinermod.monsters.Barnacle;
import marinermod.util.BarnaclePositionSaver;

import java.util.ArrayList;
import java.util.Iterator;

public class SummonBarnacleAction extends AbstractGameAction {
    private AbstractMonster m;

    public SummonBarnacleAction() {
        this.actionType = ActionType.SPECIAL;
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FAST;
        } else {
            this.startDuration = Settings.ACTION_DUR_LONG;
        }
        this.duration = this.startDuration;

        float x = AbstractDungeon.player.drawX;
        float y = AbstractDungeon.player.drawY;

        // Always spawn a GremlinThief (whos gonna tell him)
        this.m = new Barnacle(x, y);

        // Force the position since MonsterHelper.getGremlin seems to ignore our coordinates
        this.m.drawX = x;
        this.m.drawY = y;
        ((Barnacle)m).setSlot();

        // Trigger relic effects
        Iterator var3 = AbstractDungeon.player.relics.iterator();
        while(var3.hasNext()) {
            AbstractRelic r = (AbstractRelic)var3.next();
            r.onSpawnMonster(this.m);
        }
    }

    private int getSmartPosition() {
        int position = 0;
        for(Iterator var2 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator(); var2.hasNext(); ++position) {
            AbstractMonster mo = (AbstractMonster)var2.next();
            if (!(this.m.drawX > mo.drawX)) {
                break;
            }
        }
        return position;
    }

    public void update() {

        int bNum = 0;

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDying && !monster.isDead) {
                if (monster.id == Barnacle.ID) {
                    bNum++;
                }
            }
        }

        if (bNum >=8 && this.duration == this.startDuration) {
            this.isDone = true;
            return;
        }

        if (this.duration == this.startDuration) {
            // Get smart position BEFORE setting animX
            int smartPos = this.getSmartPosition();

            this.m.init();
            this.m.applyPowers();

            // Force the monster to roll its move for this turn so it shows intent immediately
            this.m.rollMove();
            this.m.createIntent();

            AbstractDungeon.getCurrRoom().monsters.addMonster(smartPos, this.m);
        }

        this.tickDuration();

        if (this.isDone) {
            this.m.showHealthBar();
            this.m.usePreBattleAction();
        }
    }
}
