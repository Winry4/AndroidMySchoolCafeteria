package com.example.myschoolcafeteria;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    public static OnListFragmentInteractionListener mListener;

    private String nameAlumno;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ItemFragment.OnListFragmentInteractionListener) {
            mListener = (ItemFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(loadProductsFromJSON()));
        }


        return view;
    }
    private ArrayList<Product> loadProductsFromJSON() {
        ArrayList<Product> productsArray=new ArrayList<Product>();
        String json = null;
        try {
            InputStream is =
                    getActivity().getAssets().open("productList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray products = jsonObject.getJSONArray("products");
            for (int i = 0; i < products.length(); i++) {
                JSONObject jsonProduct = products.getJSONObject(i);
                String name = jsonProduct.getString("name");
                String description = jsonProduct.getString("description");
                String price = jsonProduct.getString("price");
                Bitmap photo=null;
                try {
                    photo= BitmapFactory.decodeStream(
                            getActivity().getAssets().open("images/"+
                                    jsonProduct.getString("image")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                productsArray.add(new Product(name,description,photo,Float.parseFloat(price),0));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return productsArray;
    }
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddQuantity(int position);
        void onRemoveQuantity(int position);
    }
}