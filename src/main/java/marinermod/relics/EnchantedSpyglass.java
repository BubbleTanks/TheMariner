package marinermod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import marinermod.MarinerMod;
import marinermod.actions.SummonBarnacleAction;
import marinermod.character.MarinerCharacter;

public class EnchantedSpyglass extends BaseRelic {

    public static final String ID = MarinerMod.makeID(EnchantedSpyglass.class.getSimpleName());

    public EnchantedSpyglass() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL);
        pool = MarinerCharacter.Meta.CARD_COLOR;
        this.tips.add(new PowerTip(MarinerMod.keywords.get("Barnacle").PROPER_NAME, MarinerMod.keywords.get("Barnacle").DESCRIPTION));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(EncrustedSpyglass.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(EncrustedSpyglass.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(EncrustedSpyglass.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
}
