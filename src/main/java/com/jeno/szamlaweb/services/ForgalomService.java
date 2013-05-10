package com.jeno.szamlaweb.services;

import com.jeno.szamlaweb.model.Forgalom;
import com.jeno.szamlaweb.model.Forgalom.Fajta;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Sort;
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
public class ForgalomService {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mt;

    public void torol(String id) {
        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(id));

        mt.remove(q, Forgalom.class);
    } // torol

    public void ment(Forgalom f) {
        mt.save(f);
    } // ment

    public Forgalom getForgalom(String id) {
        return mt.findById(id, Forgalom.class);
    } // getForgalom

    public List<Forgalom> getOsszesForgalom(Fajta fajta) {
        Query q = new Query();
        q.addCriteria(Criteria.where("fajta").is(fajta.toString()));
        q.with(new Sort(Sort.Direction.ASC, "idopont"));
        
        return mt.find(q, Forgalom.class);
    } // getOsszesForgalom
}
