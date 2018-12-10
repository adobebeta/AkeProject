package com.example.zoom.akeproject.Weight;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zoom.akeproject.MenuFragment;
import com.example.zoom.akeproject.R;
import com.example.zoom.akeproject.Weight.Weight;
import com.example.zoom.akeproject.Weight.WeightAdapter;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

//หน้านี้จะเป็นหน้า เรียกดูข้อมูลทั้งหมดที่มี ได้แค่ วันที่ และ น้ำหนัก
public class ViewWeightFragment extends Fragment {
    private ListView listView;
    private WeightAdapter weightAdapter;
    private SQLiteDatabase database;

    ArrayList<Weight> weightList = new ArrayList<>();

    Button backBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ปุ่ม ฺ Back
        backBtn = getView().findViewById(R.id.backBtn);
        initBackBtnPressed();

        ListView listView = getView().findViewById(R.id.listView);
        //use custom listView => weight_item คือ ชื่อของ xml , weightList คือ ชื่อ Arraylistที่เก็บ Weight
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

    private void initBackBtnPressed() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
