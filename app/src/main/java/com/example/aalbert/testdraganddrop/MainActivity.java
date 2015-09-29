package com.example.aalbert.testdraganddrop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DynamicListView listView = (DynamicListView) findViewById(R.id.dynamiclistview);
        listView.enableDragAndDrop();
        listView.setDraggableManager(new TouchViewDraggableManager(R.id.grip));


        final ArrayList<String> items = new ArrayList<>();
        for(int i = 0; i < 30; ++i) {
            items.add("item" + i);
        }

        listView.setAdapter(new MyAdapter(this, items));
    }

  private class MyAdapter extends ArrayAdapter<String>  {

    private final Context context;

    public MyAdapter(Context context, ArrayList<String> items) {
      this.context = context;

      for (String  item : items) {
        add(item);
      }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
      if (view == null) {
        view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
      }

      ((TextView) view.findViewById(R.id.name)).setText(getItem(position));

      return view;
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

    @Override
    public long getItemId(final int position) {
      return getItem(position).hashCode();
    }
  }
}
