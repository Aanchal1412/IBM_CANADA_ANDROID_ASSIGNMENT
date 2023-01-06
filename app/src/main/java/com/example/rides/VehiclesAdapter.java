package com.example.rides;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.VHolder> {


    ItemClickListener itemClickListener;
    Context context;
    List<DataModel> datamodel;
    DataModel model;

    public VehiclesAdapter(Context applicationContext, List<DataModel> datamodel,ItemClickListener itemClickListener) {


        this.context = applicationContext;
        this.datamodel = datamodel;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_details,parent,false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {

        model = datamodel.get(position);

        holder.tv_count.setText("" + String.valueOf(position+1));


       holder.tv_vin.setText("Vin:  " + model.getVin());
       holder.tv_make_and_model.setText("Make-and-model:  " + model.getMakeAndModel());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               itemClickListener.onItemClick(model,position);
           }
       });

    }

    @Override
    public int getItemCount() {
        return datamodel.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {

        TextView tv_vin,tv_make_and_model,tv_count;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            tv_vin = itemView.findViewById(R.id.tv_vin);
            tv_make_and_model = itemView.findViewById(R.id.tv_make_and_model);
            tv_count = itemView.findViewById(R.id.tv_count);
        }
    }

    public interface ItemClickListener {

        void onItemClick(DataModel dataModel,int position);
    }

}
