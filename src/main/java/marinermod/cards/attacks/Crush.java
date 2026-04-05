package marinermod.cards.attacks;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import marinermod.actions.SummonBarnacleAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.util.CardStats;

public class Crush extends BaseCard {
    public static final String ID = makeID(Crush.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    public Crush() {
        super(ID, info);
        setDamage(20,6);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
        addToBot(new LethalAOEAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), (hasKilled) -> {
            if (hasKilled) addToBot(new SummonBarnacleAction());
        }));
    }
}

