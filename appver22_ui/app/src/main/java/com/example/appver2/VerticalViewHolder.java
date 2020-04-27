package com.example.appver2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class VerticalViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView description;

    public VerticalViewHolder(View itemView) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.vertical_icon);
        description = (TextView) itemView.findViewById(R.id.vertical_description);

    }
}
