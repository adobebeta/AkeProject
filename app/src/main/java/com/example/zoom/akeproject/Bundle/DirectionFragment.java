package com.example.zoom.akeproject.Bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoom.akeproject.R;

public class DirectionFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_direction,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = getView().findViewById(R.id.direction_textview);

        if(getArguments() != null){
            Bundle bundle = getArguments();
            textView.setText(bundle.getString("bundle"));
            Log.i("BUNDLE", bundle.getString("bundle"));
        }
    }
}
