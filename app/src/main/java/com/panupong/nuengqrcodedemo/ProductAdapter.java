package com.panupong.nuengqrcodedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context context;
    private ArrayList<String> nameProductStringArrayList,
            descriptionStringArrayList,amountStringArrayList,pictureStringArrayList;

    private LayoutInflater layoutInflater;


    public ProductAdapter(Context context,
                          ArrayList<String> nameProductStringArrayList,
                          ArrayList<String> descriptionStringArrayList,
                          ArrayList<String> amountStringArrayList,
                          ArrayList<String> pictureStringArrayList) {

        this.layoutInflater = LayoutInflater.from(context);
        this.nameProductStringArrayList = nameProductStringArrayList;
        this.descriptionStringArrayList = descriptionStringArrayList;
        this.amountStringArrayList = amountStringArrayList;
        this.pictureStringArrayList = pictureStringArrayList;
    }//Contructor

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recycler_product,viewGroup,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);

        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        String nameProductString = nameProductStringArrayList.get(i);
        String descriptionString = descriptionStringArrayList.get(i);
        String amountString = amountStringArrayList.get(i);
        String urlPictureString = pictureStringArrayList.get(i);

        productViewHolder.nameProductTextView.setText(nameProductString);
        productViewHolder.descriptionTextView.setText(descriptionString);
        productViewHolder.amountTextView.setText("Amount = "+amountString + " unit");

        Picasso.get().load(urlPictureString).into(productViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return nameProductStringArrayList.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

//        Explicit
        private TextView nameProductTextView,descriptionTextView,amountTextView;
        private ImageView imageView;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nameProductTextView = itemView.findViewById(R.id.txtNameProduct);
            descriptionTextView = itemView.findViewById(R.id.txtDescription);
            amountTextView = itemView.findViewById(R.id.txtAmount);
            imageView = itemView.findViewById(R.id.imvProduct);


        }
    }// ProductViewHolder Class



}// Main Class
