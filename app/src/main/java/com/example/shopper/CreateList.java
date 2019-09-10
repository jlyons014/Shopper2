package com.example.shopper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateList extends AppCompatActivity {

    Intent intent;
    //declare EditTexts
    EditText nameEditText;
    EditText storeEditText;
    EditText dateEditText;

    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize EditTexts
        nameEditText = findViewById(R.id.nameEditText);
        storeEditText = findViewById(R.id.storeEditText);
        dateEditText = findViewById(R.id.dateEditText);

        //initialize calendar
        calendar = Calendar.getInstance();

        //initialize a DatePickerDialog and register an OnDateSetListener to it
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //set the Calendar year, month, and day to the year, month and day
                //set in the DatePickerDialog
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                //call method that updates the date with date set
                //in the DatePicker
                updateDueDate();

            }
        };

        //register an OnClickListener to the date EditText
        dateEditText.setOnClickListener(new View.OnClickListener(){
            /**
             * This method gets called when the date EditText is clicked
             * @param view because the date EditText that calls this method is
             *             technically considered a view, we must pass the method
             *             a View
             */
            @Override
            public void onClick(View view) {
                //display DatePickerDialog with current date selected
                new DatePickerDialog(CreateList.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)        ).show();
            }
        });

    }

    public void createList (MenuItem menuItem) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //getting the ID of the item that was selected
        switch (item.getItemId()) {
            case R.id.action_home :
                // initializing an intent for the main activity, starting it,
                // and returning true
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            // initializing an intent for the create list activity, starting it,
            // and returning true
            case R.id.action_create_list :
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void updateDueDate() {
        //this method gets called when the date is set in the DatePickerDialog

        //Create a SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //Apply the SimpleDateFormat to the date set in the DatePickerDialog and
        //set the formatted date in the date EditText
        dateEditText.setText(simpleDateFormat.format(calendar.getTime()));

    }

}
