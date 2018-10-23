package info.pauek.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    // TODO: 1. Afegir un CheckBox a cada ítem, per marcar o desmarcar els ítems (al model també!)  v
    // TODO: 2. Que es puguin afegir elements (+ treure els inicials)                               v
    // TODO: 3. Afegir un menú amb una opció per esborrar de la llista tots els marcats.
    // TODO: 4. Que es pugui esborrar un element amb LongClick (cal fer OnLongClickListener)

    // Model
    List<ShoppingItem> items;

    // Referències a elements de la pantalla
    private RecyclerView items_view;
    private ImageButton btn_add;
    private EditText edit_box;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        items = new ArrayList<>();

        items_view = findViewById(R.id.items_view);
        btn_add = findViewById(R.id.btn_add);
        edit_box = findViewById(R.id.edit_box);

        adapter = new ShoppingListAdapter(this, items);

        items_view.setLayoutManager(new LinearLayoutManager(this));
        items_view.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        items_view.setAdapter(adapter);

        adapter.setOnClickListener(new ShoppingListAdapter.OnClickListener() {
            @Override
            public void onClick(int position)
            {
                String msg = "Has clicat: " + items.get(position).getName();
                items.get(position).selected = !items.get(position).selected;
                adapter.notifyItemChanged(position);
                Toast.makeText(ShoppingListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        //adapter.setOnLongClickListener(()
       // {

        //};
    }

    public void OnClickButtonAdd(View view) {
        String name = edit_box.getText().toString();
        if(!name.isEmpty())
        {
            items.add(new ShoppingItem(name));
            edit_box.setText("");
            adapter.notifyItemInserted(items.size());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu._shoppinglistmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.delete_items:
                for(int i = 0; i < items.size(); ++i)
                {
                    if(items.get(i).selected)
                    {
                        items.remove(i);
                        adapter.notifyItemRemoved(i);
                        i--;
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
