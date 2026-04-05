package marinermod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import marinermod.MarinerMod;
import marinermod.actions.SubmergeAction;
import marinermod.character.MarinerCharacter;

public class SinkingShip extends BaseRelic {

    public static final String ID = MarinerMod.makeID(SinkingShip.class.getSimpleName());

    public SinkingShip() {
        super(ID, RelicTier.BOSS, LandingSound.SOLID);
        pool = MarinerCharacter.Meta.CARD_COLOR;
        this.tips.add(new PowerTip(MarinerMod.keywords.get("Submerge").PROPER_NAME, MarinerMod.keywords.get("Submerge").DESCRIPTION));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        counter = 3;
    }

    public void atTurnStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new SubmergeAction(counter));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                counter++;
                this.isDone = true;
            }
        });
    }

    public void onVictory() {
        counter = -1;
    }



}
