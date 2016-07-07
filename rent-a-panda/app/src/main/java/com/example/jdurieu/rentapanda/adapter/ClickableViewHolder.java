package com.example.jdurieu.rentapanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by JonathanDurieu on 10/06/16.
 */

public class ClickableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public interface ClickableViewHolderListener {
        void onClick(int position);
    }

    private ClickableViewHolderListener listener;

    public ClickableViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void setListener(ClickableViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null) {
            listener.onClick(getAdapterPosition());
        }
    }
}
