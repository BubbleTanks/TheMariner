//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package marinermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.Anchor;
import marinermod.MarinerMod;
import marinermod.cards.attacks.AnchorShot;

import java.util.ArrayList;

public class DredgeAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;

    public DredgeAction(int numberOfCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;

       /* for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID == AnchorShot.ID) {
                AbstractDungeon.actionManager.addToTop(new DiscardToHandAction(c));
            }
        } */

    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startDuration) {
                if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                    if (this.player.discardPile.size() <= this.numberOfCards) {
                        this.numberOfCards = this.player.discardPile.size();
                    }

                    CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                    for (int i = 0; i < this.numberOfCards; i++) {
                        group.addToTop(this.player.discardPile.group.get(this.player.discardPile.group.size() - i - 1));
                    }

                    AbstractDungeon.gridSelectScreen.open(group, 1, TEXT[0], false);
                    this.tickDuration();
                } else {
                    this.isDone = true;
                }
            } else {
                if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                    for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);

                            this.player.discardPile.removeCard(c);
                        }

                        c.lighten(false);
                        c.unhover();
                        c.applyPowers();
                    }

                    for (AbstractCard c : this.player.discardPile.group) {
                        c.unhover();
                        c.target_x = (float) CardGroup.DISCARD_PILE_X;
                        c.target_y = 0.0F;
                    }

                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                    AbstractDungeon.player.hand.refreshHandLayout();
                }

                this.tickDuration();
                if (this.isDone) {
                    for (AbstractCard c : this.player.hand.group) {
                        c.applyPowers();
                    }
                }

            }
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString(MarinerMod.makeID("DredgeAction")).TEXT;
    }
}
