package com.geetha.homieappreplica;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    EditText addItemET;

    /*TextInputEditText.OnEditorActionListener addItemETListener = new TextInputEditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            String itemName = addItemET.getText().toString();
            if (itemName.trim().isEmpty()) return false;
            Item itemToAdd = new Item(itemName, false);
            itemsList.add(itemToAdd);
            itemAdaper.notifyItemInserted(itemsList.size());
            addItemET.setText("");
            return true;
        }
    };*/
    TextInputEditText addItemET;
    Button addItemToListButton;
    RecyclerView displayListRV;
    List<Item> itemsList = new ArrayList<>();
    ItemAdaper itemAdaper;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("itemsList");
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Entering OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemET = findViewById(R.id.addItemET);
        addItemToListButton = findViewById(R.id.addItemToListButton);
        addItemToListButton.setOnClickListener(this);
        displayListRV = findViewById(R.id.displayListRV);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        displayListRV.setLayoutManager(layout);
        //displayListRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        itemAdaper = new ItemAdaper(itemsList);
        displayListRV.setAdapter(itemAdaper);

       // ValueEventListener valueEventListener;
        Log.d(TAG, "onCreate: OnCreate Done");

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Entering OnResume");
        super.onResume();
        //itemsList.addAll(getItems());

        ChildEventListener messageListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                      Item itemFromDB = dataSnapshot.getValue(Item.class);
                      Log.d(TAG, "onChildAdded: " + itemFromDB);
                      itemsList.add(itemFromDB);
                      Log.d(TAG, "onDataChange: itemFromDB" + itemFromDB);
                      itemAdaper.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
       /* ValueEventListener messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot itemsFromDB: dataSnapshot.getChildren()){
                        Item itemFromDB = itemsFromDB.getValue(Item.class);
                        itemsList.add(itemFromDB);
                        Log.d(TAG, "onDataChange: itemFromDB" + itemFromDB);
                        itemAdaper.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
            }
        };*/
        myRef.addChildEventListener(messageListener);
        //itemAdaper.notifyDataSetChanged();
        Log.d(TAG, "onResume: OnResume done");
    }

    @Override
    public void onClick(View v) {
        String itemName = addItemET.getText().toString();
        if (itemName.trim().isEmpty())
        {
            Toast.makeText(this,"Enter a valid name",Toast.LENGTH_SHORT).show();
            addItemET.setText("");
            return;
        }
        Item itemToAdd = new Item(itemName, false);
        //myRef.push().setValue(itemToAdd);
        myRef.push().setValue(itemToAdd);
        //itemsList.add(itemToAdd);
        //itemAdaper.notifyItemInserted(itemsList.size());
        addItemET.setText("");
    }

    /*private List<Item> getItems() {
        List<Item> tempList = new ArrayList<>();
        String[] mandatoryItemsList = {"Milk", "Eggs", "Sugar", "Salt"};
       // myRef.on;
        for (String mandatoryItemname : mandatoryItemsList) {
            Item tempItem = new Item(mandatoryItemname, false);
            //tempList.add(tempItem);
            myRef.push().setValue(tempItem);
        }
        return tempList;
    }*/
}
