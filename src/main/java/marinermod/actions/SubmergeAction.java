package marinermod.actions;

import basemod.ReflectionHacks;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import marinermod.patches.Fields;
import marinermod.powers.VoyagerPower;

import java.util.ArrayList;

public class SubmergeAction extends AbstractGameAction {

    private static final float DISCARD_X = Settings.WIDTH * 0.96F;
    private static final float DISCARD_Y = Settings.HEIGHT * 0.06F;

    private static final float DRAW_PILE_X = Settings.WIDTH * 0.04F;
    private static final float DRAW_PILE_Y = Settings.HEIGHT * 0.06F;

    private static final float START_VELOCITY = 200.0F * Settings.scale;

    public static int cardsVoyaged;

    public SubmergeAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (AbstractDungeon.player.drawPile.size() < amount) amount = AbstractDungeon.player.drawPile.size();

            if (this.duration == Settings.ACTION_DUR_FAST) {
                for (int i = 0; i < amount; i++) {
                    AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                        @Override
                        public void update() {
                            AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
                            // card.current_x = AbstractDungeon.overlayMenu.combatDeckPanel.current_x;
                            // card.current_y = AbstractDungeon.overlayMenu.combatDeckPanel.current_y;
                            AbstractDungeon.player.drawPile.removeCard(card);
                            submerge(card, false);
                            if (Fields.marinerField.trawlCard.get(card)) {
                                addToTop(new DiscardToHandAction(card));
                                card.flash();
                            }
                            if (card instanceof SubmergeInterface) {
                                ((SubmergeInterface) card).onSubmerge(AbstractDungeon.player);
                            }
                            for (AbstractRelic relic : AbstractDungeon.player.relics) {
                                if (relic instanceof SubmergeInterface) {
                                    ((SubmergeInterface) relic).onSubmerge(AbstractDungeon.player);
                                }
                            }
                            if (AbstractDungeon.player.hasPower(VoyagerPower.ID)) {
                                if (cardsVoyaged < AbstractDungeon.player.getPower(VoyagerPower.ID).amount) {

                                    addToBot(new PlayDiscardedCardAction(card));
                                    cardsVoyaged++;

                                }
                            }

                            this.isDone = true;
                        }
                    });
                }
            }
            tickDuration();
        }
    }

    public void submerge(AbstractCard card, boolean isInvisible, Soul soul) {
        ReflectionHacks.setPrivate(soul, Soul.class, "isInvisible", isInvisible);
        soul.card = card;
        soul.group = AbstractDungeon.player.discardPile;
        soul.group.addToTop(card);
        ReflectionHacks.setPrivate(soul, Soul.class, "target", new Vector2(DISCARD_X, DISCARD_Y));
        ReflectionHacks.setPrivate(soul, Soul.class, "pos", new Vector2(DRAW_PILE_X, DRAW_PILE_Y));
        ReflectionHacks.privateMethod(Soul.class, "setSharedVariables").invoke(soul);
        ReflectionHacks.setPrivate(soul, Soul.class, "rotation", MathUtils.random(220.0F, 270.0F));
        if (Settings.FAST_MODE) {
            ReflectionHacks.setPrivate(soul, Soul.class, "currentSpeed", START_VELOCITY * MathUtils.random(8.0F, 12.0F));
        } else {
            ReflectionHacks.setPrivate(soul, Soul.class, "currentSpeed", START_VELOCITY * MathUtils.random(2.0F, 5.0F));
        }
        ReflectionHacks.setPrivate(soul, Soul.class, "rotateClockwise", false);
        ReflectionHacks.setPrivate(soul, Soul.class, "spawnStutterTimer", MathUtils.random(0.0F, 0.12F));
    }

    public void submerge(AbstractCard card, boolean isInvisible) {
        card.untip();
        card.unhover();
        card.darken(true);
        card.shrink(true);

        boolean needMoreSouls = true;

        ArrayList<Soul> souls = ReflectionHacks.getPrivate(AbstractDungeon.getCurrRoom().souls, SoulGroup.class, "souls");

        for (Soul s : souls) {
            if (s.isReadyForReuse) {
                submerge(card, isInvisible, s);
                needMoreSouls = false;

                break;
            }
        }
        if (needMoreSouls) {
            Soul s = new Soul();
            submerge(card, isInvisible, s);
            souls.add(s);
        }
    }

    public interface SubmergeInterface {

        void onSubmerge(AbstractPlayer p);

    }
}
