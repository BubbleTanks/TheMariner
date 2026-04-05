package marinermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import marinermod.MarinerMod;
import marinermod.cards.powercards.Plunge;
import marinermod.cards.powercards.Surface;

import java.util.ArrayList;

public class DiveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private float startingDuration;

    public DiveAction(int numCards) {
        this.amount = numCards;

        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startingDuration) {

                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                if (this.amount != -1) {
                    for(int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); ++i) {
                        tmpGroup.addToTop((AbstractCard)AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
                    }
                } else {
                    for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
                        tmpGroup.addToBottom(c);
                    }
                }

                AbstractDungeon.gridSelectScreen.open(tmpGroup, 0, true, TEXT[0]);
            }

            this.tickDuration();

            if (this.isDone) {

                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                ArrayList<AbstractCard> stanceChoices = new ArrayList();
                stanceChoices.add(new Surface(Math.min(this.amount, AbstractDungeon.player.drawPile.size())));
                stanceChoices.add(new Plunge(Math.min(this.amount, AbstractDungeon.player.drawPile.size())));

                this.addToTop(new ChooseOneAction(stanceChoices));
            }
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(MarinerMod.makeID("DiveAction"));
        TEXT = uiStrings.TEXT;
    }
}
