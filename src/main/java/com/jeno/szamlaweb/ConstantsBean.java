package com.jeno.szamlaweb;

import com.jeno.szamlaweb.model.Forgalom;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jeno
 */
@ManagedBean(name = "constantsBean")
public class ConstantsBean {
    public static Date HONAPRA_VISSZAMENOLEG;
    static Forgalom.Tipus[] BEJOVO_FORGALOM_TIPUS;
    static Forgalom.Tipus[] KIMENO_FORGALOM_TIPUS;
    
    static {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -36);
        HONAPRA_VISSZAMENOLEG = cal.getTime();
        
        BEJOVO_FORGALOM_TIPUS = new Forgalom.Tipus[]{ Forgalom.Tipus.TAGI_KOLCSON, Forgalom.Tipus.KAMAT, Forgalom.Tipus.EGYEB};
        KIMENO_FORGALOM_TIPUS = new Forgalom.Tipus[]{ Forgalom.Tipus.BANKI_KOLTSEG, Forgalom.Tipus.NAV, Forgalom.Tipus.OSZTALEK, Forgalom.Tipus.SZJA, Forgalom.Tipus.EGYEB};
    }

    public Forgalom.Tipus[] getBEJOVO_FORGALOM_TIPUS() {
        return BEJOVO_FORGALOM_TIPUS;
    }

    public void setBEJOVO_FORGALOM_TIPUS(Forgalom.Tipus[] BEJOVO_FORGALOM_TIPUS) {
        ConstantsBean.BEJOVO_FORGALOM_TIPUS = BEJOVO_FORGALOM_TIPUS;
    }

    public Forgalom.Tipus[] getKIMENO_FORGALOM_TIPUS() {
        return KIMENO_FORGALOM_TIPUS;
    }

    public static void setKIMENO_FORGALOM_TIPUS(Forgalom.Tipus[] KIMENO_FORGALOM_TIPUS) {
        ConstantsBean.KIMENO_FORGALOM_TIPUS = KIMENO_FORGALOM_TIPUS;
    }
}
