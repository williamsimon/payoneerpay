package com.williamsimon.android.payoneerpay.network;

import com.williamsimon.android.payoneerpay.domain.ListResult;
import com.williamsimon.android.payoneerpay.utils.ApiStatus;

import androidx.lifecycle.LiveData;
import retrofit2.Call;

public interface PaymentMethodsService {

    void fetchListResult();

    LiveData<ListResult> getListResult();

    LiveData<ApiStatus> getStatus();

}
