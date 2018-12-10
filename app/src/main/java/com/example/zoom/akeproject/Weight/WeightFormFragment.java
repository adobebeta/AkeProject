package com.example.zoom.akeproject.Weight;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zoom.akeproject.MenuFragment;
import com.example.zoom.akeproject.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by adobebeta on 10/12/2018 AD.
 */

public class WeightFormFragment extends Fragment {
    Button saveBtn;
    Button cancelBtn;

    SQLiteDatabase myDB;

    ContentValues cv;
    public WeightFormFragment(){
        cv = new ContentValues();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addweight,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        saveBtn = getView().findViewById(R.id.saveBtn);
        cancelBtn = getView().findViewById(R.id.cencelBtn);
        initsaveBtnPressed();
        initcancelBtnPressed();
    }

    void initsaveBtnPressed(){


        saveBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                EditText weight = getView().findViewById(R.id.weight);
                EditText date = getView().findViewById(R.id.date);
                String _weight = weight.getText().toString();
                String _date = date.getText().toString();
                myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

                //เปิด sherePrefences
                SharedPreferences spShow = getContext().getSharedPreferences("name", MODE_PRIVATE);
                String name = spShow.getString("username","0");

                Log.d("WeightForm","data in name = "+ name);
        myDB.execSQL("DROP TABLE IF EXISTS "+name);


                myDB.execSQL(
                        "CREATE TABLE IF NOT EXISTS "+name+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR(15), weight VARCHAR(15))"
                );

                Weight item = new Weight();
                item.setContent(_date,_weight);
                cv = item.getContent();

                //เพ่ิ่มข้อมูลลงในตารางของชื่อคนนั้นๆที่เข้ามา
                myDB.insert(name, null, cv);
                Log.d("SLEEP_FORM", "INSERT ALREADY");

                Toast.makeText(getActivity(), "Save Success"
                        , Toast.LENGTH_SHORT)
                        .show();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .commit();
            }
        });
    }

    void initcancelBtnPressed(){
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Back to menu"
                        , Toast.LENGTH_SHORT)
                        .show();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .commit();
            }
        });

    }

}
