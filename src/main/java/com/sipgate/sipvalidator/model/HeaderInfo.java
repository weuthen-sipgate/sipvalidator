package com.sipgate.sipvalidator.model;

import com.sipgate.sipvalidator.service.SipHeaderRfcMapping.RfcReference;

/**
 * Information about a validated SIP header
 */
public class HeaderInfo {
    private String name;
    private String canonicalName;
    private String type;
    private RfcReference rfcReference;
    
    public HeaderInfo() {}
    
    public HeaderInfo(String name, String canonicalName, String type, RfcReference rfcReference) {
        this.name = name;
        this.canonicalName = canonicalName;
        this.type = type;
        this.rfcReference = rfcReference;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCanonicalName() {
        return canonicalName;
    }
    
    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public RfcReference getRfcReference() {
        return rfcReference;
    }
    
    public void setRfcReference(RfcReference rfcReference) {
        this.rfcReference = rfcReference;
    }
}
