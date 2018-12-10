package com.example.zoom.akeproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zoom.akeproject.Weight.Weight;
import com.example.zoom.akeproject.Weight.WeightAdapter;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class ViewFragment extends Fragment {
    private ListView listView;
    ArrayList<Weight> weightList = new ArrayList<>();
    private SQLiteDatabase database;
    private WeightAdapter weightAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getView().findViewById(R.id.listView);
        weightAdapter = new WeightAdapter(getActivity(), R.layout.weight_item, weightList);

        //open to use db
        database = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);


        //เปิด sherePrefences
        SharedPreferences spShow = getContext().getSharedPreferences("name", MODE_PRIVATE);
        String name = spShow.getString("username","0");

//        database.execSQL("DROP TABLE IF EXISTS "+name);

        System.out.print("aaaaaaaaaaa     "+name);
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS "+name+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR(15), weight VARCHAR(15))"
        );

        String date,weight;

        //เปิด sherePrefences


        //query data
        Cursor db_query = database.rawQuery("SELECT * FROM "+name, null);

        weightAdapter.clear();

        while(db_query.moveToNext()) {

            int id = db_query.getInt(0);
            date = db_query.getString(1);
            weight = db_query.getString(2);

            weightList.add(new Weight(date, weight));
        }

        listView.setAdapter(weightAdapter);
        weightAdapter.notifyDataSetChanged();

        db_query.close();
        database.close();
    }
}
