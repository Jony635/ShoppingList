package info.pauek.shoppinglist;

import android.view.View;
import android.widget.CheckBox;

public class ShoppingItem {
    private String name;
    public boolean selected;

    public ShoppingItem(String name) {
        this.name = name;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
