package com.williamsimon.android.payoneerpay.domain;

import java.net.URL;
import java.util.Map;

public class ApplicableNetwork {

    /** Simple API, always present */
    private String code;
    /** Simple API, always present */
    private String label;
    /** Simple API, always present */
    private Map<String, URL> links;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, URL> getLinks() {
        return links;
    }

    public void setLinks(Map<String, URL> links) {
        this.links = links;
    }
}
