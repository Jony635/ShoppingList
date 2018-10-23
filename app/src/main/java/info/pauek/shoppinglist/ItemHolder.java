package info.pauek.shoppinglist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ItemHolder extends RecyclerView.ViewHolder {
    private TextView name_view;
    private CheckBox selected_checkbox;

    public ItemHolder(@NonNull View itemView, final ShoppingListAdapter.OnClickListener onClickListener, final ShoppingListAdapter.OnLongClickListener onLongClickListener) {
        super(itemView);
        name_view = itemView.findViewById(R.id.name_view);
        selected_checkbox = itemView.findViewById(R.id.selected_checkbox);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    int pos = getAdapterPosition();
                    onClickListener.onClick(pos);
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view) {
                if(onLongClickListener != null)
                {
                    int pos = getAdapterPosition();
                    onLongClickListener.OnLongClick(pos);
                }
                return true;
            }
        });
    }

    public void bind(ShoppingItem item) {
        name_view.setText(item.getName());
        selected_checkbox.setChecked(item.selected);
    }
}
