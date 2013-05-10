package com.jeno.szamlaweb;

import com.jeno.szamlaweb.model.Szamla;
import com.jeno.szamlaweb.services.SzamlaService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jeno
 */
@Component
@ManagedBean(name="szamlaBean")
@Transactional
public class SzamlaBean {
    List<Szamla> beveteliSzamlak;
    List<Szamla> kiadasiSzamlak;

    String id;
    Szamla szamla;

    @Resource
    SzamlaService szamlaService;
    
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");
    
    public String modositas() {
        System.out.println("modositas()");
        System.out.println("id:" + id);
        
        szamla = szamlaService.getSzamla(id);
        
        return "/pages/szamla.jsf";
    } // modositas
    
    public String mentes() {
        System.out.println("mentes()");
        System.out.println("id:" + id);

        if(id == null) { // új rekord
            szamla.setRogzitesIdeje(new Date());
        } else { // van régi rekord, módosítás
            Szamla regi = szamlaService.getSzamla(id);
            if( !regi.equals(szamla) ) {
                szamla.setModositasIdeje(new Date());
            } // if
        } // if - else        

        szamlaService.ment(szamla);
        
        return "/pages/index.jsf";
    } // mentes

    public String ujBeveteliSzamla() {
        System.out.println("ujBeveteliSzamla()");
        szamla = Szamla.ujBeveteliSzamla();
        id = null;

        return "/pages/szamla.jsf";
    } // ujBeveteliSzamla

    public String ujKiadasiSzamla() {
        System.out.println("ujKiadasiSzamla()");
        szamla = Szamla.ujKiadasiSzamla();
        id = null;
        
        return "/pages/szamla.jsf";
    } // ujKiadasiSzamla

    public String torles() {
        System.out.println("torles()");
        System.out.println("id:" + id);

        szamlaService.torol(id);
        
        return "/pages/index.jsf";
    } // modositas
    
    public String listahozVissza() {
        System.out.println("listahozVissza()");
        
        return "/pages/index.jsf";
    } // modositas

    public List<Szamla> getBeveteliSzamlak() {
        beveteliSzamlak = szamlaService.getOsszesSzamla(Szamla.Fajta.BEVETELI);

        return beveteliSzamlak;
    }

    public void setBeveteliSzamlak(List<Szamla> beveteliSzamlak) {
        this.beveteliSzamlak = beveteliSzamlak;
    }

    public List<Szamla> getKiadasiSzamlak() {
        kiadasiSzamlak = szamlaService.getOsszesSzamla(Szamla.Fajta.KIADASI);

        return kiadasiSzamlak;
    }

    public void setKiadasiSzamlak(List<Szamla> kiadasiSzamlak) {
        this.kiadasiSzamlak = kiadasiSzamlak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Szamla getSzamla() {
        return szamla;
    }

    public void setSzamla(Szamla szamla) {
        this.szamla = szamla;
    }

    
    
}
