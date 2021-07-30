package com.example.couponbazar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class SecondAdapter extends FirebaseRecyclerAdapter<Buy,SecondAdapter.MyViewHolderr> {
//    Context context;
//    ArrayList<Buy> list;
    private OnItemClickListener mlistener;


    public SecondAdapter(@NonNull FirebaseRecyclerOptions<Buy> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolderr holder, int position, @NonNull Buy model) {
        holder.cou_ben.setText(model.getBenefits());
        holder.cou_brand.setText(model.getBrand());
        holder.cou_name.setText(model.getName());
        holder.cou_pno.setText(model.getPhoneNo());
        holder.cou_price.setText(model.getPrice());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    @NonNull
    @Override
    public MyViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_sales,parent,false);
        return new MyViewHolderr(v);
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }


//    @NonNull
//    @Override
//    public MyViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_sales,parent,false);
//        return new MyViewHolderr(view,mlistener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SecondAdapter.MyViewHolderr holder, int position) {
//        Buy b = list.get(position);
//        holder.cou_ben.setText(b.getBenefits());
//        holder.cou_brand.setText(b.getBrand());
//        holder.cou_name.setText(b.getName());
//        holder.cou_pno.setText(b.getPhoneNo());
//        holder.cou_price.setText(b.getPrice());
//
//
//}
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }



    public class MyViewHolderr extends RecyclerView.ViewHolder{
        TextView cou_brand,cou_ben,cou_price,cou_pno,cou_name;
        View v;
        Button btn;


        public MyViewHolderr(@NonNull View itemView) {
            super(itemView);
            cou_pno=itemView.findViewById(R.id.pno);
            cou_brand=itemView.findViewById(R.id.brandd);
            cou_ben=itemView.findViewById(R.id.bene);
            cou_price=itemView.findViewById(R.id.pricee);
            cou_name=itemView.findViewById(R.id.namee);
            btn=itemView.findViewById(R.id.buy_btn);
            v=itemView;
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.OnItemClick(position);
//                        }
//                    }
//                }
//            });

        }
    }
}
