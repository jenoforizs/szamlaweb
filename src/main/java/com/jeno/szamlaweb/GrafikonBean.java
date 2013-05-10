package com.jeno.szamlaweb;

import com.jeno.szamlaweb.model.Forgalom;
import com.jeno.szamlaweb.model.Szamla;
import com.jeno.szamlaweb.services.ForgalomService;
import com.jeno.szamlaweb.services.SzamlaService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jeno
 */
@Component
@ManagedBean(name = "GrafikonBean")
public class GrafikonBean {

    private CartesianChartModel adatModel;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    @Resource
    ForgalomService forgalomService;
    @Resource
    SzamlaService szamlaService;

    public String adatOsszeallitas() {
        adatModel = new CartesianChartModel();

        LineChartSeries cashflow = new LineChartSeries();
        cashflow.setLabel("Cashflow");

        List all = new ArrayList();

        all.addAll(szamlaService.getOsszesFizetettSzamla(Szamla.Fajta.BEVETELI));
        all.addAll(forgalomService.getOsszesForgalom(Forgalom.Fajta.BEJOVO));
        all.addAll(szamlaService.getOsszesSzamla(Szamla.Fajta.KIADASI));
        all.addAll(forgalomService.getOsszesForgalom(Forgalom.Fajta.KIMENO));

        Collections.sort(all, new DatumComparator());

        double sum = 0;
        String nap = null;
        for (Object object : all) {
            if (object instanceof Szamla) {
                Szamla sz = (Szamla) object;
                if (sz.getFajta() == Szamla.Fajta.BEVETELI) {
                    sum += sz.getBruttoErtek();
                } else if (sz.getFajta() == Szamla.Fajta.KIADASI) {
                    sum -= sz.getBruttoErtek();
                } // if - else if
                nap = sdf.format(sz.getFizetesIdopontja());
            } // if

            if (object instanceof Forgalom) {
                Forgalom f = (Forgalom) object;
                if (f.getFajta() == Forgalom.Fajta.BEJOVO) {
                    sum += f.getOsszeg();
                } else if (f.getFajta() == Forgalom.Fajta.KIMENO) {
                    sum -= f.getOsszeg();
                } // if - else if
                nap = sdf.format(f.getIdopont());
            } // if

            cashflow.set(nap, sum);
        } // for

        adatModel.addSeries(cashflow);
        return "/pages/grafikon.jsf";
    }

    public CartesianChartModel getAdatModel() {
        return adatModel;
    }

    public void setAdatModel(CartesianChartModel adatModel) {
        this.adatModel = adatModel;
    }

    private class DatumComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Date d1 = null;
            Date d2 = null;

            if (o1 instanceof Szamla) {
                d1 = ((Szamla) o1).getFizetesIdopontja();
            } // if
            if (o2 instanceof Szamla) {
                d2 = ((Szamla) o2).getFizetesIdopontja();
            } // if

            if (o1 instanceof Forgalom) {
                d1 = ((Forgalom) o1).getIdopont();
            } // if
            if (o2 instanceof Forgalom) {
                d2 = ((Forgalom) o2).getIdopont();
            } // if

            if (d1.before(d2)) {
                return -1;
            } else if (d1.after(d2)) {
                return 1;
            } // if - else if

            return 0;
        } // compare
    }
}
