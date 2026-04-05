package marinermod.cards.attacks;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import marinermod.actions.EasyXCostAction;
import marinermod.actions.LethalAOEAction;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

public class Typhoon extends BaseCard {
    public static final String ID = makeID(Typhoon.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            -1
    );

    public Typhoon() {
        super(ID, info);
        setDamage(6,3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (effect,params) -> {
            for (int i = 0; i < effect; i++){
                this.addToBot(new SFXAction("ATTACK_WHIRLWIND"));
                this.addToBot(new VFXAction(new WhirlwindEffect(new Color(0.2F, 0.7F, 0.9F, 1.0F), false), 0.0F));
                this.addToBot(new SFXAction("ATTACK_HEAVY"));
                this.addToBot(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
                addToBot(new LethalAOEAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), (hasKilled) -> {
                    if (hasKilled) addToTop(new SubmergeAction(2));
            }));
            }
            return true;
        }));
    }
}

