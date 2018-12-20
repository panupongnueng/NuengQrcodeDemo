package com.panupong.nuengqrcodedemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

//    Explicit
    private String nameProductString,descriptionString,pictureString,receiveString,amountString;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment detailInstance(String nameProduct,
                                         String description,
                                         String urlPicture,
                                         String receive,
                                         String amount) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle   = new Bundle();
        bundle.putString("NameProduct",nameProduct);
        bundle.putString("Description",description);
        bundle.putString("Picture",urlPicture);
        bundle.putString("Receive",receive);
        bundle.putString("Amount",amount);
        detailFragment.setArguments(bundle);

        return detailFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Receive Value

        receiveValue();




    }//Main Method

    private void receiveValue() {
        nameProductString = getArguments().getString("NameProduct","NameProduct");
        descriptionString = getArguments().getString("Description","Description");
        pictureString = getArguments().getString("Picture","https://firebasestorage.googleapis.com/v0/b/nuengqrcode.appspot.com/o/Product%2Ficonfinder_clock_1055090.png?alt=media&token=4249c1a2-306e-45ef-87d6-02178c8ceb15");
        receiveString = getArguments().getString("Receive","Receive");
        amountString = getArguments().getString("Amount","Amount");
        Log.d("20decV2","name ==> "+nameProductString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
