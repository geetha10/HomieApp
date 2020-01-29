package com.geetha.homieappreplica;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        itemAdaper = new ItemAdaper(itemsList);
        displayListRV.setAdapter(itemAdaper);
        Log.d(TAG, "onCreate: OnCreate Done");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Entering OnResume");
        super.onResume();
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
        myRef.addChildEventListener(messageListener);
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
        myRef.push().setValue(itemToAdd);
        addItemET.setText("");
    }
}
