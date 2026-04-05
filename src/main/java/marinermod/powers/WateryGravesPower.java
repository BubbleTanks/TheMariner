package marinermod.powers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marinermod.MarinerMod;
import marinermod.patches.SunkenPatch;

import java.util.ArrayList;

public class WateryGravesPower extends BasePower {

    public static String ID = MarinerMod.makeID(WateryGravesPower.class.getSimpleName());

    public WateryGravesPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        int i = 0;
        if (amount == 1) i++;
        this.description = String.format(DESCRIPTIONS[i], this.amount);
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {

            ArrayList<AbstractCard> random = new ArrayList<>();

            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c instanceof SunkenPatch.SunkenClass.SunkenCardInterface) {
                    random.add(c);
                }
            }

            for (int i = 0; i < amount; i++) {

                if (random.isEmpty()) break;
                AbstractCard card = random.get(AbstractDungeon.cardRng.random(random.size()-1));
                random.remove(card);
                addToBot(new DiscardToHandAction(card));

            }
        }
    }

}


