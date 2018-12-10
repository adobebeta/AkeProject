package com.example.zoom.akeproject.Weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zoom.akeproject.R;

import java.util.List;

/**
 * Created by adobebeta on 10/12/2018 AD.
 */

public class WeightAdapter extends ArrayAdapter<Weight>{
    private List<Weight> weightList;
    private Context context;


    public WeightAdapter(@NonNull Context context, int resource, @NonNull List<Weight> objects){
        super(context, resource, objects);
        this.weightList = objects;
        this.context = context;
    }


    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {


        View weightItem = LayoutInflater.from(context).inflate(R.layout.weight_item,parent,false);

        TextView weight_date = (TextView) weightItem.findViewById(R.id.weight_date);
        TextView weight = (TextView) weightItem.findViewById(R.id.weight);

        Weight sl = weightList.get(position);


        weight_date.setText(sl.getDate());
        weight.setText(sl.getWeight());

        return weightItem;

    }

}




