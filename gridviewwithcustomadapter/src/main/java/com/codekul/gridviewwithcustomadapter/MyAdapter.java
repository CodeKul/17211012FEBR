package com.codekul.gridviewwithcustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aniruddha on 2/3/17.
 */

public class MyAdapter extends BaseAdapter {

    private final Context context;
    private final List<GridItem> dataSet;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<GridItem> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSet.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if(convertView == null) view = inflater.inflate(R.layout.grid_item, parent, false);
        else view = convertView;

        ((ImageView)view.findViewById(R.id.imgVw)).setImageResource(dataSet.get(position).img);
        ((TextView)view.findViewById(R.id.txtVw)).setText(dataSet.get(position).txt);
        return view;
    }
}
