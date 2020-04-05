package com.example.appver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private List<lst> list;
    Context context;
    private int lastPosition = -1;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title, msg;

        public CustomViewHolder(View itemView) {
            super(itemView);
            // ?
            this.title = itemView.findViewById(R.id.title);
            this.msg = itemView.findViewById(R.id.message);

        }
    }

//    public RecyclerViewAdapter(Context context, List<lst> list) {
//        this.list = list;
//        this.context = context;
//    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_map, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        lst s = list.get(i);
        customViewHolder.title.setText(s.getTitle());
        customViewHolder.msg.setText(s.getMsg());
        setAnimation(customViewHolder.itemView, i);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CustomViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setDuration(500);
            itemView.startAnimation(animation);
            lastPosition = position;
        }
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }


}

//    public class CustomViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
//        protected TextView title,msg;
//        public CustomViewHolder(View itemView) {
//            super(itemView);
//            // ?
//            this.title = itemView.findViewById(R.id.title);
//            this.msg = itemView.findViewById(R.id.message);
//            this.itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (v == itemView) {
//            }
//        }
//    }





