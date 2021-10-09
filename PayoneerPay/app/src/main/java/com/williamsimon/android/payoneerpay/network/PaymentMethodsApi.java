package com.williamsimon.android.payoneerpay.network;

import com.williamsimon.android.payoneerpay.domain.ListResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaymentMethodsApi {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<ListResult> getListResult();

}


