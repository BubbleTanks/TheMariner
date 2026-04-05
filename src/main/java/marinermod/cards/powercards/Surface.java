package marinermod.cards.powercards;

import basemod.abstracts.events.phases.CombatPhase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import marinermod.actions.SubmergeAction;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.powers.DrowningPower;
import marinermod.util.CardStats;

public class Surface extends BaseCard {
    public static final String ID = makeID(Surface.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );

    public Surface() {
        this(5);
    }

    private static boolean combatCheck() {
        if(AbstractDungeon.getCurrMapNode() != null) {
            AbstractRoom r = AbstractDungeon.getCurrRoom();
            if(r != null) {
                return r.phase == AbstractRoom.RoomPhase.COMBAT;
            }
        }
        return false;
    }

    public Surface(int c) {
        super(ID, info);
        if (combatCheck()) {setMagic(c);} else {setMagic(5,1);}
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot(new DrawCardAction(magicNumber));
    }
}
