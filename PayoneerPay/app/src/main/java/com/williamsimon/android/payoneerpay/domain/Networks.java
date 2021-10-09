package com.williamsimon.android.payoneerpay.domain;

import java.util.Date;
import java.util.List;

public class Networks {

    /** Simple API, always present */
    private List<ApplicableNetwork> applicable;

    public List<ApplicableNetwork> getApplicable() {
        return applicable;
    }

    public void setApplicable(List<ApplicableNetwork> applicable) {
        this.applicable = applicable;
    }

}
