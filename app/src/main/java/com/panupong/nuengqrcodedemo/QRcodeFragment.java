package com.panupong.nuengqrcodedemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRcodeFragment extends Fragment implements ZXingScannerView.ResultHandler {


    private String qrString;
    private ZXingScannerView zXingScannerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        zXingScannerView = new ZXingScannerView(getActivity());
        return zXingScannerView;

    }

    @Override
    public void onResume() {
        super.onResume();

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        qrString = result.toString().trim();
        if (!qrString.isEmpty()) {
            //Read able
            Log.d("20decV3","qrString ==> "+qrString);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference()
                    .child("Product").child(qrString);


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ProductModel productModel = dataSnapshot.getValue(ProductModel.class);

                    String nameString = productModel.getNameProduct();
                    String descripString = productModel.getDescription();
                    String pictureString = productModel.getPicture();
                    String amountString = Integer.toString(productModel.getAmount());
                    String receiveString = productModel.getReceive();

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentServiceFragment,DetailFragment.detailInstance(
                                    nameString,
                                    descripString,
                                    pictureString,
                                    receiveString,
                                    amountString
                            )).commit();

                    Log.d("20decV3","NameProduct ==> "+nameString);


                }




                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }// if

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                zXingScannerView.resumeCameraPreview(QRcodeFragment.this);
            }
        }, 2000);


    }


}//main Class
