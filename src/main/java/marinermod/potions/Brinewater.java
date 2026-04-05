package marinermod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import marinermod.actions.SummonBarnacleAction;
import marinermod.character.MarinerCharacter;

import static marinermod.MarinerMod.makeID;

public class Brinewater extends BasePotion {
    public static final String ID = makeID(Brinewater.class.getSimpleName());
    public Brinewater() {
        super(ID, 1, PotionRarity.COMMON, PotionSize.SPHERE, new Color(0.5F, 0.9F, 0.8F, 1.0F), new Color(0.3F, 0.7F, 0.5F, 1.0F), null);
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
            addToBot(new SummonBarnacleAction());
            if (AbstractDungeon.player.hasRelic(SacredBark.ID)) {
                addToBot(new SummonBarnacleAction());
            }
        }
    }
}
