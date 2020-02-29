package com.geetha.homieappreplica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements View.OnClickListener{
    FloatingActionButton addRecipefab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeFragment= inflater.inflate(R.layout.fragment_home,container,false);
        addRecipefab=homeFragment.findViewById(R.id.addRecipefab);
        addRecipefab.setOnClickListener(this);

        return homeFragment;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addRecipefab){
            AddNewRecipeDialog addNewRecipeDialog= new AddNewRecipeDialog();
            //addNewRecipeDialog.show(getSupportFragmentManager(),);
            addNewRecipeDialog.show(getFragmentManager(),"Add Recipe Dialog");
        }
    }
}
