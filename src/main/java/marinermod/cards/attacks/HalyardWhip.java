package marinermod.cards.attacks;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import marinermod.actions.SubmergeAction;
import marinermod.cardmods.TrawlMod;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

import java.util.ArrayList;

public class HalyardWhip extends BaseCard {
    public static final String ID = makeID(HalyardWhip.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public HalyardWhip() {
        super(ID, info);
        setDamage(9,2);
        setMagic(2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {

                int halyardAmount = magicNumber;
                if (AbstractDungeon.player.drawPile.size() < halyardAmount) halyardAmount = AbstractDungeon.player.drawPile.size();


                if (halyardAmount != 0) {
                    ArrayList<AbstractCard> list = new ArrayList<>();
                    for (int i = 0; i < halyardAmount; i++) {
                        list.add(AbstractDungeon.player.drawPile.getNCardFromTop(i));
                    }
                    for (AbstractCard c : list) {
                        CardModifierManager.addModifier(c, new TrawlMod());
                    }
                }

                this.isDone = true;
            }
        });
    }

}
