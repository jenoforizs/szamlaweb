package com.jeno.szamlaweb.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Jeno
 */
@Document(collection = "szamlak")
public class Szamla {

    @Id
    private String id;
    private String szam;
    private Fajta fajta;
    private String nev;
    private String megjegyzes;
    private FizetesModja fizetesModja;
    private Double nettoErtek;
    private Double bruttoErtek;
    private Double afa;
    private IgenNem afaSzamolando;
    private Date teljesitesIdoponja;
    private Date kelte;
    private Date fizetesiHatarido;
    private Date rogzitesIdeje;
    private Date modositasIdeje;
    
    public static Szamla ujBeveteliSzamla() {
        Szamla sz = new Szamla();
        
        sz.fajta = Fajta.BEVETELI;
        sz.fizetesModja = FizetesModja.ATUTALAS;
        sz.afa = 27d;
        sz.setAfaSzamolando(IgenNem.IGEN);
        sz.rogzitesIdeje = new Date();
        
        return sz;
    } // ujBeveteliSzamla

    public static Szamla ujKiadasiSzamla() {
        Szamla sz = new Szamla();
        
        sz.fajta = Fajta.KIADASI;
        sz.fizetesModja = FizetesModja.ATUTALAS;
        sz.afa = 27d;
        sz.setAfaSzamolando(IgenNem.IGEN);
        sz.rogzitesIdeje = new Date();
        
        return sz;
    } // ujBeveteliSzamla

    public FizetesModja getFizetesModja() {
        return fizetesModja;
    }

    public void setFizetesModja(FizetesModja fizetesModja) {
        this.fizetesModja = fizetesModja;
    }

    public Date getRogzitesIdeje() {
        return rogzitesIdeje;
    }

    public void setRogzitesIdeje(Date rogzitesIdeje) {
        this.rogzitesIdeje = rogzitesIdeje;
    }

    public Date getModositasIdeje() {
        return modositasIdeje;
    }

    public void setModositasIdeje(Date modositasIdeje) {
        this.modositasIdeje = modositasIdeje;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSzam() {
        return szam;
    }

    public void setSzam(String szam) {
        this.szam = szam;
    }

    public Fajta getFajta() {
        return fajta;
    }

    public void setFajta(Fajta fajta) {
        this.fajta = fajta;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }

    public Double getNettoErtek() {
        return nettoErtek;
    }

    public void setNettoErtek(Double nettoErtek) {
        this.nettoErtek = nettoErtek;
    }

    public Double getBruttoErtek() {
        return bruttoErtek;
    }

    public void setBruttoErtek(Double bruttoErtek) {
        this.bruttoErtek = bruttoErtek;
    }

    public Double getAfa() {
        return afa;
    }

    public void setAfa(Double afa) {
        this.afa = afa;
    }

    public IgenNem getAfaSzamolando() {
        return afaSzamolando;
    }

    public void setAfaSzamolando(IgenNem afaSzamolando) {
        this.afaSzamolando = afaSzamolando;
    }

    public Date getTeljesitesIdoponja() {
        return teljesitesIdoponja;
    }

    public void setTeljesitesIdoponja(Date teljesitesIdoponja) {
        this.teljesitesIdoponja = teljesitesIdoponja;
    }

    public Date getKelte() {
        return kelte;
    }

    public void setKelte(Date kelte) {
        this.kelte = kelte;
    }

    public Date getFizetesiHatarido() {
        return fizetesiHatarido;
    }

    public void setFizetesiHatarido(Date fizetesiHatarido) {
        this.fizetesiHatarido = fizetesiHatarido;
    }

    @Override
    public String toString() {
        return "Szamla{" + "id=" + id + ", szam=" + szam + ", fajta=" + fajta + ", nev=" + nev + ", megjegyzes=" + megjegyzes + ", fizetesModja=" + fizetesModja + ", nettoErtek=" + nettoErtek + ", bruttoErtek=" + bruttoErtek + ", afa=" + afa + ", afaSzamolando=" + afaSzamolando + ", teljesitesIdoponja=" + teljesitesIdoponja + ", kelte=" + kelte + ", fizetesiHatarido=" + fizetesiHatarido + ", rogzitesIdeje=" + rogzitesIdeje + ", modositasIdeje=" + modositasIdeje + '}';
    }
    
    public enum Fajta {

        BEVETELI, KIADASI
    }

    enum FizetesModja {

        ATUTALAS, KESZPENZ
    }
}
