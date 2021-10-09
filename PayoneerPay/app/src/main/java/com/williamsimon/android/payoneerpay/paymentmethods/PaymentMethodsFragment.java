package com.williamsimon.android.payoneerpay.paymentmethods;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.williamsimon.android.payoneerpay.MainActivity;
import com.williamsimon.android.payoneerpay.R;
import com.williamsimon.android.payoneerpay.databinding.FragmentPaymentMethodsBinding;
import com.williamsimon.android.payoneerpay.domain.ApplicableNetwork;
import com.williamsimon.android.payoneerpay.domain.ListResult;
import com.williamsimon.android.payoneerpay.network.PaymentMethodsApiService;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentMethodsFragment extends Fragment {

    private PaymentMethodsViewModel viewModel;
    private PaymentMethodsViewModelFactory viewModelFactory;
    private PaymentMethodsAdapter adapter;


    public PaymentMethodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPaymentMethodsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_methods, container, false);

        viewModelFactory = new PaymentMethodsViewModelFactory(
                PaymentMethodsApiService.getRetrofitService()
        );

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentMethodsViewModel.class);
        viewModel.init();

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        adapter = new PaymentMethodsAdapter(new PaymentMethodListener(){
            @Override
            public void onClick(ApplicableNetwork applicableNetwork) {
                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
            }
        });

        binding.paymentMethodsRecycler.setAdapter(adapter);

        viewModel.getListResult().observe(getViewLifecycleOwner(), new Observer<ListResult>() {
            @Override
            public void onChanged(ListResult listResult) {
                if(listResult != null){
                    adapter.submitList(listResult.getNetworks().getApplicable());
                }
            }
        });

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.home));

        return binding.getRoot();
    }

}
