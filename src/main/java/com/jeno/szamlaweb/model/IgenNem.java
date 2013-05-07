package com.jeno.szamlaweb.model;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jeno
 */
@ManagedBean
public enum IgenNem {
    IGEN("Igen"), 
    NEM("Nem");
    
    private String label;
    
    private IgenNem(String label) {
        this.label = label;
    } // IgenNem
    
    public String getLabel() {
        return label;
    } // getLabel
}
