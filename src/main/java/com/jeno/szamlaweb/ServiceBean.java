/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeno.szamlaweb;

import com.jeno.szamlaweb.model.IgenNem;
import javax.faces.bean.ManagedBean;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jeno
 */
@Component
@ManagedBean(name="serviceBean")
public class ServiceBean {
    DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy.MM.dd");
    
    public String getDate(DateTime param) {
        return param!=null?dtf.print(param):"";
    } // getDate
    
    public DateTime getDate(String param) {
        return param!=null?dtf.parseDateTime(param):null;
    } // getDate

    public IgenNem[] getIgenNem() {
        return IgenNem.values();
    } // getIgenNem
}
