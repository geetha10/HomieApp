package com.geetha.homieappreplica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.graphics.Paint.STRIKE_THRU_TEXT_FLAG;

public class ItemAdaper  extends RecyclerView.Adapter<ItemAdaper.ItemViewHolder> {

    private List<Item> listOfItems;

    public ItemAdaper(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.itemNameRV.setText(listOfItems.get(position).itemName);
        itemViewHolder.itemIsDoneCB.setChecked(listOfItems.get(position).itemIsDone);
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        private TextView itemNameRV;
        private CheckBox itemIsDoneCB;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameRV=itemView.findViewById(R.id.itemNameTV);
            itemIsDoneCB=itemView.findViewById(R.id.itemIsDoneCB);
            itemIsDoneCB.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            itemNameRV.setPaintFlags(STRIKE_THRU_TEXT_FLAG);
        }
    }
}
