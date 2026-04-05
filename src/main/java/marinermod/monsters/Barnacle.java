package marinermod.monsters;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import marinermod.powers.EnrichmentPower;
import marinermod.relics.EnchantedSpyglass;

import java.util.ArrayList;
import java.util.Collections;

import static marinermod.MarinerMod.imagePath;
import static marinermod.MarinerMod.makeID;

public class Barnacle extends BaseMonster {

    public static final String ID = makeID(Barnacle.class.getSimpleName());
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    private static final Byte ENRICHMENT = 0;
    private float targetX;
    private float targetY;

    public Barnacle (float x, float y) {
        super(NAME, ID, 5, 0f, 0f, 100f, 100f, null, 0f, 0f, true);
        addMove(ENRICHMENT, Intent.BUFF);
        this.loadAnimation("images/monsters/theBottom/louseRed/skeleton.atlas", "images/monsters/theBottom/louseRed/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = true;
        if (barnacleArrayList.size() < 8) {
            barnacleArrayList.add(this);
        }
        this.tint.color.a = 0.0F;
    }

    public void usePreBattleAction() {

        if (AbstractDungeon.player.hasRelic(EnchantedSpyglass.ID)) {
            addToTop(new ApplyPowerAction(this, this, new EnrichmentPower(this, 2)));
        } else addToTop(new ApplyPowerAction(this, this, new EnrichmentPower(this, 1)));

        addToTop(new ApplyPowerAction(this, this, new MinionPower(this)));
    }

    public static final ArrayList<Barnacle> barnacleArrayList = new ArrayList<>();

    public void render(SpriteBatch sb) {

        this.drawX = Interpolation.fade.apply(drawX, targetX, 15F * Gdx.graphics.getDeltaTime());
        this.drawY = Interpolation.fade.apply(drawY, targetY, 15F * Gdx.graphics.getDeltaTime());

        super.render(sb);

        if (this.tint.color.a < 1.0F && !isDying && !halfDead) {
            this.tint.color.a += 0.05F;
        }
    }

    public void setSlot() {
        float dist = 150.0F * Settings.scale + 8.0F * 10.0F * Settings.scale;
        float angle = 100.0F;
        float offsetAngle = angle / 2.0F;
        angle *= ((float)barnacleArrayList.indexOf(this)) / (8.0F - 1.0F);
        angle += 90.0F - offsetAngle;

        this.targetX = dist * MathUtils.cosDeg(angle) * 2.2F + AbstractDungeon.player.drawX;
        this.targetY = dist * MathUtils.sinDeg(angle) + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;

        this.hb.move(this.drawX, this.drawY);
    }

    @Override
    public void takeTurn() {
        addToBot(new ApplyPowerAction(this, this, new EnrichmentPower(this, 1)));
    }

    @Override
    protected void getMove(int i) {
        setMoveShortcut(ENRICHMENT, MOVES[ENRICHMENT]);
    }

    @Override
    public void die(boolean triggerRelics) {
        super.die(triggerRelics);
        barnacleArrayList.remove(this);

        for (Barnacle b : barnacleArrayList) {
            b.setSlot();
        }

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (this.hasPower(EnrichmentPower.ID)) {
                addToBot(new DrawCardAction(this, this.getPower(EnrichmentPower.ID).amount));
            } else addToBot(new DrawCardAction(this, 1));
        }
    }
}
