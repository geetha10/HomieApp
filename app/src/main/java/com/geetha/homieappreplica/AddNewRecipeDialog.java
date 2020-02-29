package com.geetha.homieappreplica;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddNewRecipeDialog extends AppCompatDialogFragment {
    private ImageView addImage;
    private EditText recipeNameET;
    private EditText recipieIngredientsET;
    private EditText recipeInstsET;
   // private AddNewRecipeDialogListener Listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dalogfragment_addrecipe,null);
        builder.setView(view).setTitle("Add New Recipe").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        recipeNameET=view.findViewById(R.id.recipeNameET);
        recipieIngredientsET= view.findViewById(R.id.ingredientsET);
        recipeInstsET=view.findViewById(R.id.recipeInsts);
        return builder.create();
    }

    /*@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface AddNewRecipeDialogListener {
        void applyTexts(String username, String password);
    }*/
}


