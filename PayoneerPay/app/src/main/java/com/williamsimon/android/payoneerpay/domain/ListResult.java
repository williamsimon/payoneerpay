package com.williamsimon.android.payoneerpay.domain;

import java.net.URL;
import java.util.Map;

public class ListResult {

    /** Simple API, always present */
    private Map<String, URL> links;

    /** Simple API, optional, always present in native LIST */
    private Networks networks;

    public Map<String, URL> getLinks() {
        return links;
    }

    public Networks getNetworks() {
        return networks;
    }

    public void setLinks(Map<String, URL> links) {
        this.links = links;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }
}
