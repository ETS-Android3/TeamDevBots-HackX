package com.example.couponbazar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link yourSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class yourSaleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    FirstAdapter adapter;
    DatabaseReference database,ref2;
    ArrayList<ManageSales> list;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public yourSaleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment yourSaleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static yourSaleFragment newInstance(String param1, String param2) {
        yourSaleFragment fragment = new yourSaleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentActivity c = getActivity();
        View view=inflater.inflate(R.layout.fragment_your_sale, container, false);
        recyclerView = view.findViewById(R.id.rview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        database= FirebaseDatabase.getInstance().getReference("Sales").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        list=new ArrayList<ManageSales>();
        adapter=new FirstAdapter(list);
        recyclerView.setAdapter(adapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ManageSales m=dataSnapshot.getValue(ManageSales.class);
                    list.add(m);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }


}