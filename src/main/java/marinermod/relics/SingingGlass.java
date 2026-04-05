package marinermod.relics;

import basemod.helpers.CardPowerTip;
import borealiscards.cards.curses.ElectromagneticInstability;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import marinermod.MarinerMod;
import marinermod.cards.skills.GlassBead;
import marinermod.character.MarinerCharacter;
import marinermod.patches.OnMonsterDeathPatch;

public class SingingGlass extends BaseRelic {

    public static final String ID = MarinerMod.makeID(SingingGlass.class.getSimpleName());

    public SingingGlass() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL);
        pool = MarinerCharacter.Meta.CARD_COLOR;
        this.tips.add(new PowerTip(MarinerMod.keywords.get("Submerge").PROPER_NAME, MarinerMod.keywords.get("Submerge").DESCRIPTION));
        this.tips.add(new CardPowerTip(new GlassBead()));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new GlassBead(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new GlassBead(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        UnlockTracker.markCardAsSeen("marinermod:GlassBead");
    }

}
