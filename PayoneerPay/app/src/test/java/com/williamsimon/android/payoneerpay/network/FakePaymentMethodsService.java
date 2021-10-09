package com.williamsimon.android.payoneerpay.network;

import com.williamsimon.android.payoneerpay.domain.ListResult;
import com.williamsimon.android.payoneerpay.utils.ApiStatus;

import java.io.IOException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FakePaymentMethodsService implements PaymentMethodsService {

    private ListResult listResultData;

    private final MutableLiveData<ListResult> listResult = new MutableLiveData<>();
    private final MutableLiveData<ApiStatus> status = new MutableLiveData<>();

    @Override
    public void fetchListResult() {
        listResult.postValue(listResultData);
        if(listResultData != null){
            status.postValue(ApiStatus.DONE);
        }
    }

    @Override
    public LiveData<ListResult> getListResult() {
        return listResult;
    }

    @Override
    public LiveData<ApiStatus> getStatus() {
        return status;
    }

    public ListResult getListResultData() {
        return listResultData;
    }

    public void setListResultData(ListResult listResultData) {
        this.listResultData = listResultData;
    }

}
