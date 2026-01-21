package com.sipgate.sipvalidator.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapping of SIP header names to their corresponding RFC specifications
 */
public class SipHeaderRfcMapping {
    
    private static final Map<String, RfcReference> headerToRfc = new HashMap<>();
    
    static {
        // Core SIP headers from RFC 3261
        headerToRfc.put("accept", new RfcReference("RFC 3261", "20.1", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.1"));
        headerToRfc.put("accept-encoding", new RfcReference("RFC 3261", "20.2", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.2"));
        headerToRfc.put("accept-language", new RfcReference("RFC 3261", "20.3", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.3"));
        headerToRfc.put("alert-info", new RfcReference("RFC 3261", "20.4", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.4"));
        headerToRfc.put("allow", new RfcReference("RFC 3261", "20.5", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.5"));
        headerToRfc.put("authentication-info", new RfcReference("RFC 3261", "20.6", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.6"));
        headerToRfc.put("authorization", new RfcReference("RFC 3261", "20.7", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.7"));
        headerToRfc.put("call-id", new RfcReference("RFC 3261", "20.8", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.8"));
        headerToRfc.put("call-info", new RfcReference("RFC 3261", "20.9", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.9"));
        headerToRfc.put("contact", new RfcReference("RFC 3261", "20.10", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.10"));
        headerToRfc.put("content-disposition", new RfcReference("RFC 3261", "20.11", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.11"));
        headerToRfc.put("content-encoding", new RfcReference("RFC 3261", "20.12", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.12"));
        headerToRfc.put("content-language", new RfcReference("RFC 3261", "20.13", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.13"));
        headerToRfc.put("content-length", new RfcReference("RFC 3261", "20.14", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.14"));
        headerToRfc.put("content-type", new RfcReference("RFC 3261", "20.15", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.15"));
        headerToRfc.put("cseq", new RfcReference("RFC 3261", "20.16", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.16"));
        headerToRfc.put("date", new RfcReference("RFC 3261", "20.17", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.17"));
        headerToRfc.put("error-info", new RfcReference("RFC 3261", "20.18", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.18"));
        headerToRfc.put("expires", new RfcReference("RFC 3261", "20.19", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.19"));
        headerToRfc.put("from", new RfcReference("RFC 3261", "20.20", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.20"));
        headerToRfc.put("in-reply-to", new RfcReference("RFC 3261", "20.21", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.21"));
        headerToRfc.put("max-forwards", new RfcReference("RFC 3261", "20.22", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.22"));
        headerToRfc.put("min-expires", new RfcReference("RFC 3261", "20.23", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.23"));
        headerToRfc.put("mime-version", new RfcReference("RFC 3261", "20.24", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.24"));
        headerToRfc.put("organization", new RfcReference("RFC 3261", "20.25", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.25"));
        headerToRfc.put("priority", new RfcReference("RFC 3261", "20.26", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.26"));
        headerToRfc.put("proxy-authenticate", new RfcReference("RFC 3261", "20.27", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.27"));
        headerToRfc.put("proxy-authorization", new RfcReference("RFC 3261", "20.28", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.28"));
        headerToRfc.put("proxy-require", new RfcReference("RFC 3261", "20.29", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.29"));
        headerToRfc.put("record-route", new RfcReference("RFC 3261", "20.30", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.30"));
        headerToRfc.put("reply-to", new RfcReference("RFC 3261", "20.31", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.31"));
        headerToRfc.put("require", new RfcReference("RFC 3261", "20.32", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.32"));
        headerToRfc.put("retry-after", new RfcReference("RFC 3261", "20.33", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.33"));
        headerToRfc.put("route", new RfcReference("RFC 3261", "20.34", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.34"));
        headerToRfc.put("server", new RfcReference("RFC 3261", "20.35", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.35"));
        headerToRfc.put("subject", new RfcReference("RFC 3261", "20.36", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.36"));
        headerToRfc.put("supported", new RfcReference("RFC 3261", "20.37", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.37"));
        headerToRfc.put("timestamp", new RfcReference("RFC 3261", "20.38", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.38"));
        headerToRfc.put("to", new RfcReference("RFC 3261", "20.39", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.39"));
        headerToRfc.put("unsupported", new RfcReference("RFC 3261", "20.40", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.40"));
        headerToRfc.put("user-agent", new RfcReference("RFC 3261", "20.41", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.41"));
        headerToRfc.put("via", new RfcReference("RFC 3261", "20.42", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.42"));
        headerToRfc.put("warning", new RfcReference("RFC 3261", "20.43", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.43"));
        headerToRfc.put("www-authenticate", new RfcReference("RFC 3261", "20.44", "https://datatracker.ietf.org/doc/html/rfc3261#section-20.44"));

        // Compact forms
        headerToRfc.put("m", headerToRfc.get("contact"));
        headerToRfc.put("f", headerToRfc.get("from"));
        headerToRfc.put("i", headerToRfc.get("call-id"));
        headerToRfc.put("l", headerToRfc.get("content-length"));
        headerToRfc.put("c", headerToRfc.get("content-type"));
        headerToRfc.put("t", headerToRfc.get("to"));
        headerToRfc.put("v", headerToRfc.get("via"));

        // Additional headers from other RFCs
        headerToRfc.put("session-expires", new RfcReference("RFC 4028", "4", "https://datatracker.ietf.org/doc/html/rfc4028#section-4"));
        headerToRfc.put("min-se", new RfcReference("RFC 4028", "4", "https://datatracker.ietf.org/doc/html/rfc4028#section-4"));
        headerToRfc.put("replaces", new RfcReference("RFC 3891", "6.1", "https://datatracker.ietf.org/doc/html/rfc3891#section-6.1"));
        headerToRfc.put("referred-by", new RfcReference("RFC 3892", "4", "https://datatracker.ietf.org/doc/html/rfc3892#section-4"));
        headerToRfc.put("refer-to", new RfcReference("RFC 3515", "2.1", "https://datatracker.ietf.org/doc/html/rfc3515#section-2.1"));
        headerToRfc.put("event", new RfcReference("RFC 6665", "8.1.1", "https://datatracker.ietf.org/doc/html/rfc6665#section-8.1.1"));
        headerToRfc.put("subscription-state", new RfcReference("RFC 6665", "8.1.2", "https://datatracker.ietf.org/doc/html/rfc6665#section-8.1.2"));
        headerToRfc.put("allow-events", new RfcReference("RFC 6665", "8.1.3", "https://datatracker.ietf.org/doc/html/rfc6665#section-8.1.3"));
        headerToRfc.put("rack", new RfcReference("RFC 3262", "7.1", "https://datatracker.ietf.org/doc/html/rfc3262#section-7.1"));
        headerToRfc.put("rseq", new RfcReference("RFC 3262", "7.1", "https://datatracker.ietf.org/doc/html/rfc3262#section-7.1"));
        headerToRfc.put("p-access-network-info", new RfcReference("RFC 3455", "4.4", "https://datatracker.ietf.org/doc/html/rfc3455#section-4.4"));
        headerToRfc.put("p-called-party-id", new RfcReference("RFC 3455", "4.1", "https://datatracker.ietf.org/doc/html/rfc3455#section-4.1"));
        headerToRfc.put("p-charging-function-addresses", new RfcReference("RFC 3455", "4.6", "https://datatracker.ietf.org/doc/html/rfc3455#section-4.6"));
        headerToRfc.put("p-charging-vector", new RfcReference("RFC 3455", "4.6", "https://datatracker.ietf.org/doc/html/rfc3455#section-4.6"));
        headerToRfc.put("service-route", new RfcReference("RFC 3608", "6.1.1", "https://datatracker.ietf.org/doc/html/rfc3608#section-6.1.1"));
        headerToRfc.put("path", new RfcReference("RFC 3327", "4.1", "https://datatracker.ietf.org/doc/html/rfc3327#section-4.1"));
    }
    
    /**
     * Get RFC reference for a SIP header
     * @param headerName The name of the SIP header (case-insensitive)
     * @return RfcReference or null if not found
     */
    public static RfcReference getRfcReference(String headerName) {
        if (headerName == null) {
            return null;
        }
        return headerToRfc.get(headerName.toLowerCase());
    }
    
    /**
     * Class to hold RFC reference information
     */
    public static class RfcReference {
        private final String rfc;
        private final String section;
        private final String url;
        
        public RfcReference(String rfc, String section, String url) {
            this.rfc = rfc;
            this.section = section;
            this.url = url;
        }
        
        public String getRfc() {
            return rfc;
        }
        
        public String getSection() {
            return section;
        }
        
        public String getUrl() {
            return url;
        }
        
        public String getDisplayText() {
            return rfc + " Section " + section;
        }
    }
}
