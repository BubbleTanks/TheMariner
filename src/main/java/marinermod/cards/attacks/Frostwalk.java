package marinermod.cards.attacks;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import marinermod.actions.EasyXCostAction;
import marinermod.actions.LethalAOEAction;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.Fields;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

import java.util.ArrayList;

public class Frostwalk extends BaseCard {
    public static final String ID = makeID(Frostwalk.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            -1
    );

    public Frostwalk() {
        super(ID, info);
        setDamage(14);
        setMagic(0,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ORB_LIGHTNING_EVOKE"));
        addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        ArrayList<AbstractCard> random = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof SunkenPatch.SunkenClass.SunkenCardInterface) {
                random.add(c);
            }
        }

        addToBot(new EasyXCostAction(this, (effect,params) -> {

            for (int i = 0; i < effect + params[0]; i++){

                if (!random.isEmpty()) {
                    AbstractCard card = random.get(AbstractDungeon.cardRng.random(random.size() - 1));
                    random.remove(card);
                    addToBot(new DiscardToHandAction(card));
                    card.retain = true;
                }
            }
            return true;

        }, magicNumber));

    }

}
