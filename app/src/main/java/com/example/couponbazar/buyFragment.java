package com.example.couponbazar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link buyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class buyFragment extends Fragment {
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Sales2"), Buy.class)
                        .build();

        adapter=new SecondAdapter(options);
        recyclerView.setAdapter(adapter);

//        reference= FirebaseDatabase.getInstance().getReference("Sales2");
//        list=new ArrayList<Buy>();
//        adapter=new SecondAdapter(list);
//        recyclerView.setAdapter(adapter);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    Buy m=dataSnapshot.getValue(Buy.class);
//                    list.add(m);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        adapter.setOnItemClickListener(new SecondAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(int position) {
//                try {
//                    Intent i = new Intent(getActivity(), payment_gateway.class);
//                    ref=FirebaseDatabase.getInstance().getReference("Sales2");
//                    ref.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Buy buy = snapshot.getValue(Buy.class);
//                            if(buy != null){
//                                i.putExtra("key_pno",buy.phoneNo);
//                                i.putExtra("key_price",buy.price);
//                                startActivity(i);
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }catch (Exception e){
//                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



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