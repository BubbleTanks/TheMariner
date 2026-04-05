package marinermod.actions;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.patches.Fields;

import java.util.function.Consumer;

public class LethalAOEAction extends AbstractGameAction {
    private DamageInfo info;
    private Consumer<Boolean> callback;

    public LethalAOEAction(DamageInfo info, Consumer<Boolean> callback) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.callback = callback;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == 0.1F) {
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {

                    target = m;
                    this.target.damage(this.info);
                    callback.accept((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !Fields.BehaveYourself.stopFuckingPlaying.get(target));

                    if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !Fields.BehaveYourself.stopFuckingPlaying.get(target)) {
                        Fields.BehaveYourself.stopFuckingPlaying.set(target, true);
                    }

                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
            }

            this.tickDuration();
        }
    }
}
