package marinermod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.actions.SummonBarnacleAction;
import marinermod.character.MarinerCharacter;

public class BottleOfRum extends BaseRelic implements SubmergeAction.SubmergeInterface {

    public static final String ID = MarinerMod.makeID(BottleOfRum.class.getSimpleName());

    public BottleOfRum() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK);
        pool = MarinerCharacter.Meta.CARD_COLOR;
        this.tips.add(new PowerTip(MarinerMod.keywords.get("Submerge").PROPER_NAME, MarinerMod.keywords.get("Submerge").DESCRIPTION));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onSubmerge(AbstractPlayer p) {
        if (!grayscale) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new SubmergeAction(3));
            grayscale = true;
        }
    }

    public void onVictory() {
        grayscale = false;
    }
}
