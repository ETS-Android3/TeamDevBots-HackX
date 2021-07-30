package com.example.couponbazar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addSaleFragment extends Fragment {
    EditText couBrand,couCode,couBen,couPrice;
    Button sell;
    DatabaseReference reff,reff2,reference;
    FirebaseDatabase db;
    public String name,pnumber;
    ManageSales manageSales;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addSaleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addSaleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addSaleFragment newInstance(String param1, String param2) {
        addSaleFragment fragment = new addSaleFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_sale, container, false);
        couBrand=v.findViewById(R.id.cbrand);
        couCode=v.findViewById(R.id.code);
        couBen=v.findViewById(R.id.ben);
        couPrice=v.findViewById(R.id.cprice);
        sell=v.findViewById(R.id.sell);
        reference=FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);

                    name= user.fullName;
                    pnumber=user.phone;
                String price = couPrice.getText().toString().trim();
                String brand = couBrand.getText().toString().trim();
                String benefits = couBen.getText().toString().trim();
                String codee = couCode.getText().toString().trim();
                Buy buy= new Buy(name,benefits,price,brand,pnumber);
                    reff2=FirebaseDatabase.getInstance().getReference().child("Sales2").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff=FirebaseDatabase.getInstance().getReference().child("Sales").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("s");

                String price = couPrice.getText().toString().trim();
                String brand = couBrand.getText().toString().trim();
                String benefits = couBen.getText().toString().trim();
                String codee = couCode.getText().toString().trim();
                ManageSales m = new ManageSales(brand, benefits, codee, price);


                    reff.setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                couCode.setText("");
                                couBrand.setText("");
                                couBen.setText("");
                                couPrice.setText("");
                                Toast.makeText(getActivity(), "Sale added!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Some Error Occurred!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user=snapshot.getValue(User.class);

                        name= user.fullName;
                        pnumber=user.phone;
                        String price = couPrice.getText().toString().trim();
                        String brand = couBrand.getText().toString().trim();
                        String benefits = couBen.getText().toString().trim();
                        String codee = couCode.getText().toString().trim();
                        Buy buy= new Buy(name,benefits,price,brand,pnumber);
                        reff2=FirebaseDatabase.getInstance().getReference().child("Sales2").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        reff2.setValue(buy);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });

            }
        });
        return v;
    }
}