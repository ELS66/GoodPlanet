package com.example.goodplanet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodplanet.R;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<String> list;

    public FileAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_file,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder){

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        TextView mTvPromotion;
        TextView mTvState;

        public Holder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
