package com.example.couponbazar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class payment_gateway extends AppCompatActivity {
    TextView price,pno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        price=findViewById(R.id.textView3);
        pno=findViewById(R.id.textView4);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String phone = extras.getString("key_pno");
            String pricee = extras.getString("key_price");
            price.setText(pricee);
            pno.setText(phone);
            //The key argument here must match that used in the other activity
        }
    }
}