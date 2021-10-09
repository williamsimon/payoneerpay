package com.williamsimon.android.payoneerpay.network;

import android.util.Log;

import com.squareup.moshi.Moshi;
import com.williamsimon.android.payoneerpay.domain.ListResult;
import com.williamsimon.android.payoneerpay.utils.ApiStatus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PaymentMethodsApiService implements PaymentMethodsService {

    private static PaymentMethodsApiService instance = null;

    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    private static Moshi moshi;

    private static Retrofit retrofit;

    private static PaymentMethodsApi retrofitService;

    private final MutableLiveData<ListResult> listResult = new MutableLiveData<>();
    private final MutableLiveData<ApiStatus> status = new MutableLiveData<>();

    private PaymentMethodsApiService(){
        moshi = new Moshi.Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        retrofitService = retrofit.create(PaymentMethodsApi.class);
    }

    public static PaymentMethodsApiService getRetrofitService() {
        if(instance == null){
            instance = new PaymentMethodsApiService();
        }
        return instance;
    }


    @Override
    public void fetchListResult() {
        status.postValue(ApiStatus.LOADING);
        retrofitService.getListResult().enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                if (response.body() != null) {
                    listResult.postValue(response.body());
                }
                status.postValue(ApiStatus.DONE);
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {
                Log.e("Retrofit error", t.getMessage());
                status.postValue(ApiStatus.ERROR);
            }
        });
    }

    public final LiveData<ListResult> getListResult(){
        return listResult;
    }

    public final LiveData<ApiStatus> getStatus(){
        return status;
    }
}
