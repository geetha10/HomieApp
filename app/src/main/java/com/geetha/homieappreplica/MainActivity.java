package com.geetha.homieappreplica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addItemET;
    Button addItemToListButton;
    TextView displayListTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemET=findViewById(R.id.addItemET);
        addItemToListButton=findViewById(R.id.addItemToListButton);
        addItemToListButton.setOnClickListener(this);
        displayListTV=findViewById(R.id.displayListTV);
    }

    @Override
    public void onClick(View v) {
        String itemName=addItemET.getText().toString();
        displayListTV.setText(itemName);
        addItemET.setText("");
    }
}
