package com.example.myappaccomo.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappaccomo.R;
import com.example.myappaccomo.entities.Ad;

import java.util.List;

public class AdRecyclerViewAdapter extends RecyclerView.Adapter<AdViewHolder>{
    private List<Ad> ads;
    private Context context;

    public AdRecyclerViewAdapter(List<Ad>ads, Context context){
        this.ads = ads;
        this.context = context;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View adView = inflater.inflate(R.layout.recycler_item_view, parent, false);
         AdViewHolder adViewHolder = new AdViewHolder(adView);
         return adViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
    Ad ad = ads.get(position);
    holder.updateAd(ad);
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }
    public  void addItem(Ad ad){
        ads.add(ad);
        notifyItemInserted(getItemCount());
    }
}
