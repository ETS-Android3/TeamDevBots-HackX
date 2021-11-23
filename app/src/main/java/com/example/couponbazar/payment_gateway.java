package com.example.couponbazar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.couponbazar.Model.MyCoupons;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class payment_gateway extends AppCompatActivity implements PaymentResultListener {
    TextView pricee,pno;
    String phone,price, codee,brand,benefits;
    DatabaseReference ref,ref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
//        pricee=findViewById(R.id.textView3);
//        pno=findViewById(R.id.textView4);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            phone = extras.getString("key_pno");
            price = extras.getString("key_price");
            codee = extras.getString("key_codee");
            benefits = extras.getString("key_ben");
            brand = extras.getString("key_brand");
        }
        startpayment();

    }
    public void startpayment() {
        final Activity activity = this;
        final Checkout co = new Checkout();
        try {
            Checkout.preload(activity.getApplicationContext());
            JSONObject options = new JSONObject();
            options.put("name", "Coupon Bazaar");
            options.put("send_sms_hash",true);
            options.put("description", "App Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2ssctLD-qR7A2ScxpcAmZP-LgIF_4r6fcDQ&usqp=CAU");
            options.put("currency", "INR");
            String payment = price;
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "kamal.bunkar07@gmail.com");
            preFill.put("contact", phone);
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        MyCoupons mc = new MyCoupons("xyz123",brand,benefits);
        ref= FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CouponsBought").push();
        ref.setValue(mc);
        Intent i = new Intent(this,HomeActivity.class);
        i.putExtra("key_trans",s);
        startActivity(i);
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }
}