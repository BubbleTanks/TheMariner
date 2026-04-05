package marinermod.util;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BarnaclePositionSaver {

    public AbstractMonster m;
    public final float x;
    public final float y;

    public BarnaclePositionSaver(AbstractMonster m, float x, float y) {
        this.m = m;
        this.x = x;
        this.y = y;
    }

    public void monsterChanger(AbstractMonster monster) {

        this.m = monster;

    }

}
