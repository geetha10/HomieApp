package com.geetha.homieappreplica;

public class Item {

    String itemName;
    Boolean itemIsDone;

    public Item(String itemName, Boolean itemIsDone) {
        this.itemName = itemName;
        this.itemIsDone = itemIsDone;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean getItemIsDone() {
        return itemIsDone;
    }

    public void setItemIsDone(Boolean itemIsDone) {
        this.itemIsDone = itemIsDone;
    }

}
