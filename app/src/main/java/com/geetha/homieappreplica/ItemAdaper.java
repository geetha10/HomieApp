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

    /*interface OnItemUpdateCallback {
        void update(Item item, int position);
    }*/
    private List<Item> listOfItems;
   // private OnItemUpdateCallback callback;

    private static final String TAG = "ItemAdapter";

    public ItemAdaper(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
       // this.callback = callback;
    }

    /*public ItemAdaper(List<Item> listOfItems, OnItemUpdateCallback callback) {
        this.listOfItems = listOfItems;
        this.callback = callback;
    }*/

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        itemViewHolder.itemNameTV.setText(listOfItems.get(position).itemName);
        itemViewHolder.itemIsDoneCB.setOnCheckedChangeListener(null);
        if(listOfItems.get(position).itemIsDone){
            itemViewHolder.itemIsDoneCB.setChecked(true);
            itemViewHolder.itemNameTV.setPaintFlags(STRIKE_THRU_TEXT_FLAG);
        }
        else{
            itemViewHolder.itemIsDoneCB.setChecked(false);
            itemViewHolder.itemNameTV.setText(listOfItems.get(position).itemName);
        }
        itemViewHolder.itemIsDoneCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //int currentposition=itemViewHolder.getAdapterPosition() ;
                Item itemToUpdateBf = listOfItems.get(position);
                itemToUpdateBf.setItemIsDone(isChecked);
                //callback.update(itemToUpdateBf, position);
                if(isChecked) {
                    Item itemToUpdateAf = new Item(itemToUpdateBf.getItemName(),true);
                    listOfItems.remove(itemToUpdateBf);
                    listOfItems.add(itemToUpdateAf);
                    notifyDataSetChanged();
                }
                else{
                    listOfItems.get(position).setItemIsDone(false);
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNameTV;
        private CheckBox itemIsDoneCB;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTV=itemView.findViewById(R.id.itemNameTV);
            itemIsDoneCB=itemView.findViewById(R.id.itemIsDoneCB);

        }

    }

}
