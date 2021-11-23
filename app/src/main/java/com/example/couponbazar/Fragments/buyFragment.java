package com.example.couponbazar.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.couponbazar.Adapters.SecondAdapter;
import com.example.couponbazar.Model.Buy;
import com.example.couponbazar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link buyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class buyFragment extends Fragment  {
    RecyclerView recyclerView;
    DatabaseReference reference,ref;
    SecondAdapter adapter;
    ArrayList<Buy> list;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public buyFragment() {
        // Required empty public constructor
    }

    public static buyFragment newInstance(String param1, String param2) {
        buyFragment fragment = new buyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_buy, container, false);
        recyclerView=v.findViewById(R.id.rview2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        FirebaseRecyclerOptions<Buy> options =
                new FirebaseRecyclerOptions.Builder<Buy>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("all_coupons"), Buy.class)
                        .build();

        adapter=new SecondAdapter(options);
        recyclerView.setAdapter(adapter);




        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}