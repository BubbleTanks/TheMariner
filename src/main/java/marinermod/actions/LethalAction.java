package marinermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;
import java.util.function.Consumer;

public class LethalAction extends AbstractGameAction {
    private DamageInfo info;
    private Consumer<Boolean> callback;


    public LethalAction(AbstractCreature target, DamageInfo info, AttackEffect effect, Consumer<Boolean> callback) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.callback = callback;
        this.attackEffect = effect;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == 0.1F && this.target != null) {
                this.target.damage(this.info);
                if (attackEffect != null) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));
                }

                callback.accept((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead);

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
            }

            this.tickDuration();
        }
    }
}
