package com.jeno.szamlaweb;

import com.jeno.szamlaweb.model.Forgalom;
import com.jeno.szamlaweb.model.Szamla;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jeno
 */
@Component
@Transactional
public class Test {
    @Resource
    private MongoTemplate mt;

    private String result;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    
    public void test() throws Exception {
        Szamla sz = Szamla.ujBeveteliSzamla();
        sz.setNev("Proba1");
        sz.setTeljesitesIdoponja(sdf.parse("2013.02.28"));
        sz.setKelte(sdf.parse("2013.03.03"));
        sz.setFizetesiHatarido(sdf.parse("2013.03.30"));
        sz.setNettoErtek(880000d);
        sz.setBruttoErtek(1117600d);
        
        mt.save(sz);
        
        result = "mentve.";
    } // test

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static void main(String[] args) {
        try {
            Mongo mongo = new MongoClient("localhost");
            Test test = new Test();
            test.mt = new MongoTemplate(mongo, "szamla");

            test.mt.dropCollection(Szamla.class);
            test.mt.dropCollection(Forgalom.class);
            
            test.insertUjBeveteliSzamla("1", 200000, test.sdf.parse("2013.01.01"));
            test.insertUjBeveteliSzamla("2",500000, test.sdf.parse("2013.02.01"));
            test.insertUjBeveteliSzamla("3",100000, test.sdf.parse("2013.03.01"));
            test.insertUjNemFizetettBeveteliSzamla("4",100000, test.sdf.parse("2013.04.01"));
            test.insertUjNemFizetettBeveteliSzamla("5",100000, test.sdf.parse("2013.05.01"));
            
            test.insertUjKiadasiSzamla("1", 20000, test.sdf.parse("2013.01.10"));
            test.insertUjKiadasiSzamla("2",50000, test.sdf.parse("2013.02.10"));
            test.insertUjKiadasiSzamla("3",10000, test.sdf.parse("2013.03.10"));

            test.insertUjForgalom(Forgalom.Fajta.BEJOVO, Forgalom.Tipus.TAGI_KOLCSON, 100000, test.sdf.parse("2013.01.20"));
            test.insertUjForgalom(Forgalom.Fajta.BEJOVO, Forgalom.Tipus.KAMAT, 1000, test.sdf.parse("2013.02.20"));
            
            test.insertUjForgalom(Forgalom.Fajta.KIMENO, Forgalom.Tipus.SZJA, 40000, test.sdf.parse("2013.01.10"));
            test.insertUjForgalom(Forgalom.Fajta.KIMENO, Forgalom.Tipus.NAV, 60000, test.sdf.parse("2013.01.11"));
            test.insertUjForgalom(Forgalom.Fajta.KIMENO, Forgalom.Tipus.OSZTALEK, 500000, test.sdf.parse("2013.01.30"));
            
            mongo.close();
        } catch( Exception e ) {
            e.printStackTrace();
        } // try - catch
    } // main
    
    private void insertUjForgalom(Forgalom.Fajta fajta, Forgalom.Tipus tipus, double osszeg, Date d) {
        Forgalom f = new Forgalom();
        f.setFajta(fajta);
        f.setTipus(tipus);
        f.setOsszeg(osszeg);
        f.setIdopont(d);
        f.setRogzitesIdeje(new Date());
        mt.save(f);
    } // insertUjForgalom
    
    private void insertUjBeveteliSzamla(String szam, double netto, Date d) {
        Szamla sz = Szamla.ujBeveteliSzamla();
        sz.setSzam(szam);
        sz.setNev("Proba"+szam);
        sz.setTeljesitesIdoponja(d);
        sz.setKelte(d);
        sz.setFizetesiHatarido(d);
        sz.setFizetesIdopontja(d);
        sz.setNettoErtek(netto);
        sz.setBruttoErtek(netto*1.27);
        sz.setAfa(27d);
        sz.setRogzitesIdeje(new Date());
        mt.save(sz);
    } // insertUjBeveteliSzamla
    
    private void insertUjNemFizetettBeveteliSzamla(String szam, double netto, Date d) {
        Szamla sz = Szamla.ujBeveteliSzamla();
        sz.setSzam(szam);
        sz.setNev("Proba"+szam);
        sz.setTeljesitesIdoponja(d);
        sz.setKelte(d);
        sz.setFizetesiHatarido(d);
        sz.setNettoErtek(netto);
        sz.setBruttoErtek(netto*1.27);
        sz.setAfa(27d);
        sz.setRogzitesIdeje(new Date());
        mt.save(sz);
    } // insertUjNemFizetettBeveteliSzamla

    private void insertUjKiadasiSzamla(String szam, double netto, Date d) {
        Szamla sz = Szamla.ujKiadasiSzamla();
        sz.setSzam(szam);
        sz.setNev("Proba"+szam);
        sz.setTeljesitesIdoponja(d);
        sz.setKelte(d);
        sz.setFizetesiHatarido(d);
        sz.setFizetesIdopontja(d);
        sz.setNettoErtek(netto);
        sz.setBruttoErtek(netto*1.27);
        sz.setAfa(27d);
        sz.setRogzitesIdeje(new Date());
        mt.save(sz);
    } // insertUjKiadasiSzamla
}
