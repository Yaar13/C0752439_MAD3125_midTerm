package com.example.c0752439_mad3125_midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private LayoutInflater inflater;
    Context context;


    private onClickListener clickListener;
    private List<DataModel> dataList;

    public DataAdapter(Context mCtx, List<DataModel> dataList) {
        inflater = LayoutInflater.from(mCtx);
        context = mCtx;
        this.dataList = dataList;
    }
    public DataAdapter(Context mCtx, List<DataModel> dataList,onClickListener clickListener) {
        inflater = LayoutInflater.from(mCtx);
        context = mCtx;
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.data_item, null);
        DataViewHolder dataViewHolder = new DataViewHolder(view,clickListener);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).title);
        holder.launchYear.setText(dataList.get(position).launchYear);
        Glide.with(context).load(dataList.get(position).imageUrl).into(holder.imageView);
        //Picasso.get().load(dataList.get(position).imageUrl).into(holder.imageView);
    }


    public interface onClickListener{
        void onClickListener(int position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class DataViewHolder extends RecyclerView.ViewHolder {


        TextView title, launchYear;
        ImageView imageView;

        public DataViewHolder(View itemView, DataAdapter.onClickListener onClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            launchYear = itemView.findViewById(R.id.launchYear);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}