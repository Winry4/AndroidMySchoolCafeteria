package com.example.myschoolcafeteria;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class OrderActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView nameText = findViewById(R.id.editTextTextPersonName);
        final Spinner spinner = findViewById(R.id.spinner);
        FloatingActionButton fab = findViewById(R.id.fab);
        final TextView horaText = findViewById(R.id.textView);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameAlumno = nameText.getText().toString();
                String tipoElegido = spinner.getSelectedItem().toString();
                Intent i= new Intent();
                i.createChooser(i,"Choose the app to send the email with your order");
                i.setAction(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String para = "myschoolcafeteria@uoc.edu";
                String subjectText = "Order from: " +  nameAlumno + " de: " + tipoElegido;
                String productosElegidos = "";
                int precioTotal = 0;
                for(int a= 0; a < MyItemRecyclerViewAdapter.mValues.size(); a++)
                {
                    if(MyItemRecyclerViewAdapter.mValues.get(a).quantity > 0)
                    {
                        productosElegidos += "\n " + MyItemRecyclerViewAdapter.mValues.get(a).quantity +MyItemRecyclerViewAdapter.mValues.get(a).name;
                        precioTotal += MyItemRecyclerViewAdapter.mValues.get(a).price;
                    }

                }
                String emailText = "Los productos elegidos son : " + productosElegidos + " \n Precio total: " + precioTotal;
                i.putExtra(Intent.EXTRA_EMAIL, para);
                i.putExtra(Intent.EXTRA_SUBJECT, subjectText);
                i.putExtra(Intent.EXTRA_TEXT,emailText);
                i.setType("message/rfc822");
                startActivity(i);
            }
        });

        horaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        horaText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    @Override
    public void onAddQuantity(int position) {
        MyItemRecyclerViewAdapter.mValues.get(position).quantity += 1;
    }

    @Override
    public void onRemoveQuantity(int position) {
        MyItemRecyclerViewAdapter.mValues.get(position).quantity -= 1;
    }
}