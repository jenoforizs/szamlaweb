package com.jeno.szamlaweb.services;

import com.jeno.szamlaweb.model.Szamla;
import com.jeno.szamlaweb.model.Szamla.Fajta;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jeno
 */
@Service
@Transactional
public class SzamlaService {

    @Resource(name="mongoTemplate")
    private MongoTemplate mt;
    
    public void torol(String id) {
        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(id));
        
        mt.remove(q, Szamla.class);
    } // torol

    public void ment(Szamla sz) {
        mt.save(sz);
    } // ment
    
    public Szamla getSzamla(String id) {
        return mt.findById(id, Szamla.class);
    } // getSzamla
    
    public List<Szamla> getOsszesSzamla(Fajta fajta) {
        Query q = new Query();
        q.addCriteria(Criteria.where("fajta").is(fajta.toString()));
        return mt.find(q, Szamla.class);
    } // getOsszesSzamla
    
}
