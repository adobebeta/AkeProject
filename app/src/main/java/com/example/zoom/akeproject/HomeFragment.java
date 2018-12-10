package com.example.zoom.akeproject;

import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
//    EditText name;
    String _name;
    String _name2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button okBtn = getView().findViewById(R.id.okBtn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = getView().findViewById(R.id.name);
                String _name = name.getText().toString();

                if (_name.length() < 3 || !(Character.isUpperCase(_name.charAt(0))) ){
                    Toast.makeText(getActivity(),"กรุณากรอกข้อมูลให้ถูกต้อง",Toast.LENGTH_SHORT).show();
                }
                else{

                    //โหมดแก้ไข by p'aew
                    SharedPreferences.Editor sp = getContext().getSharedPreferences("name", MODE_PRIVATE).edit();
                    sp.putString("username", _name).apply();
                    sp.commit();

                    //โหมด เรียกมาดู
                    SharedPreferences spShow = getContext().getSharedPreferences("name", MODE_PRIVATE);
                    Log.d("LOGIN",spShow.getString("username","0"));

//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("USERNAME", _name);
//                    editor.commit();

                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view,new MenuFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
