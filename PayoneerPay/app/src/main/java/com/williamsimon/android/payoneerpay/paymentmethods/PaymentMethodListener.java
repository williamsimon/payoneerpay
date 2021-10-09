package com.williamsimon.android.payoneerpay.paymentmethods;

import com.williamsimon.android.payoneerpay.domain.ApplicableNetwork;

public interface PaymentMethodListener {
    void onClick(ApplicableNetwork applicableNetwork);
}
