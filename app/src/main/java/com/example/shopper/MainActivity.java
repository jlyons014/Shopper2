package com.example.shopper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    //declare an intent
    Intent intent;

    //declare a db handler
    DBHandler dbHandler;

    //declare shopping lists cursor adapter
    ShoppingLists shoppingListsAdapter;

    //declare a listView
    ListView shopperListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize the dbHandler
        dbHandler = new DBHandler(this,null);

        //initialize the listview
        shopperListView = (ListView) findViewById(R.id.shopperListView);

        //initialize shopping lists cursor adapter
        shoppingListsAdapter = new ShoppingLists(this, dbHandler.getShoppingLists(), 0);

        //set shopping lists cursor adapter on listview
        shopperListView.setAdapter(shoppingListsAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method gets called when an item in the overflow menu is selected and
     * it controls what happens when the item is selected
     * @param item item selected in the overflow menu. it contains information about the item
     * @return
     */
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

    public void openCreateList(View view) {
        // initializing an intent for the create list activity and starting it
        intent = new Intent(this, CreateList.class);
        startActivity(intent);
    }
}
