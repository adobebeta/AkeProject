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
            EditText name = getView().findViewById(R.id.name);
            String _name = name.getText().toString();
            @Override
            public void onClick(View v) {
                if (_name.length() < 3){
                    Toast.makeText(getActivity(),_name.length(),Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences sp = getContext().getSharedPreferences("name", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USERNAME", _name);
                    editor.commit();

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
