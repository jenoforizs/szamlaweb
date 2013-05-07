package com.jeno.szamlaweb.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Jeno
 */
public class Forgalom implements Serializable {
    @Id
    private String id;
    private Fajta fajta;
    private Tipus tipus;
    private Double osszeg;
    private Date idopont;
    private String megjegyzes;
    private Date rogzitesIdeje;
    private Date modositasIdeje;

    public static Forgalom ujBejovoForgalom() {
        Forgalom f = new Forgalom();
        
        f.setFajta(Fajta.BEJOVO);
        f.setIdopont(new Date());
        
        return f;
    } // ujBejovoForgalom
    
    public static Forgalom ujKimenoForgalom() {
        Forgalom f = new Forgalom();
        
        f.setFajta(Fajta.KIMENO);
        f.setIdopont(new Date());
        
        return f;
    } // ujKimenoForgalom
    
    //<editor-fold defaultstate="collapsed" desc="getter, setter">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fajta getFajta() {
        return fajta;
    }

    public void setFajta(Fajta fajta) {
        this.fajta = fajta;
    }

    public Double getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(Double osszeg) {
        this.osszeg = osszeg;
    }

    public Date getIdopont() {
        return idopont;
    }

    public void setIdopont(Date idopont) {
        this.idopont = idopont;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
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

    public Tipus getTipus() {
        return tipus;
    }

    public void setTipus(Tipus tipus) {
        this.tipus = tipus;
    }
    //</editor-fold>

    public enum Fajta {
        BEJOVO("Bejövő"), KIMENO("Kimenő");
        private String label;

        private Fajta(String label) {
            this.label = label;
        } // IgenNem

        public String getLabel() {
            return label;
        } // getLabel
        
    }

    public enum Tipus {
        KAMAT("Folyószámla kamat"), 
        TAGI_KOLCSON("Tagi kölcsön"),
        SZJA("SZJA"), 
        BANKI_KOLTSEG("Banki költség"), 
        NAV("NAV adók"),
        OSZTALEK("Osztalék"),
        EGYEB("Egyéb");
        
        private String label;

        private Tipus(String label) {
            this.label = label;
        } // IgenNem

        public String getLabel() {
            return label;
        } // getLabel
        
    }

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return "Forgalom{" + "id=" + id + ", fajta=" + fajta + ", tipus=" + tipus + ", osszeg=" + osszeg + ", idopont=" + idopont + ", megjegyzes=" + megjegyzes + ", rogzitesIdeje=" + rogzitesIdeje + ", modositasIdeje=" + modositasIdeje + '}';
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode és equals">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.fajta != null ? this.fajta.hashCode() : 0);
        hash = 37 * hash + (this.tipus != null ? this.tipus.hashCode() : 0);
        hash = 37 * hash + (this.osszeg != null ? this.osszeg.hashCode() : 0);
        hash = 37 * hash + (this.idopont != null ? this.idopont.hashCode() : 0);
        hash = 37 * hash + (this.megjegyzes != null ? this.megjegyzes.hashCode() : 0);
        hash = 37 * hash + (this.rogzitesIdeje != null ? this.rogzitesIdeje.hashCode() : 0);
        hash = 37 * hash + (this.modositasIdeje != null ? this.modositasIdeje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Forgalom other = (Forgalom) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if (this.fajta != other.fajta) {
            return false;
        }
        if (this.tipus != other.tipus) {
            return false;
        }
        if (this.osszeg != other.osszeg && (this.osszeg == null || !this.osszeg.equals(other.osszeg))) {
            return false;
        }
        if (this.idopont != other.idopont && (this.idopont == null || !this.idopont.equals(other.idopont))) {
            return false;
        }
        if ((this.megjegyzes == null) ? (other.megjegyzes != null) : !this.megjegyzes.equals(other.megjegyzes)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

}
