package com.williamsimon.android.payoneerpay.paymentmethods;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.williamsimon.android.payoneerpay.databinding.ListItemPaymentMethodBinding;
import com.williamsimon.android.payoneerpay.domain.ApplicableNetwork;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentMethodsAdapter extends ListAdapter<ApplicableNetwork, PaymentMethodsAdapter.ViewHolder> {

    private final PaymentMethodListener listener;

    PaymentMethodsAdapter(PaymentMethodListener listener){
        super(new PaymentMethodDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApplicableNetwork item = getItem(position);
        holder.bind(listener, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemPaymentMethodBinding binding;

        private ViewHolder(ListItemPaymentMethodBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PaymentMethodListener listener, ApplicableNetwork item){
            binding.setPaymentMethod(item);
            binding.setClickListener(listener);
            binding.executePendingBindings();
        }

        static ViewHolder from(ViewGroup parent){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ListItemPaymentMethodBinding binding = ListItemPaymentMethodBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding);
        }

    }

}

class PaymentMethodDiffCallback extends DiffUtil.ItemCallback<ApplicableNetwork> {

    @Override
    public boolean areItemsTheSame(@NonNull ApplicableNetwork oldItem, @NonNull ApplicableNetwork newItem) {
        return oldItem.getCode().equals(newItem.getCode());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ApplicableNetwork oldItem, @NonNull ApplicableNetwork newItem) {
        return oldItem.getLabel().equals(newItem.getLabel());
    }
}
