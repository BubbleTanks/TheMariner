package marinermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.patches.Fields;
import marinermod.util.CardStats;

public class FractalTrench extends BaseCard implements AbyssalPatch.AbyssalInterface {
    public static final String ID = makeID(FractalTrench.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public FractalTrench() {
        super(ID, info);
        setDamage(4,1);
        setMagic(2);
        setCustomVar("magic2", VariableType.MAGIC, 2);
        Fields.marinerField.trawlCard.set(this, true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < customVar("magic2"); i++) {
            addToBot(new SFXAction("ORB_LIGHTNING_EVOKE"));
            addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }

    }

    @Override
    public void onAbyssal(AbstractPlayer p) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {

                setCustomVar("magic2", customVar("magic2") + magicNumber);
                this.isDone = true;

            }
        });
    }
}