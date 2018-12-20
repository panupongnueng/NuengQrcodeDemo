package com.panupong.nuengqrcodedemo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListProductFragment extends Fragment {


    public ListProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecyclerView
        createRecyclerView();


    }//Main Method

    private void createRecyclerView() {

        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(linearLayoutManager);

//        Read Value From Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Product");


        int [] countInts = new int[]{0};
        int test = 0;
        final String tag = "20decV1";

        final ArrayList<String> nameProductStringArrayList = new ArrayList<>();
        final ArrayList<String> descriptionStringArrayList = new ArrayList<>();
        final ArrayList<String> pictureStringArrayList = new ArrayList<>();
        final ArrayList<String> receiveStringArrayList = new ArrayList<>();
        final ArrayList<Integer> amountArrayList = new ArrayList<>();
        final ArrayList<String> amountStringArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int amountChildren = (int) dataSnapshot.getChildrenCount();
                Log.d(tag,"amountChildren ==> "+amountChildren);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ProductModel productModel = dataSnapshot1.getValue(ProductModel.class);

                    nameProductStringArrayList.add(productModel.getNameProduct());

                    descriptionStringArrayList.add(productModel.getDescription());

                    pictureStringArrayList.add(productModel.getPicture());

                    receiveStringArrayList.add(productModel.getReceive());

                    amountArrayList.add(productModel.getAmount());

                    amountStringArrayList.add(Integer.toString(productModel.getAmount()));

                }//For

                Log.d(tag,"nameProduct ==> "+nameProductStringArrayList.toString());
                Log.d(tag,"picture ==> "+pictureStringArrayList.toString());

                ProductAdapter productAdapter = new ProductAdapter(

                        getActivity(),
                        nameProductStringArrayList,
                        descriptionStringArrayList,
                        amountStringArrayList,
                        pictureStringArrayList

                );

                recyclerView.setAdapter(productAdapter);


            }//onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }//Create RecyclerView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_product, container, false);
    }

}
