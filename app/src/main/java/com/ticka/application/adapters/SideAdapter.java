package com.ticka.application.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ticka.application.R;
import com.ticka.application.models.SideModel;

import java.util.List;

public class SideAdapter extends RecyclerView.Adapter<SideAdapter.VH> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SideModel> models;
    private OnItemClickListener onItemClickListener;
    private int lastPosition = 0;

    public SideAdapter(Context context , List<SideModel> models) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.models = models;
    }

    public SideAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
        this.notifyDataSetChanged();
    }

    public int getLastPosition() {
        return lastPosition;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(layoutInflater.inflate(R.layout.layout_rec_side_menu, viewGroup , false));
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, @SuppressLint("RecyclerView") final int position) {

        holder.title.setText(models.get(position).getTitle());
        holder.icon.setImageDrawable(
                context.getResources().getDrawable(
                        models.get(position).getIcon()
                )
        );

        if(lastPosition == position){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorButton));
            holder.icon.setColorFilter(context.getResources().getColor(R.color.colorWhite));
            holder.arrow.setColorFilter(context.getResources().getColor(R.color.colorWhite));
            holder.title.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            holder.icon.setColorFilter(context.getResources().getColor(R.color.colorBlack));
            holder.arrow.setColorFilter(context.getResources().getColor(R.color.colorBlack));
            holder.title.setTextColor(context.getResources().getColor(R.color.colorBlack));
        }

        if(onItemClickListener != null){

            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(holder.root , position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class VH extends RecyclerView.ViewHolder {

        RelativeLayout root;
        ImageView icon , arrow;
        TextView title;

        VH(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            arrow = itemView.findViewById(R.id.arrow);
            title = itemView.findViewById(R.id.title);
            root = itemView.findViewById(R.id.root);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

}
