package marinermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

public class AhoyPatch {

    @SpirePatch2(clz = UseCardAction.class, method = "update")
    public static class AhoyInstrumentPatch {
        @SpireInstrumentPatch
        public static ExprEditor ahoyBullshit() {

            return new ExprEditor() {

                public void edit(FieldAccess f) throws CannotCompileException {

                    if (f.getClassName().equals(UseCardAction.class.getName()) && f.getFieldName().equals("reboundCard")) {

                        f.replace("$_ = $proceed($$) || ((Boolean)" + Fields.marinerField.class.getName() +".ahoyCard.get(this.targetCard)).booleanValue();");

                    }

                }

            };

        }


    }

}
