package com.williamsimon.android.payoneerpay.paymentmethods;

import com.williamsimon.android.payoneerpay.domain.ListResult;
import com.williamsimon.android.payoneerpay.network.PaymentMethodsService;
import com.williamsimon.android.payoneerpay.utils.ApiStatus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class PaymentMethodsViewModel extends ViewModel {

    private final PaymentMethodsService service;

    private LiveData<ListResult> listResult;
    private LiveData<ApiStatus> status;

    public PaymentMethodsViewModel(PaymentMethodsService service) {
        this.service = service;
    }

    public void init(){
        requestListResult();
        listResult = service.getListResult();
        status = service.getStatus();
    }

    private void requestListResult(){
        service.fetchListResult();
    }

    public final LiveData<ListResult> getListResult(){
        return listResult;
    }

    public final LiveData<ApiStatus> getStatus(){
        return status;
    }

}
