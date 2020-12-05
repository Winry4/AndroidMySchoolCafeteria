package com.example.myschoolcafeteria;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Product}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    public static List<Product> mValues;



    public MyItemRecyclerViewAdapter(List<Product> items) {
        mValues = items;
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.description.setText(mValues.get(position).description);
        holder.name.setText(mValues.get(position).name);
        holder.price.setText(String.valueOf(mValues.get(position).price));
        holder.photo.setImageBitmap(mValues.get(position).photo);

        holder.productAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //update list & IU
                ItemFragment.mListener.onAddQuantity(position);
                holder.quantity.setText(""+mValues.get(position).quantity);
            }
        });

        holder.productRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemFragment.mListener.onRemoveQuantity(position);
                holder.quantity.setText(""+mValues.get(position).quantity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView productAdd;
        public final ImageView productRemove;
        public final ImageView photo;
        public final TextView description;
        public final TextView name;
        public final TextView price;
        public final TextView quantity;

        public Product mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            productAdd = view.findViewById(R.id.item_add);
            productRemove = view.findViewById(R.id.item_remove);
            photo = view.findViewById(R.id.item_photo);
            description = view.findViewById(R.id.item_description);
            name = view.findViewById(R.id.item_name);
            price = view.findViewById(R.id.item_price);
            quantity = view.findViewById(R.id.item_quantity);

        }


        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", productAdd=" + productAdd +
                    ", productRemove=" + productRemove +
                    ", photo=" + photo +
                    ", description=" + description +
                    ", name=" + name +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", mItem=" + mItem +
                    '}';
        }

    }
}