package com.eu.fpms.bbap;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<CustomListView> arrayList;

    public CustomAdapter(Context context){

        this.context = context;
        arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        //beer est dans le fichier strings.xml
        String[] names = resources.getStringArray(R.array.beer);
        int[] images = {R.drawable.chimay_bleue, R.drawable.orval, R.drawable.rochefort};

        for (int pos=0; pos<names.length;pos++){
            arrayList.add(new CustomListView(names[pos],images[pos]));
        }
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_layout,parent,false);
        //View row = inflater.inflate(R.layout.row_items,parent,false);
        TextView textView = (TextView)row.findViewById(R.id.tvBeername);
        ImageView imageView = (ImageView)row.findViewById(R.id.imviewBeer);
        CustomListView temp_obj = arrayList.get(position);

        textView.setText(temp_obj.name);
        imageView.setImageResource(temp_obj.image);

        return row;
    }
}
