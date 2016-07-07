package com.example.jdurieu.rentapanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by JonathanDurieu on 10/06/16.
 */

public abstract class ClickableRecyclerViewAdapter <VH extends  ClickableViewHolder> extends RecyclerView.Adapter implements ClickableViewHolder.ClickableViewHolderListener {

    public interface ClickableRecyclerViewAdapterListener {
        void onClick(int position);
    }

    private ClickableRecyclerViewAdapterListener listener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH clickableViewHolder = instantiateViewHolder(parent, viewType);
        clickableViewHolder.setListener(this);

        return clickableViewHolder;
    }

    public void setListener(ClickableRecyclerViewAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindCurrentViewHolder((VH)holder, position);
    }

    public abstract void bindCurrentViewHolder(VH holder, int position);

    public abstract VH instantiateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onClick(int position) {
        if(listener!=null) {
            listener.onClick(position);
        }
    }
}
