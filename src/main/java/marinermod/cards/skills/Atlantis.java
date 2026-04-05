package marinermod.cards.skills;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.patches.AbyssalPatch;
import marinermod.patches.SunkenPatch;
import marinermod.util.CardStats;

public class Atlantis extends BaseCard implements SunkenPatch.SunkenClass.SunkenCardInterface {
    public static final String ID = makeID(Atlantis.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public Atlantis() {
        super(ID, info);
        returnToHand = true;
        setCustomVar("block", VariableType.BLOCK, 6);
        setMagic(2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                setCustomVar("block", VariableType.BLOCK, customVar("block") + magicNumber);
                this.isDone = true;
            }
        });
    }

    @Override
    public void onSunken(AbstractPlayer p) {
        addToBot(new GainBlockAction(p, customVar("block")));
    }
}
