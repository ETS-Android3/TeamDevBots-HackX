package com.example.couponbazar;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentFragment extends Fragment implements PaymentResultListener {
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
        i.putExtra("key_code",code);
        i.putExtra("key_ben",benefits);
        i.putExtra("key_brand",brand);
        startActivity(i);
//        startpayment();

        return v;
    }

    public void startpayment() {
        final Activity activity = getActivity();
        final Checkout co = new Checkout();
        try {
            Checkout.preload(activity.getApplicationContext());
            JSONObject options = new JSONObject();
            options.put("name", "Coupon Bazaar");
            options.put("send_sms_hash",true);
            options.put("description", "App Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");
            String payment = price;
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "kamal.bunkar07@gmail.com");
            preFill.put("contact", phoneNumber);
            options.put("prefill", preFill);
            co.open(getActivity(), options);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void onBackPressed() {

        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new buyFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getActivity(), "Payment successfully done! " +s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

        try {
            Toast.makeText(getActivity(), "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }
}