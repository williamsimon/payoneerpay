package com.williamsimon.android.payoneerpay.paymentmethods;

import com.williamsimon.android.payoneerpay.network.PaymentMethodsService;
import com.williamsimon.android.payoneerpay.paymentmethods.PaymentMethodsViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PaymentMethodsViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final PaymentMethodsService service;

    public PaymentMethodsViewModelFactory(@NonNull PaymentMethodsService service) {
        this.service = service;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == PaymentMethodsViewModel.class){
            return (T) new PaymentMethodsViewModel(service);
        }
        return null;
    }
}
