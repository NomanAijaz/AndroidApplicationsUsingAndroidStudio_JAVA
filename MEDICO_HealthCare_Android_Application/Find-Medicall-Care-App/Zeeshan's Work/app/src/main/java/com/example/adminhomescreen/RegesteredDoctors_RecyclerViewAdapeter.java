package com.example.adminhomescreen;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegesteredDoctors_RecyclerViewAdapeter extends RecyclerView.Adapter<RegesteredDoctors_RecyclerViewAdapeter.MyViewHolder> {
    Context mcontext;
    List<ListItemInfoContainer_RegesteredDoctors> mData;
    Dialog mydialog;

    List<ServiceAvailbleForCustomer> serviceAvailbleForCustomers;

    public RegesteredDoctors_RecyclerViewAdapeter(Context mcontext, List<ListItemInfoContainer_RegesteredDoctors> mData, List<ServiceAvailbleForCustomer> serviceAvailbleForCustomers) {
        this.mcontext = mcontext;
        this.mData = mData;
        this.serviceAvailbleForCustomers=serviceAvailbleForCustomers;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.registered_doctors_list,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;


    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position)
    {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_disc.setText(mData.get(position).getDesc());
        holder.img.setImageResource(mData.get(position).getPhoto());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView tv_name;
       private TextView tv_disc;
       private ImageView img;
       private LinearLayout item_service;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            item_service = (LinearLayout)itemView.findViewById(R.id.ListRoot);
            tv_name = (TextView)itemView.findViewById(R.id.services_name);
            tv_disc = (TextView)itemView.findViewById(R.id.services_desc);
            img = (ImageView) itemView.findViewById(R.id.img_services);
        }
    }



}
