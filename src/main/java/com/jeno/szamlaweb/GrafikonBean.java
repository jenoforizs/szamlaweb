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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.component.chart.bar.BarChart;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jeno
 */
@Component
@ManagedBean(name = "GrafikonBean")
public class GrafikonBean {
    @Resource
    ForgalomService forgalomService;
    @Resource
    SzamlaService szamlaService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    
    CartesianChartModel adatModelLine;
    CartesianChartModel adatModelBar;
    double min = 0;
    double max = 0;

    public String adatOsszeallitasBeKi() {
        min = 0;
        
        adatModelLine = new CartesianChartModel();
        adatModelBar = new CartesianChartModel();
        
        ChartSeries bevetelekChart = new ChartSeries("Bevételek");
        ChartSeries kiadasokChart = new ChartSeries("Kiadások");

        List all = new ArrayList();

        all.addAll(szamlaService.getOsszesFizetettSzamla(Szamla.Fajta.BEVETELI));
        all.addAll(forgalomService.getOsszesForgalom(Forgalom.Fajta.BEJOVO));
        all.addAll(szamlaService.getOsszesSzamla(Szamla.Fajta.KIADASI));
        all.addAll(forgalomService.getOsszesForgalom(Forgalom.Fajta.KIMENO));

        Collections.sort(all, new DatumComparator());

        double napiBevetel = 0;
        double napiKiadas = 0;

        String nap = null;
        String aktualisNap = null;

        for (Object object : all) {
            if (object instanceof Szamla) {
                Szamla sz = (Szamla) object;
                if (sz.getFajta() == Szamla.Fajta.BEVETELI) {
                    napiBevetel += sz.getBruttoErtek();
                } else if (sz.getFajta() == Szamla.Fajta.KIADASI) {
                    napiKiadas += sz.getBruttoErtek();
                } // if - else if
                nap = sdf.format(sz.getFizetesIdopontja());
            } // if

            if (object instanceof Forgalom) {
                Forgalom f = (Forgalom) object;
                if (f.getFajta() == Forgalom.Fajta.BEJOVO) {
                    napiBevetel += f.getOsszeg();
                } else if (f.getFajta() == Forgalom.Fajta.KIMENO) {
                    napiKiadas += f.getOsszeg();
                } // if - else if
                nap = sdf.format(f.getIdopont());
            } // if

            if (napiBevetel > max) {
                max = napiBevetel;
            } else if (napiKiadas * -1 < min) {
                min = napiKiadas * -1;
            } // if - else if

            if (!nap.equals(aktualisNap)) {
                if (napiBevetel != 0) {
                    bevetelekChart.set(aktualisNap, napiBevetel);
                } // if

                if (napiKiadas != 0) {
                    kiadasokChart.set(aktualisNap, napiKiadas);
                } // if

                aktualisNap = nap;
                napiBevetel = 0;
                napiKiadas = 0;
            } // if
        } // for

        adatModelBar.addSeries(bevetelekChart);
        adatModelBar.addSeries(kiadasokChart);
        
        return "/pages/grafikonBeKi.jsf";
    }

    public String adatOsszeallitasCashflow() {
        adatModelLine = new CartesianChartModel();
        
        LineChartSeries cashflow = new LineChartSeries("Cashflow");

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

            if (sum > max) {
                max = sum;
            } else if (sum < min) {
                min = sum;
            } // if - else if

            cashflow.set(nap, sum);
        } // for

        adatModelLine.addSeries(cashflow);

        return "/pages/grafikonCashflow.jsf";
    }

    public void kivalasztas(ItemSelectEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",  
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    } // kivalasztas
    
    public CartesianChartModel getAdatModelBar() {
        return adatModelBar;
    }

    public void setAdatModelBar(CartesianChartModel adatModelBar) {
        this.adatModelBar = adatModelBar;
    }

    public CartesianChartModel getAdatModelLine() {
        return adatModelLine;
    }

    public void setAdatModelLine(CartesianChartModel adatModelLine) {
        this.adatModelLine = adatModelLine;
    }

    public double getMin() {
        return min * 1.1;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max * 1.1;
    }

    public void setMax(double max) {
        this.max = max;
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
