package com.example.zoom.akeproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zoom.akeproject.Weight.WeightFormFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    ArrayList<String> menu ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menu = new ArrayList<>();
        menu.add("Add");
        menu.add("View");
        menu.add("Theme");
        menu.add("Load Image");

        ListView menuList = getView().findViewById(R.id.menu_listview);


        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menu
        );

        menuList.setAdapter(menuAdapter);

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MENU", "Click on menu = " + menu.get(position));

                if (menu.get(position).equals("Add")) {
                    Log.d("USER", "GOTO ADD");
                    Toast.makeText
                            (getContext(), "GO TO ADD", Toast.LENGTH_SHORT)
                            .show();

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFormFragment())
                            .addToBackStack(null)
                            .commit();
                }

                else if (menu.get(position).equals("View")){
                    Log.d("USER", "GOTO VIEW");
                    Toast.makeText
                            (getContext(), "GO TO VIEW", Toast.LENGTH_SHORT)
                            .show();

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new ViewFragment())
                            .addToBackStack(null)
                            .commit();


                }
                else if (menu.get(position).equals("Load Image")){
                    Log.d("USER", "GOTO VIEW");
                    Toast.makeText
                            (getContext(), "GO TO VIEW", Toast.LENGTH_SHORT)
                            .show();

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoadImageFragment())
                            .addToBackStack(null)
                            .commit();


                }
            }
        });

    }
}
