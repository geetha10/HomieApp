package com.geetha.homieappreplica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ShoppingCartFragment extends Fragment {


    private Context context;
    private TextInputEditText addItemET;
    private Button addItemToListButton;
    private RecyclerView displayListRV;
    private List<Item> itemsList = new ArrayList<>();
    private ItemAdaper itemAdaper;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference shoppingListDbRef = database.getReference("itemsList");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        itemAdaper = new ItemAdaper(itemsList);
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
        shoppingListDbRef.addChildEventListener(messageListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View shoppingCartView= inflater.inflate(R.layout.fragment_shoppingcart,container,false);
       // String itemName = addItemET.getText().toString();
        addItemET = shoppingCartView.findViewById(R.id.addItemET);
        addItemToListButton = shoppingCartView.findViewById(R.id.addItemToListButton);
        addItemToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = addItemET.getText().toString();
                if (itemName.trim().isEmpty()) {
                    Toast.makeText(context, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    addItemET.setText("");
                    return;
                }
                Item itemToAdd = new Item(itemName, false);
                shoppingListDbRef.push().setValue(itemToAdd);
                addItemET.setText("");
                displayListRV.scrollToPosition(itemsList.size());
            }
        });

        displayListRV = shoppingCartView.findViewById(R.id.displayListRV);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(context);
        displayListRV.setLayoutManager(layout);
        displayListRV.setAdapter(itemAdaper);
        Log.d(TAG, "onCreate: OnCreate Done");
        return shoppingCartView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
