package marinermod.cards.skills;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import marinermod.MarinerMod;
import marinermod.cardmods.BallastMod;
import marinermod.cards.BaseCard;
import marinermod.character.MarinerCharacter;
import marinermod.util.CardStats;

public class GreatBarrierReef extends BaseCard {
    public static final String ID = makeID(GreatBarrierReef.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MarinerCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public GreatBarrierReef() {
        super(ID, info);
        setBlock(20, 5);
        tags.add(MarinerMod.REEF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GainBlockAction(p, block));

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.hasTag(MarinerMod.REEF)) {
                        CardModifierManager.addModifier(c, new BallastMod());
                    }
                }
                this.isDone = true;
            }
        });

    }




}
