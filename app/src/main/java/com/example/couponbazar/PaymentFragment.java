package com.example.couponbazar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.couponbazar.Fragments.buyFragment;

public class PaymentFragment extends Fragment{
    String phoneNumber, price,brand,code,benefits;
    TextView pno;
    private String TAG =" main";

    public PaymentFragment(String phoneNumber, String price, String brand, String code, String benefits) {
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.brand = brand;
        this.code = code;
        this.benefits = benefits;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
    }

    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_payment, container, false);
        pno=v.findViewById(R.id.PNO);
        TextView amount=v.findViewById(R.id.Price);
        pno.setText(phoneNumber);
        amount.setText(price);
        Intent i= new Intent(getActivity(),payment_gateway.class);
        i.putExtra("key_price",price);
        i.putExtra("key_pno",phoneNumber);
        i.putExtra("key_codee",code);
        i.putExtra("key_ben",benefits);
        i.putExtra("key_brand",brand);
        startActivity(i);
        return v;
    }



    public void onBackPressed() {

        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new buyFragment()).addToBackStack(null).commit();
    }




}