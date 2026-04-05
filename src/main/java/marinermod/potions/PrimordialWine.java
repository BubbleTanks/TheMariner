package marinermod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import marinermod.character.MarinerCharacter;

import static marinermod.MarinerMod.makeID;

public class PrimordialWine extends BasePotion {
    public static final String ID = makeID(PrimordialWine.class.getSimpleName());
    public PrimordialWine() {
        super(ID, 10, PotionRarity.RARE, PotionSize.FAIRY, Color.PURPLE.cpy(), new Color(0.2F, 0.0F, 0.2F, 1.0F), null);
        playerClass = MarinerCharacter.Meta.MARINER;
        labOutlineColor = Color.TEAL.cpy();
    }

    @Override
    public String getDescription() {
        return String.format(DESCRIPTIONS[0], potency);
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ShuffleAllAction());
            this.addToBot(new ShuffleAction(AbstractDungeon.player.drawPile, false));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, potency));
        }
    }
}
