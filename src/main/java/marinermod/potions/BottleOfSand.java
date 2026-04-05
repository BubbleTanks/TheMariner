package marinermod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import marinermod.actions.SubmergeAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.character.MarinerCharacter;

import static marinermod.MarinerMod.makeID;

public class BottleOfSand extends BasePotion {
    public static final String ID = makeID(BottleOfSand.class.getSimpleName());
    public BottleOfSand() {
        super(ID, 8, PotionRarity.UNCOMMON, PotionSize.BOTTLE, Color.GOLDENROD.cpy(), null, null);
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
            addToBot(new SubmergeAction(potency));
        }
    }
}
