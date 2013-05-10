package com.jeno.szamlaweb;

import com.jeno.szamlaweb.services.ForgalomService;
import com.jeno.szamlaweb.model.Forgalom;
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
@ManagedBean(name="forgalomBean")
@Transactional
public class ForgalomBean {
    List<Forgalom> bejovoForgalom;
    List<Forgalom> kimenoForgalom;

    String id;
    Forgalom forgalom;
    
    @Resource
    ForgalomService forgalomService;
    
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");

    public String ujBejovoForgalom() {
        System.out.println("ujBejovoForgalom");
        forgalom = Forgalom.ujBejovoForgalom();
        id = null;
        
        return "/pages/forgalom.jsf";
    } // ujBejovoForgalom
    
    public String ujKimenoForgalom() {
        System.out.println("ujKimenoForgalom");
        forgalom = Forgalom.ujKimenoForgalom();
        id = null;
        
        return "/pages/forgalom.jsf";
    } // ujKimenoForgalom

    public String modositas() {
        System.out.println("modositas()");
        System.out.println("id:" + id);
        
        this.forgalom = forgalomService.getForgalom(id);

        return "/pages/forgalom.jsf";
    } // modositas

    public String mentes() {
        System.out.println("mentes()");
        System.out.println("id:" + id);

        if( id == null ) { // új rekord
            forgalom.setRogzitesIdeje(new Date());
        } else { // régi rekord
            Forgalom regi = forgalomService.getForgalom(id);

            if( !regi.equals(forgalom) ) {
                forgalom.setModositasIdeje(new Date());
            } // if
        } // if - else        

        forgalomService.ment(forgalom);
        
        return "/pages/index.jsf";
    } // mentes
    
    public String torles() {
        System.out.println("torles()");
        System.out.println("id:" + id);

        forgalomService.torol(id);
        
        return "success";
    } // torles

    public Forgalom getForgalom() {
        return forgalom;
    }

    public void setForgalom(Forgalom forgalom) {
        this.forgalom = forgalom;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Forgalom> getBejovoForgalom() {
        bejovoForgalom = forgalomService.getOsszesForgalom(Forgalom.Fajta.BEJOVO);

        return bejovoForgalom;
    }

    public void setBejovoForgalom(List<Forgalom> bejovoForgalom) {
        this.bejovoForgalom = bejovoForgalom;
    }

    public List<Forgalom> getKimenoForgalom() {
        kimenoForgalom = forgalomService.getOsszesForgalom(Forgalom.Fajta.KIMENO);

        return kimenoForgalom;
    }

    public void setKimenoForgalom(List<Forgalom> kimenoForgalom) {
        this.kimenoForgalom = kimenoForgalom;
    }
    
}
