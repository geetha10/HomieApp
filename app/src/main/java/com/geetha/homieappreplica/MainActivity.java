package com.geetha.homieappreplica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addItemET;
    Button addItemToListButton;
   // TextView displayListTV;
    RecyclerView displayListRV;
    List<Item> itemsList= new ArrayList<>();
    ItemAdaper itemAdaper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemET=findViewById(R.id.addItemET);
        addItemToListButton=findViewById(R.id.addItemToListButton);
        addItemToListButton.setOnClickListener(this);
        displayListRV=findViewById(R.id.displayListRV);
        RecyclerView.LayoutManager layout=new LinearLayoutManager(this);
        displayListRV.setLayoutManager(layout);
        itemAdaper = new ItemAdaper(itemsList);
        displayListRV.setAdapter(itemAdaper);

    }

    @Override
    protected void onResume() {
        super.onResume();
        itemsList.addAll(getItems());
        itemAdaper.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        String itemName=addItemET.getText().toString();
        Item itemToAdd= new Item(itemName, false);
        itemsList.add(itemToAdd);
        itemAdaper.notifyItemInserted(itemsList.size());
        addItemET.setText("");
    }

    private List<Item> getItems(){
        List<Item> tempList= new ArrayList<>();
        String[] mandatoryItemsList={"Milk", "Eggs", "Sugar", "Salt"};
        for(String mandatoryItemname: mandatoryItemsList){
            Item tempItem=new Item(mandatoryItemname,false);
            tempList.add(tempItem);
        }
        return tempList;
    }
}
