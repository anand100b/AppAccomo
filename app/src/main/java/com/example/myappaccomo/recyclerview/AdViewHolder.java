package com.example.myappaccomo.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappaccomo.R;
import com.example.myappaccomo.entities.Ad;

public class AdViewHolder extends RecyclerView.ViewHolder {
    public final TextView categoryName;
    public final TextView typeName;
    public final TextView adDescription;
    public final Button callButton;
    public final Button smsButton;


    public AdViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.categoryName);
        typeName = itemView.findViewById(R.id.typeName);
        adDescription = itemView.findViewById(R.id.adDescription);
        callButton = itemView.findViewById(R.id.callButton);
        smsButton = itemView.findViewById(R.id.smsButton);

    }
    public void updateAd(Ad ad){
        this.categoryName.setText(ad.getCategory());
        this.typeName.setText(ad.getType());
        this.adDescription.setText(ad.getDescription());
    }
}
